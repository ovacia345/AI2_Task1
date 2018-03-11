package spml_assignment1;

/**
 *
 * @author Jasper
 */
public class Graph {
    private final double[][] matrix;

    public Graph(int numVertices) {
        this.matrix = new double[numVertices][numVertices];
    }

    public int getNumberOfVertices() {
        return matrix.length;
    }

    public double getCost(int v1, int v2) {
        if (v1 < 0
                || v1 >= matrix.length
                || v2 < 0
                || v2 >= matrix[v1].length) {
            throw new IllegalArgumentException("Index not in vertex "
                    + "boundaries.");
        }

        return matrix[v1][v2];
    }

    public void setCost(int v1, int v2, double cost) {
        if (v1 < 0
                || v1 >= matrix.length
                || v2 < 0
                || v2 >= matrix[v1].length) {
            throw new IllegalArgumentException("Index not in vertex "
                    + "boundaries.");
        }

        matrix[v1][v2] = cost;
        matrix[v2][v1] = cost;
    }
}
