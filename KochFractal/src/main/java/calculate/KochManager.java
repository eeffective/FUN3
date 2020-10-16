/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
    private Future<List<Edge>> futureLeft;
    private Future<List<Edge>> futureBottom;
    private Future<List<Edge>> futureRight;

    public KochManager(FUN3KochFractalFX application) {
        this.edges = new ArrayList<Edge>();
        this.application = application;
        this.tsCalc = new TimeStamp();
        this.tsDraw = new TimeStamp();
    }


    public void changeLevel(int nxt) {
        // aanmaken van een pool voor de 3 threads
        ExecutorService pool = Executors.newFixedThreadPool(3);

        edges.clear();
        tsCalc.init();
        tsCalc.setBegin("Begin calculating");

        // aanroepen threads
        EdgeGenerator left = new EdgeGenerator("left", nxt, this);
        EdgeGenerator bottom = new EdgeGenerator("bottom", nxt, this);
        EdgeGenerator right = new EdgeGenerator("right", nxt, this);

        // vullen van de pool
        futureLeft = pool.submit(left);
        futureBottom = pool.submit(bottom);
        futureRight = pool.submit(right);

        pool.shutdown();

    }

    public void drawEdges() {
        tsDraw.init();
        tsDraw.setBegin("Begin drawing");
        application.clearKochPanel();
        for (Edge e : edges) {
            application.drawEdge(e);
        }
        tsDraw.setEnd("End drawing");
        application.setTextDraw(tsDraw.toString());
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public void allDone() {
        if (futureRight == null || futureBottom == null || futureLeft == null) return;
        if (futureRight.isDone() && futureBottom.isDone() && futureLeft.isDone()) {
            edges.clear();

            try {
                edges.addAll(futureLeft.get());
                edges.addAll(futureBottom.get());
                edges.addAll(futureRight.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            tsCalc.setEnd("Stop calculating");
            application.setTextNrEdges("" + this.edges.size());
            application.setTextCalc(tsCalc.toString());
            drawEdges();
            clearFutures();
        }
    }

    void clearFutures() {
        futureRight = null;
        futureBottom = null;
        futureLeft = null;
    }

    public FUN3KochFractalFX getApplication() {
        return this.application;
    }

}
