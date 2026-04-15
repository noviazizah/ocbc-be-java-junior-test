import java.util.List;
import java.util.PriorityQueue;

final class MinimumPenalty {

    private MinimumPenalty() {
    }

    public static long getMinimumPenalty(List<Integer> quantity, int m) {
        if (quantity == null || quantity.isEmpty() || m <= 0) {
            return 0L;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Integer count : quantity) {
            if (count != null && count > 0) {
                minHeap.offer(count);
            }
        }

        long totalPenalty = 0L;
        for (int sold = 0; sold < m && !minHeap.isEmpty(); sold++) {
            int current = minHeap.poll();
            totalPenalty += current;
            if (current > 1) {
                minHeap.offer(current - 1);
            }
        }

        return totalPenalty;
    }
}
