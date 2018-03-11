package spml_assignment1;

/**
 *
 * @author Jasper
 */
public class Edge implements Comparable<Edge> {
    private final int start;
    private final int end;
    private final double cost;

    public Edge(int start, int end, double cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(cost, o.cost);
    }
}
