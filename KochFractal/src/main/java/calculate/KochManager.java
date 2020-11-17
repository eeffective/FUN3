/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import java.util.*;
import java.util.concurrent.*;

import enums.EdgeSide;
import fun3kochfractalfx.FUN3KochFractalFX;
import timeutil.TimeStamp;


/**
 * @author Nico Kuijpers
 * Modified for FUN3 by Gertjan Schouten
 */

public class KochManager {

    private ArrayList<Edge> edges;
    private FUN3KochFractalFX application;
    private TimeStamp tsCalc;
    private TimeStamp tsDraw;
    private List<EdgeGenerator> edgeGenerators;
    private List<Future<List<Edge>>> results;


    public KochManager(FUN3KochFractalFX application) {
        this.edges = new ArrayList<>();
        this.application = application;
        this.tsCalc = new TimeStamp();
        this.tsDraw = new TimeStamp();
    }

    public void changeLevel(int nxt) {

        edges.clear();
        tsCalc.init();
        tsCalc.setBegin("Begin calculating");

        createEdgeGenerators(nxt);
        bindProgressBars();
        calculateEdges();

    }

    public synchronized void drawEdges() {
        tsDraw.init();
        tsDraw.setBegin("Begin drawing");
        application.clearKochPanel();
        for (Edge e : edges) {
            application.drawEdge(e);
        }
        tsDraw.setEnd("End drawing");
        application.setTextDraw(tsDraw.toString());
    }

    private void calculateEdges() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        results = new ArrayList<>();
        try {
            for (var eg : edgeGenerators) {
                results.add(pool.submit((Callable<List<Edge>>) eg));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            pool.shutdown();
        }
    }

    private void createEdgeGenerators(int nxt) {
        edgeGenerators = new ArrayList<>();
        edgeGenerators.add(new EdgeGenerator(EdgeSide.LEFT, nxt, this));
        edgeGenerators.add(new EdgeGenerator(EdgeSide.RIGHT, nxt, this));
        edgeGenerators.add(new EdgeGenerator(EdgeSide.BOTTOM, nxt, this));
    }


    private void bindProgressBars() {
        this.application.getProgressBarLeft().progressProperty().bind(edgeGenerators.get(0).progressProperty());
        this.application.getProgressBarRight().progressProperty().bind(edgeGenerators.get(1).progressProperty());
        this.application.getProgressBarBottom().progressProperty().bind(edgeGenerators.get(2).progressProperty());

        this.application.getLblProgressBarLeft().textProperty().bind(edgeGenerators.get(0).messageProperty());
        this.application.getLblProgressBarRight().textProperty().bind(edgeGenerators.get(1).messageProperty());
        this.application.getLblProgressBarBottom().textProperty().bind(edgeGenerators.get(2).messageProperty());
    }


    public void resultsReady() {

        if (results.size() != 3) {
            return;
        }

        if (results.stream().allMatch(f -> f.isDone())) {
            edges.clear();
            results.stream().forEach(f -> {
                try {
                    edges.addAll(f.get());
                } catch (Exception e) {
                    System.out.println(e);
                }
            });

            tsCalc.setEnd("End calculating");
            application.setTextNrEdges("" + edges.size());
            application.setTextCalc(tsCalc.toString());
            application.requestDrawEdges();
            results.clear();
        }
    }
}
