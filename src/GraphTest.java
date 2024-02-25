import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.nio.file.*;
import org.junit.Test;

public class GraphTest {
    @Test
    public void testPrintWeightMatrix() {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 2);
        g.addEdge(1, 2, 3);
        g.addEdge(2, 0, 1);

        String fileName = "src/OutputTest.txt";
        g.printWeightMatrix(fileName);

        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            String expectedContent = "0 2 0 \n0 0 3 \n1 0 0 \n";
            expectedContent = expectedContent.replace("\n", System.lineSeparator());

            assertEquals(expectedContent, content);
        } catch (IOException e) {
            fail("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}
