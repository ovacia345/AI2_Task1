package spml_assignment1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Jasper
 */
public class GraphGenerator {
    private final Random random;

    public GraphGenerator() {
        random = new Random();
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

    public Graph getGraph(int numVertices, int minCycles, double connectedProb,
            double minCost, double maxCost) {
        Graph graph = new Graph(numVertices);
        List<Set<Integer>> connectedVertices = generateUnconnectedVertices(
                numVertices);

        int counter = 0;
        double range = maxCost - minCost;
        while (counter < minCycles || connectedVertices.size() > 1) {
            for (int start = 0; start < numVertices; ++start) {
                for (int end = 0; end < numVertices; ++end) {
                    if (start <= end) {
                        if (graph.getCost(start, end) == 0.0d
                                && random.nextDouble() < connectedProb) {
                            graph.setCost(start, end, range
                                    * random.nextDouble()
                                    + minCost);
                            mergeSets(connectedVertices, start, end);
                        }
                    }
                }
            }

            counter++;
        }

        return graph;
    }
}
