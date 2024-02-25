import java.io.*;

public class Graph {
    private int V; // Кількість вершин
    private int[][] adjMatrix; // Матриця ваг

    public Graph(int V) {
        this.V = V;
        adjMatrix = new int[V][V];
    }

    public void addEdge(int source, int destination, int weight) {
        adjMatrix[source][destination] = weight;
    }

    public void printWeightMatrix(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new File(fileName));

            // Виведення матриці ваг на екран та у файл
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    System.out.print(adjMatrix[i][j] + " ");
                    writer.print(adjMatrix[i][j] + " ");
                }
                System.out.println();
                writer.println();
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Помилка при відкритті файлу: " + e.getMessage());
        }
    }
}
