import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends Day {
    public static void main(String[] args) {
        String data = String.join("", getData("day3"));
        Pattern mulPattern = Pattern.compile("mul\\((?<first>\\d{1,3}),(?<second>\\d{1,3})\\)");
        Matcher mulMatcher = mulPattern.matcher(data);

        int result1 = mulMatcher.results()
                .mapToInt(matchResult -> {
                    int first = Integer.parseInt(matchResult.group("first"));
                    int second = Integer.parseInt(matchResult.group("second"));
                    return first * second;
                })
                .sum();

        printPart1(String.valueOf(result1));


        Pattern omniPattern = Pattern.compile("(mul\\((?<first>\\d{1,3}),(?<second>\\d{1,3})\\))|(do\\(\\))|(don't\\(\\))");
        Matcher omniMatcher = omniPattern.matcher(data);
        List<MatchResult> matches = omniMatcher.results().toList();

        int result2 = 0;
        boolean isEnabled = true;
        for (MatchResult match : matches) {
            String instruction = match.group().split("\\(")[0];
            switch (instruction) {
                case "mul" -> {
                    if (isEnabled) {
                        int first = Integer.parseInt(match.group("first"));
                        int second = Integer.parseInt(match.group("second"));
                        result2 += first * second;
                    }
                }
                case "do" -> isEnabled = true;
                case "don't" -> isEnabled = false;
                default -> throw new RuntimeException("unknown instruction processed: \"" + instruction + "\"");
            }
        }

        printPart2(String.valueOf(result2));
    }
}
