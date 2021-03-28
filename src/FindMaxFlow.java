import java.io.File;
import java.io.IOException;
import java.util.*;

public class FindMaxFlow {
    private int source = 0;
    private int vertexCount;
    private int sink = vertexCount - 1;
    private Scanner sc = new Scanner(System.in);
    private Graph graph;

    private static boolean bfs(Graph residualGraph, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[residualGraph.getVertexCount()]; // Array to store the visited vertices
        LinkedList<Integer> queue = new LinkedList<>(); //queue which stores the vertices to be visited in order
        for (int i = 0; i < residualGraph.getVertexCount(); i++)
            visited[i] = false;


        //visit source
        queue.add(source);
        visited[source] = true;
        parent[source] = 0;

        //loop through all vertices
        while (!queue.isEmpty()) {
            int i = queue.poll();
            // check neighbours of vertex i
            for (Integer j : residualGraph.neighbours(i)) {
                // if not visited and positive value then visit
                if ((!visited[j]) && (residualGraph.getAdjacent()[i][j] > 0)) {
                    queue.add(j);
                    visited[j] = true;
                    parent[j] = i;
                }
            }
        }
        return visited[sink];
    }

    private static int FordFulkerson(Graph graph, int source, int sink) {
        int vertexCount = graph.getVertexCount(); //number of vertices
        int[] parent = new int[vertexCount]; //store path with parent of each node
        int maxFlow = 0; // max flow value

        //error validation
        if (source == sink) {
            return 0;
        }

        // create residual graph
        Graph residualGraph = new Graph(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                residualGraph.getAdjacent()[i][j] = graph.getAdjacent()[i][j]; //map the original graph to the residual graph
            }
        }

