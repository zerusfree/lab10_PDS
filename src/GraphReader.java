import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphReader {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/graph.txt");
            Scanner scanner = new Scanner(inputFile);
            int n = scanner.nextInt(); // кількість вершин
            int m = scanner.nextInt(); // кількість ребер

            // Створюємо список вершин
            List<Vertex> vertices = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                vertices.add(new Vertex(i + 1));
            }

            // Зчитуємо ребра
            for (int i = 0; i < m; i++) {
                int vi = scanner.nextInt();
                int ui = scanner.nextInt();
                int wi = scanner.nextInt();
                Edge edge = new Edge(vertices.get(vi - 1), vertices.get(ui - 1), wi);
                vertices.get(vi - 1).addEdge(edge);
            }

            scanner.close();

            // Виводимо матрицю ваг на екран
            printWeightMatrix(vertices, n);

            // Записуємо матрицю ваг у файл
            File outputFile = new File("src/Output.txt");
            PrintWriter writer = new PrintWriter(outputFile);
            writeWeightMatrixToFile(vertices, n, writer);
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printWeightMatrix(List<Vertex> vertices, int n) {
        int[][] weightMatrix = new int[n][n];
        for (Vertex vertex : vertices) {
            for (Edge edge : vertex.getEdges()) {
                weightMatrix[vertex.getId() - 1][edge.getEnd().getId() - 1] = edge.getWeight();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(weightMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void writeWeightMatrixToFile(List<Vertex> vertices, int n, PrintWriter writer) {
        int[][] weightMatrix = new int[n][n];
        for (Vertex vertex : vertices) {
            for (Edge edge : vertex.getEdges()) {
                weightMatrix[vertex.getId() - 1][edge.getEnd().getId() - 1] = edge.getWeight();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                writer.print(weightMatrix[i][j] + " ");
            }
            writer.println();
        }
    }
}

class Vertex {
    private int id;
    private List<Edge> edges;

    public Vertex(int id) {
        this.id = id;
        this.edges = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }
}

class Edge {
    private Vertex start;
    private Vertex end;
    private int weight;

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertex getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}
