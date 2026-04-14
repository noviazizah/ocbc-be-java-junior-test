final class DemolitionScore {

    private DemolitionScore() {
    }

    public static int demolitionScore(int[] blocks) {
        if (blocks == null || blocks.length == 0) {
            return 0;
        }

        int best = Integer.MIN_VALUE;
        int current = 0;

        for (int block : blocks) {
            current = Math.max(block, current + block);
            best = Math.max(best, current);
        }

        return best;
    }
}
