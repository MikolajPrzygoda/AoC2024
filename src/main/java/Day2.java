import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Day2 extends Day {
    public static void main(String[] args) {
        List<String> data = getData("day2");
        List<List<Integer>> reports = data.stream()
                .map(strReport -> Arrays.stream(strReport.split(" "))
                        .map(Integer::valueOf)
                        .toList()
                )
                .toList();

        int safeCount1 = 0;
        int safeCount2 = 0;
        for (List<Integer> report : reports) {
            if (isReportSafe(report)) {
                safeCount1++;
                safeCount2++;
            } else {
                for (int ignoredIndex = 0; ignoredIndex < report.size(); ignoredIndex++) {
                    if (isReportSafe(report, ignoredIndex)) {
                        safeCount2++;
                        break;
                    }
                }
            }
        }

        printPart1(String.valueOf(safeCount1));
        printPart2(String.valueOf(safeCount2));
    }

    private static boolean isReportSafe(List<Integer> report) {
        return isReportSafe(report, -1);
    }

    private static boolean isReportSafe(List<Integer> report, int ignoreIndex) {
        Optional<Boolean> isRising = Optional.empty();

        for (int i = 0; i < report.size() - 1; i++) {
            if (i == ignoreIndex) {
                continue;
            }

            int nextIndex = i + 1 != ignoreIndex ? i + 1 : i + 2;
            if (nextIndex == report.size()) {
                continue;
            }

            int n = report.get(i);
            int nextN = report.get(nextIndex);

            if (n < nextN) {
                if (isRising.isPresent() && isRising.get().equals(Boolean.FALSE)) {
                    return false;
                }
                if (isRising.isEmpty()) {
                    isRising = Optional.of(Boolean.TRUE);
                }
            } else if (n > nextN) {
                if (isRising.isPresent() && isRising.get().equals(Boolean.TRUE)) {
                    return false;
                }
                if (isRising.isEmpty()) {
                    isRising = Optional.of(Boolean.FALSE);
                }
            }

            int diff = Math.abs(n - nextN);
            if (diff == 0 || diff > 3) {
                return false;
            }
        }

        return true;
    }
}