        // while a path exists from source to dest loop
        while (bfs(residualGraph, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE; // to store path flow
            ArrayList<Integer> pathView = new ArrayList<>(); //stores the augmenting path

            // find maximum flow of path augmented by bfs method
            for (int i = sink; i != source; i = parent[i]) {
                pathView.add(i); //add visited node to the path
                int j = parent[i];
                pathFlow = Math.min(pathFlow, residualGraph.getAdjacent()[j][i]);
            }

            Collections.reverse(pathView);
            System.out.println("Augmenting path: " + pathView.toString().replace("[", "").replace("]", "") + " | Path flow: " + pathFlow);
            pathView.clear();

            // update residual graph capacities
            // reverse edges along the path
            for (int i = sink; i != source; i = parent[i]) {
                int j = parent[i];
                residualGraph.getAdjacent()[j][i] -= pathFlow;
                residualGraph.getAdjacent()[i][j] += pathFlow;
            }

            // Add path flow to max flow
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private void readFile(String fileName) {
        try {
            int lineCount = 0;
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (lineCount == 0) {
                    String[] details = line.split(" ");
                    vertexCount = Integer.parseInt(details[0]);
                    sink = vertexCount - 1;
                    graph = new Graph(vertexCount);
                    System.out.println("Vertex count : " + vertexCount + " | Sink : " + sink);
                } else {
                    String[] details = line.split(" ");
                    int node1 = Integer.parseInt(details[0]);
                    int node2 = Integer.parseInt(details[1]);
                    int weight = Integer.parseInt(details[2]);

                    graph.addEdge(node1, node2, weight);
                }
                lineCount++;
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FindMaxFlow findMaxFlow = new FindMaxFlow();
        findMaxFlow.execute();
    }

    public void findMaxFlow (String filename) {
        long start = System.currentTimeMillis();
       // String fileName_bridge1 = "datasets/bridge_1.txt";
        readFile(filename);
        System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        System.out.println(">>> Elapsed time = " + elapsed + " seconds <<<\n");
    }

    private void execute() {
        exitMenu:
        while (true)
        {
            System.out.println("==============Welcome to calculate Max Flow==============");
            System.out.println("===Select the option below to continue===");
            System.out.println("(1) To Calculate max flow in Bridge files");
            System.out.println("(2) To Calculate max flow in Ladder files");
            System.out.println("(3) Exit the programme : ");
            Scanner scanner = new Scanner(System.in);
            try {
                int menuOption = scanner.nextInt();
                switch (menuOption)
                {
                    case 1:
                        System.out.println("-----Select the file number-----");
                        System.out.println("(1) file with 6 nodes");
                        System.out.println("(2) file with 10 nodes");
                        System.out.println("(3) file with 18 nodes");
                        System.out.println("(4) file with 34 nodes");
                        System.out.println("(5) file with 66 nodes");
                        System.out.println("(6) file with 130 nodes");
                        System.out.println("(7) file with 258 nodes");
                        System.out.println("(8) file with 514 nodes");
                        System.out.println("(9) file with 1026 nodes");
                        int bridgeOption = scanner.nextInt();
                        switch (bridgeOption)
                        {
                            case 1:
//                                long start = System.currentTimeMillis();
//                                String fileName_bridge1 = "datasets/bridge_1.txt";
//                                readFile(fileName_bridge1);
//                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
//                                long now = System.currentTimeMillis();
//                                double elapsed = (now - start) / 1000.0;
//                                System.out.println(">>> Elapsed time = " + elapsed + " seconds <<<\n");
                                findMaxFlow("datasets/bridge_1.txt");
                                break;
                            case 2:
//                                long start = System.currentTimeMillis();
//                                String fileName_bridge2 = "datasets/bridge_2.txt";
//                                readFile(fileName_bridge2);
//                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                findMaxFlow("datasets/bridge_2.txt");
                                break;
                            case  3:
//                                String fileName_bridge3 = "datasets/bridge_3.txt";
//                                readFile(fileName_bridge3);
//                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                findMaxFlow("datasets/bridge_3.txt");
                                break;
                            case 4:
//                                String fileName_bridge4 = "datasets/bridge_4.txt";
//                                readFile(fileName_bridge4);
//                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                findMaxFlow("datasets/bridge_4.txt");
                                break;
                            case 5:
//                                String fileName_bridge5 = "datasets/bridge_5.txt";
//                                readFile(fileName_bridge5);
//                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                findMaxFlow("datasets/bridge_5.txt");
                                break;
                            case 6:
//                                String fileName_bridge6 = "datasets/bridge_6.txt";
//                                readFile(fileName_bridge6);
//                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                findMaxFlow("datasets/bridge_6.txt");
                                break;
                            case 7:
//                                String fileName_bridge7 = "datasets/bridge_7.txt";
//                                readFile(fileName_bridge7);
//                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                findMaxFlow("datasets/bridge_7.txt");
                                break;
                            case 8:
//                                String fileName_bridge8 = "datasets/bridge_8.txt";
//                                readFile(fileName_bridge8);
//                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                findMaxFlow("datasets/bridge_8.txt");
                                break;
                            case 9:
//                                String fileName_bridge9 = "datasets/bridge_9.txt";
//                                readFile(fileName_bridge9);
//                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                findMaxFlow("datasets/bridge_9.txt");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("-----Select the file number-----");
                        System.out.println("(1) file with 6 nodes");
                        System.out.println("(2) file with 12 nodes");
                        System.out.println("(3) file with 24 nodes");
                        System.out.println("(4) file with 48 nodes");
                        System.out.println("(5) file with 96 nodes");
                        System.out.println("(6) file with 192 nodes");
                        System.out.println("(7) file with 384 nodes");
                        System.out.println("(8) file with 768 nodes");
                        System.out.println("(9) file with 1536 nodes");
                        int ladderOption = scanner.nextInt();
                        switch (ladderOption)
                        {
                            case 1:
                                String fileName_ladder1 = "datasets/ladder_1.txt";
                                readFile(fileName_ladder1);
                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                break;
                            case 2:
                                String fileName_ladder2 = "datasets/ladder_2.txt";
                                readFile(fileName_ladder2);
                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                break;
                            case  3:
                                String fileName_ladder3 = "datasets/ladder_3.txt";
                                readFile(fileName_ladder3);
                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                break;
                            case 4:
                                String fileName_ladder4 = "datasets/ladder_4.txt";
                                readFile(fileName_ladder4);
                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                break;
                            case 5:
                                String fileName_ladder5 = "datasets/ladder_5.txt";
                                readFile(fileName_ladder5);
                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                break;
                            case 6:
                                String fileName_ladder6 = "datasets/ladder_6.txt";
                                readFile(fileName_ladder6);
                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                break;
                            case 7:
                                String fileName_ladder7 = "datasets/ladder_7.txt";
                                readFile(fileName_ladder7);
                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                break;
                            case 8:
                                String fileName_ladder8 = "datasets/ladder_8.txt";
                                readFile(fileName_ladder8);
                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                break;
                            case 9:
                                String fileName_ladder9 = "datasets/ladder_9.txt";
                                readFile(fileName_ladder9);
                                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(graph, source, sink) + "\n");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("Exiting the programme......");
                        break exitMenu;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid selection !");
            }
        }


    }
}
