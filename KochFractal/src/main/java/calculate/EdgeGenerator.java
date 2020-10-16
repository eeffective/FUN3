package calculate;

import javafx.application.Platform;
import timeutil.TimeStamp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class EdgeGenerator implements Callable<List<Edge>> {
    private KochFractal fractal;
    private ArrayList<Edge> edges;
    private String edgeSide;
    private Integer maxEdges;
    private KochManager manager;
    private TimeStamp tsCalc = new TimeStamp();

    public EdgeGenerator(String edgeSide, Integer level, KochManager manager) {
        this.fractal = new KochFractal(this);
        this.edgeSide = edgeSide;
        this.edges = new ArrayList<>();
        this.fractal.setLevel(level);
        this.manager = manager;
        maxEdges = this.fractal.getNrOfEdges() / 3;
    }

    void addEdge(Edge e) {
        this.edges.add(e);
    }

    @Override
    public List<Edge> call() {
        tsCalc.init();
        tsCalc.setBegin("Start calculating");

        this.edges.clear();
        switch (edgeSide) {
            case "left":
                fractal.generateLeftEdge();
                break;
            case "right":
                fractal.generateRightEdge();
                break;
            case "bottom":
                fractal.generateBottomEdge();
                break;
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                manager.allDone();
            }
        });

        return this.edges;
    }
}
