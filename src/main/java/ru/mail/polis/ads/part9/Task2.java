package ru.mail.polis.ads.part9;

import java.io.*;
import java.util.*;

public class Task2 {
    private Task2() {
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
            graph.addEdge(in.nextInt(), in.nextInt());
        }
        graph.findCycles();
        if (graph.min == Integer.MAX_VALUE) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            System.out.println(graph.min);
        }
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
        int min = Integer.MAX_VALUE;
        private final HashMap<Integer, ArrayList<Integer>> map;
        private final HashMap<Integer, Status> visited;
        private final HashMap<Integer, Integer> parent;
        private final HashMap<Integer, Boolean> cycle;

        Graph(int size) {
            map = new HashMap<>(size);
            visited = new HashMap<>(size);
            parent = new HashMap<>(size);
            cycle = new HashMap<>(size);
        }

        void addEdge(int v, int w) {
            if (!map.containsKey(v))
                map.put(v, new ArrayList<>());

            if (!map.containsKey(w))
                map.put(w, new ArrayList<>());

            map.get(w).add(v);
            map.get(v).add(w);
        }

        enum Status {
            NOT_CHECKED(),
            CHECKED(),
            DONE();

            Status() {
            }
        }

        private void dfs(int vertex) {
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.addFirst(vertex);

            while (!queue.isEmpty()) {
                int start = queue.peek();
                visited.put(start, Status.CHECKED);
                boolean newChild = false;

                for (Integer end : map.get(start)) {
                    if (!parent.get(start).equals(end) && visited.get(end).equals(Status.NOT_CHECKED)) {
                        parent.put(end, start);
                        queue.addFirst(end);
                        newChild = true;
                        break;
                    } else if (!parent.get(start).equals(end) && visited.get(end).equals(Status.CHECKED)) {
                        cycle.put(end, true);
                        int u = start;
                        if (!cycle.get(start)) {
                            while (u != end) {
                                cycle.put(u, true);
                                u = parent.get(u);
                                if (cycle.get(u)) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!newChild) {
                    visited.put(start, Status.DONE);
                    queue.removeFirst();
                }
            }
        }

        void findCycles() {
            for (Integer vertex : map.keySet()) {
                visited.put(vertex, Status.NOT_CHECKED);
                parent.put(vertex, 0);
                cycle.put(vertex, false);
            }
            for (Integer vertex : map.keySet()) {
                if (visited.get(vertex).equals(Status.NOT_CHECKED)) {
                    dfs(vertex);
                }
            }
            for (Integer vertex : map.keySet()) {
                if (cycle.get(vertex) && vertex < min) {
                    min = vertex;
                }
            }
        }
    }
}