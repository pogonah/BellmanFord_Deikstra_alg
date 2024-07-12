import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void bellmanFord(int[][] graph, int source) {
        int n = graph.length;
        int[] dist = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;

        for (int i = 0; i < n-1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                            && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        printSolution(dist);
    }

    public static void printSolution(int[] dist) {
        System.out.println("Вершина \t Расстояние от источника");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i+1 + "                     \t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = readGraphFromFile("input3.txt");
        bellmanFord(graph, 0);
    }

    public static int[][] readGraphFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int n = sc.nextInt();
            int[][] graph = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = sc.nextInt();
                }
            }
            sc.close();
            return graph;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}