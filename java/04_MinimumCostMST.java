import java.util.Arrays;

final class MinimumCostMST {

    private MinimumCostMST() {
    }

    public static int minimumCost(int n, int[][] edges) {
        if (n <= 1) {
            return 0;
        }
        if (edges == null || edges.length == 0) {
            return -1;
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        UnionFind unionFind = new UnionFind(n + 1);
        int totalCost = 0;
        int usedEdges = 0;

        for (int[] edge : edges) {
            if (edge == null || edge.length < 3) {
                continue;
            }

            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            if (unionFind.union(from, to)) {
                totalCost += cost;
                usedEdges++;
                if (usedEdges == n - 1) {
                    return totalCost;
                }
            }
        }

        return -1;
    }

    private static final class UnionFind {
        private final int[] parent;
        private final int[] rank;

        private UnionFind(int size) {
            this.parent = new int[size];
            this.rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        private int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        private boolean union(int first, int second) {
            int rootFirst = find(first);
            int rootSecond = find(second);

            if (rootFirst == rootSecond) {
                return false;
            }

            if (rank[rootFirst] < rank[rootSecond]) {
                parent[rootFirst] = rootSecond;
            } else if (rank[rootFirst] > rank[rootSecond]) {
                parent[rootSecond] = rootFirst;
            } else {
                parent[rootSecond] = rootFirst;
                rank[rootFirst]++;
            }

            return true;
        }
    }
}
