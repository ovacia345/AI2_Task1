package spml_assignment1;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author Jasper
 */
public class Prim {
    private final boolean verbose;

    public Prim(boolean verbose) {
        this.verbose = verbose;
    }

    private void addEdgesToQueue(PriorityQueue edges, Graph graph,
            int vertex) {
        for (int i = 0; i < graph.getNumberOfVertices(); ++i) {
            double cost = graph.getCost(vertex, i);

            if (cost > 0.0d) {
                edges.add(new Edge(vertex, i, cost));
            }
        }
    }

    public Graph run(Graph graph) {
        Graph mst = new Graph(graph.getNumberOfVertices());

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        addEdgesToQueue(edges, graph, 0);

        Set<Integer> vertices = new HashSet<>(graph.getNumberOfVertices());
        vertices.add(0);

        int nEdgesConsidered = 0;
        while (!edges.isEmpty()
                && vertices.size() < graph.getNumberOfVertices()) {
            nEdgesConsidered++;

            Edge edge = edges.poll();
            if (!vertices.contains(edge.getEnd())) {
                mst.setCost(edge.getStart(), edge.getEnd(),
                        edge.getCost());
                addEdgesToQueue(edges, graph, edge.getEnd());
                vertices.add(edge.getEnd());
            }
        }

        if (vertices.size() < graph.getNumberOfVertices()) {
            throw new IllegalArgumentException("Not all edges in the graph "
                    + "are connected.");
        }

        if (verbose) {
            System.out.println(nEdgesConsidered + " edges considered.");
        }

        return mst;
    }
}
