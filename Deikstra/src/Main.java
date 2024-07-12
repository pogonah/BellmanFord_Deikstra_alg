import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    public static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static void printSolution(int[] dist) {
        System.out.println("Вершина \t Расстояние от источника");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i+1 + "                    \t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = readGraphFromFile("input3.txt");
        dijkstra(graph, 0);
    }

    public static int[][] readGraphFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int n = sc.nextInt();
            int[][] graph = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                        graph[i][j] = sc.nextInt();
                    if (graph[i][j] < 0) {
                        System.out.println("В матрице есть отрицательные элементы");
                        System.out.println("           Остановка кода            ");
                        System.exit(0);
                    }
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
//Код, который был представлен выше, считывает матрицу смежности из текстового файла, применяет алгоритм Дейкстры для поиска кратчайших путей от заданной вершины (в данном случае от вершины 0) до всех остальных вершин в графе и выводит найденные расстояния.
//
//Вывод программы будет представлять собой таблицу, в которой для каждой вершины будет указано расстояние от начальной вершины до соответствующей вершины. Например, для графа из предыдущего примера результат программы может быть следующим:
//
//Vertex Distance from Source
//0       0
//1       4
//2       12
//3       19
//4       21
//5       11
//6       9
//7       8
//8       14
//Таким образом, программа выводит кратчайшие расстояния от начальной вершины до каждой другой вершины в графе.