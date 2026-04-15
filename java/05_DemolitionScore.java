import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class DemolitionScore {

    private DemolitionScore() {
    }

    public static long getDemolitionScore(List<Integer> arr, int k) {
        Map<String, Long> memo = new HashMap<>();
        long result = solve(arr, k, memo);
        return result < 0 ? 0L : result;
    }

    private static long solve(List<Integer> arr, int remainingMoves, Map<String, Long> memo) {
        if (remainingMoves == 0) {
            return 0L;
        }
        if (arr == null || arr.isEmpty()) {
            return Long.MIN_VALUE;
        }

        String key = remainingMoves + "|" + arr;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        long best = Long.MIN_VALUE;
        for (int index = 0; index < arr.size(); index++) {
            int demolishedValue = arr.get(index);
            List<Integer> nextArray = buildNextArray(arr, index, demolishedValue);
            long nextScore = solve(nextArray, remainingMoves - 1, memo);

            if (nextScore != Long.MIN_VALUE) {
                best = Math.max(best, demolishedValue + nextScore);
            }
        }

        memo.put(key, best);
        return best;
    }

    private static List<Integer> buildNextArray(List<Integer> arr, int demolishedIndex, int demolishedValue) {
        int leftSize = demolishedIndex;
        int rightSize = arr.size() - demolishedIndex - 1;
        int start;
        int end;

        if (leftSize > rightSize) {
            start = 0;
            end = demolishedIndex;
        } else {
            start = demolishedIndex + 1;
            end = arr.size();
        }

        List<Integer> next = new ArrayList<>(Math.max(0, end - start));
        for (int index = start; index < end; index++) {
            next.add(Math.max(0, arr.get(index) - demolishedValue));
        }
        return next;
    }
}
