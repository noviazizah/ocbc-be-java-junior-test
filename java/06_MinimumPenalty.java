import java.util.Arrays;

final class MinimumPenalty {

    private MinimumPenalty() {
    }

    public static long minimumPenalty(int[] values) {
        if (values == null || values.length <= 1) {
            return 0L;
        }

        Arrays.sort(values);
        long penalty = 0L;

        for (int index = 1; index < values.length; index++) {
            penalty += (long) values[index] - values[index - 1];
        }

        return penalty;
    }
}
