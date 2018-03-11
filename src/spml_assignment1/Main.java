package spml_assignment1;

/**
 *
 * @author Jasper
 */
public class Main {
    private static void printGraph(Graph graph) {
        double sum = 0.0d;
        for (int i = 0; i < graph.getNumberOfVertices(); ++i) {
            for (int j = 0; j < graph.getNumberOfVertices(); ++j) {
                if (i < j) {
                    double cost = graph.getCost(i, j);
                    if (cost > 0.0d) {
                        System.out.println(i + " - " + j + ": " + cost);
                        sum += cost;
                    }
                }
            }
        }

        System.out.println("Total cost: " + sum);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);

        graph.setCost(0, 1, 2.0d);
        graph.setCost(0, 3, 1.0d);
        graph.setCost(1, 3, 2.0d);
        graph.setCost(2, 3, 3.0d);
//        Graph graph = new GraphGenerator().getGraph(4, 20, 1.0d, 1.0, 3.0);

        System.out.println("Original graph");

        printGraph(graph);

        Kruskal alg = new Kruskal(true);
        Graph mst = alg.run(graph);

        printGraph(mst);
    }
}
