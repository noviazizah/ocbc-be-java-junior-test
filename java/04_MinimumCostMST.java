import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

final class MinimumCostMST {

    private MinimumCostMST() {
    }

    public static int getMinimumCostMST(int graphNodes, List<Integer> graphFrom, List<Integer> graphTo,
                                        List<Integer> graphWeight, int source, int destination) {
        if (source < 1 || destination < 1 || source > graphNodes || destination > graphNodes) {
            return -1;
        }
        if (source == destination) {
            return 0;
        }
        if (graphNodes <= 0 || graphFrom == null || graphTo == null || graphWeight == null) {
            return -1;
        }

        int edgeCount = Math.min(graphFrom.size(), Math.min(graphTo.size(), graphWeight.size()));
        List<Edge> edges = new ArrayList<>(edgeCount);
        for (int index = 0; index < edgeCount; index++) {
            edges.add(new Edge(graphFrom.get(index), graphTo.get(index), graphWeight.get(index)));
        }
        edges.sort(Comparator.comparingInt(edge -> edge.weight));

        List<List<PathEdge>> mst = new ArrayList<>(graphNodes + 1);
        for (int node = 0; node <= graphNodes; node++) {
            mst.add(new ArrayList<>());
        }

        UnionFind unionFind = new UnionFind(graphNodes + 1);
        for (Edge edge : edges) {
            if (unionFind.union(edge.from, edge.to)) {
                mst.get(edge.from).add(new PathEdge(edge.to, edge.weight));
                mst.get(edge.to).add(new PathEdge(edge.from, edge.weight));
            }
        }

        return findPathCost(mst, source, destination);
    }

    private static int findPathCost(List<List<PathEdge>> mst, int source, int destination) {
        boolean[] visited = new boolean[mst.size()];
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(source, 0));
        visited[source] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.node == destination) {
                return current.cost;
            }

            for (PathEdge next : mst.get(current.node)) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    queue.offer(new State(next.to, current.cost + next.weight));
                }
            }
        }

        return -1;
    }

    private static final class Edge {
        private final int from;
        private final int to;
        private final int weight;

        private Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static final class PathEdge {
        private final int to;
        private final int weight;

        private PathEdge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static final class State {
        private final int node;
        private final int cost;

        private State(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    private static final class UnionFind {
        private final int[] parent;
        private final int[] rank;

        private UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int index = 0; index < size; index++) {
                parent[index] = index;
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
