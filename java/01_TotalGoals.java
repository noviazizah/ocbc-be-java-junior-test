import java.util.Collection;

final class TotalGoals {

    private TotalGoals() {
    }

    public static int totalGoals(int[] goals) {
        if (goals == null) {
            return 0;
        }

        int total = 0;
        for (int goal : goals) {
            total += goal;
        }
        return total;
    }

    public static int totalGoals(Collection<Integer> goals) {
        if (goals == null) {
            return 0;
        }

        int total = 0;
        for (Integer goal : goals) {
            if (goal != null) {
                total += goal;
            }
        }
        return total;
    }
}
