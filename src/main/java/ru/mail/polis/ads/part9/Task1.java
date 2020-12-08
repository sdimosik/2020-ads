package ru.mail.polis.ads.part9;

import java.io.*;
import java.util.*;

public class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int vertex = in.nextInt(),
            edge = in.nextInt();
        Graph graph = new Graph(vertex);
        for (int i = 0; i < edge; i++) {
            graph.addEdge(in.nextInt() - 1, in.nextInt() - 1);
        }
        if (graph.hasLoop()) {
            System.out.print(-1);
            return;
        }
        graph.topologicalSort();
    }

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Graph {
        private final int vert;
        int cycle_start, cycle_end;
        private final ArrayList<ArrayList<Integer>> adj;

        Graph(int vert) {
            this.vert = vert;
            adj = new ArrayList<>(vert);
            for (int i = 0; i < vert; ++i)
                adj.add(new ArrayList<>());
        }

        void addEdge(int v, int w) {
            adj.get(v).add(w);
        }

        void topologicalSortUtil(int v, boolean[] visited,
                                 Stack<Integer> stack) {
            visited[v] = true;
            Integer i;

            for (Integer integer : adj.get(v)) {
                i = integer;
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);
            }

            stack.push(v);
        }

        void topologicalSort() {
            Stack<Integer> stack = new Stack<>();

            // Mark all the vertices as not visited
            boolean[] visited = new boolean[vert];
            for (int i = 0; i < vert; i++)
                visited[i] = false;

            // Call the recursive helper
            // function to store
            // Topological Sort starting
            // from all vertices one by one
            for (int i = 0; i < vert; i++)
                if (!visited[i])
                    topologicalSortUtil(i, visited, stack);

            // Print contents of stack
            while (!stack.empty())
                System.out.print(stack.pop() + 1 + " ");
        }

        boolean hasLoop() {
            int[] color = new int[vert];
            int[] parent = new int[vert];
            Arrays.fill(color, 0);
            Arrays.fill(parent, -1);
            cycle_start = -1;

            for (int v = 0; v < vert; v++) {
                if (color[v] == 0 && dfs(v, color, parent))
                    break;
            }

            return cycle_start != -1;
        }

        boolean dfs(int v, int[] color, int[] parent) {
            color[v] = 1;
            for (int u : adj.get(v)) {
                if (color[u] == 0) {
                    parent[u] = v;
                    if (dfs(u, color, parent))
                        return true;
                } else if (color[u] == 1) {
                    cycle_end = v;
                    cycle_start = u;
                    return true;
                }
            }
            color[v] = 2;
            return false;
        }
    }
}