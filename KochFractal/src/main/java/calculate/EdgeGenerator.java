package calculate;

import enums.EdgeSide;
import javafx.application.Platform;
import javafx.concurrent.Task;
import observer.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class EdgeGenerator extends Task implements Listener, Callable<List<Edge>> {

    private EdgeSide edgeSide;
    private KochFractal fractal;
    private KochManager manager;
    private ArrayList<Edge> edges;
    private Integer maxEdges;

    public EdgeGenerator(EdgeSide edgeSide, Integer nxt, KochManager manager) {
        this.fractal = new KochFractal();
        this.fractal.addListener(this);
        this.fractal.setLevel(nxt);
        this.edgeSide = edgeSide;
        this.edges = new ArrayList<>();
        this.manager = manager;
        this.maxEdges = this.fractal.getNumberOfEdges() / 3;
    }

    @Override
    public List<Edge> call() throws Exception {
        switch (this.edgeSide) {
            case LEFT:
                fractal.generateLeftEdge();
                break;
            case RIGHT:
                fractal.generateRightEdge();
                break;
            case BOTTOM:
                fractal.generateBottomEdge();
                break;
        }

        Platform.runLater(() -> manager.resultsReady());

        return this.edges;
    }

    public void addEdge(Edge e) {
        this.edges.add(e);
    }

    @Override
    public void update(Object object) {
        addEdge((Edge) object);
        updateProgress(this.edges.size(), maxEdges);
        updateMessage("Number of edges: " + this.edges.size());
    }

}


