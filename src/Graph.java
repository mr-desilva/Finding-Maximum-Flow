import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    private int vertexCount; // Store the count of the vertex
    private int[][] adjacent; // Store the adjacent between two edges

    public Graph(int vertexCount) { // Constructor
        this.vertexCount = vertexCount;
        adjacent = new int[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++)
        {
            for (int k = 0; k < vertexCount; k++)
            {
                adjacent[i][k] = 0;
            }
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int[][] getAdjacent() {
        return adjacent;
    }

    public void addEdge(int i, int k, int capacity) {
        adjacent[i][k] = capacity;
    }

    public void removeEdge(int i, int k) {
        adjacent[i][k] = 0;
    }

    public void editEdgeCapacity (int i, int k, int capacity) {
        adjacent[i][k] = capacity;
    }

    public boolean foundEdge(int i, int k) {
        return adjacent[i][k] != 0;
    }

    List<Integer> neighbours(int vertex) {
        List<Integer> edges = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++)
            if (foundEdge(vertex, i))
                edges.add(i);
        return edges;
    }

    void printGraph(Graph graph) {
        System.out.println(Arrays.deepToString(graph.getAdjacent())
                .replace("],","\n").replace(",","\t| ")
                .replaceAll("[\\[\\]]", " "));
        }
}
