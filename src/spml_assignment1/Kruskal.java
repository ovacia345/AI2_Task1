package spml_assignment1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author Jasper
 */
public class Kruskal {
    private final boolean verbose;

    public Kruskal(boolean verbose) {
        this.verbose = verbose;
    }

    private List<Set<Integer>> generateUnconnectedVertices(int numVertices) {
        List<Set<Integer>> connectedVertices = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; ++i) {
            connectedVertices.add(new HashSet<>());
            connectedVertices.get(i).add(i);
        }

        return connectedVertices;
    }

    private void mergeSets(List<Set<Integer>> connectedVertices,
            int start, int end) {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < connectedVertices.size(); ++i) {
            if (connectedVertices.get(i).contains(start)) {
                startIndex = i;
            }
            if (connectedVertices.get(i).contains(end)) {
                endIndex = i;
            }
        }

        if (startIndex != endIndex) {
            connectedVertices.get(startIndex).addAll(
                    connectedVertices.remove(endIndex));
        }
    }

    public boolean areVerticesConnected(List<Set<Integer>> connectedVertices,
            int v1, int v2) {
        return connectedVertices.stream().anyMatch((connectedSet)
                -> (connectedSet.contains(v1) && connectedSet.contains(v2)));
    }

    public Graph run(Graph graph) {
        Graph mst = new Graph(graph.getNumberOfVertices());

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 0; i < graph.getNumberOfVertices(); ++i) {
            for (int j = 0; j < graph.getNumberOfVertices(); ++j) {
                double cost = graph.getCost(i, j);

                if (cost > 0.0d) {
                    edges.add(new Edge(i, j, cost));
                }
            }
        }

        List<Set<Integer>> connectedVertices = generateUnconnectedVertices(
                graph.getNumberOfVertices());
        Edge edge = edges.poll();
        mst.setCost(edge.getStart(), edge.getEnd(), edge.getCost());
        int nEdgesConsidered = 0;
        while (!edges.isEmpty()
                && connectedVertices.size() > 1) {
            nEdgesConsidered++;
            edge = edges.poll();

            if (!areVerticesConnected(connectedVertices, edge.getStart(),
                    edge.getEnd())) {
                mst.setCost(edge.getStart(), edge.getEnd(), edge.getCost());
                mergeSets(connectedVertices, edge.getStart(), edge.getEnd());
            }
        }

        if (connectedVertices.size() > 1) {
            throw new IllegalArgumentException("Not all edges in the graph "
                    + "are connected.");
        }

        if (verbose) {
            System.out.println(nEdgesConsidered + " edges considered.");
        }

        return mst;
    }
}
