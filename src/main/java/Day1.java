import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1 extends Day {
    public static void main(String[] args) {
        List<String> day1 = getData("day1");
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        day1.stream()
                .map(line -> line.split(" +"))
                .map(strings -> Arrays.stream(strings).mapToInt(Integer::valueOf).toArray())
                .forEach(ints -> {
                    a.add(ints[0]);
                    b.add(ints[1]);
                });
        assert a.size() == b.size();

        a.sort(Integer::compare);
        b.sort(Integer::compare);

        int result1 = 0;
        for (int i = 0; i < a.size(); i++) {
            result1 += Math.abs(a.get(i) - b.get(i));
        }
        printPart1(String.valueOf(result1));

        int result2 = 0;
        for (Integer number : a) {
            result2 += (int) (number * b.stream().filter(integer -> integer.equals(number)).count());
        }
        printPart2(String.valueOf(result2));
    }
}
