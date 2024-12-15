import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public abstract class Day {
    static protected List<String> getData(String name) {
        return getResourceLines("aocData/" + name);
    }

    @SuppressWarnings("unused")
    static protected List<String> getTestData(String name) {
        return getResourceLines("aocData/" + name + "-test");
    }

    static private List<String> getResourceLines(String resourceName) {
        try (InputStream inputStream = Day.class.getClassLoader().getResourceAsStream(resourceName)) {
            assert inputStream != null : "Resource \"" + resourceName + "\" should be opened to read";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            return bufferedReader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static protected void printPart1(String result) {
        System.out.println("============== < Part 1 > ==============");
        System.out.println(result);
        System.out.println("============== </Part 1 > ==============");
    }

    static protected void printPart2(String result) {
        System.out.println("============== < Part 2 > ==============");
        System.out.println(result);
        System.out.println("============== </Part 2 > ==============");
    }
}
