package ru.mail.polis.ads.part9;

import java.io.*;
import java.util.*;

public class Task4 {
    private Task4() {
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
        int start = in.nextInt(),
            end = in.nextInt();
        Graph graph = new Graph(vertex, start);
        for (int i = 0; i < edge; i++) {
            graph.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        graph.doWork();
        graph.printResult(start, end);
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
        static class Edge {
            Integer start;
            Integer value;

            public Edge(Integer start, Integer value) {
                this.start = start;
                this.value = value;
            }
        }

        static class Vertex implements Comparable<Vertex> {
            Integer vertex;
            Integer dist;

            public Vertex(Integer vertex, Integer dist) {
                this.vertex = vertex;
                this.dist = dist;
            }

            @Override
            public int compareTo(Vertex o) {
                return Integer.compare(this.dist, o.dist);
            }
        }

        private final ArrayList<ArrayList<Edge>> list;
        private final int[] dist;
        private final int[] parent;
        private final Queue<Vertex> queue;

        Graph(int size, int start) {
            list = new ArrayList<>();
            for (int i = 0; i <= size; i++) list.add(new ArrayList<>());
            dist = new int[size + 1];
            parent = new int[size + 1];
            Arrays.fill(dist, -1);
            Arrays.fill(parent, -1);
            dist[start] = 0;
            queue = new PriorityQueue<>();
            queue.add(new Vertex(start, 0));
        }

        void addEdge(int s, int e, int v) {
            list.get(s).add(new Edge(e, v));
            list.get(e).add(new Edge(s, v));
        }

        void doWork() {
            while (!queue.isEmpty()) {
                Vertex current = queue.remove();
                if (current.dist > dist[current.vertex]) {
                    continue;
                }
                for (Edge next : list.get(current.vertex)) {
                    if (dist[next.start] == -1 || dist[next.start] > current.dist + next.value) {
                        dist[next.start] = current.dist + next.value;
                        queue.add(new Vertex(next.start, dist[next.start]));
                        parent[next.start] = current.vertex;
                    }
                }
            }
        }

        void printResult(int start, int end) {
            if (dist[end] == -1) {
                System.out.println(-1);
                return;
            }
            System.out.println(dist[end]);
            ArrayList<Integer> path = new ArrayList<>();
            int current = end;
            while (current != start) {
                path.add(current);
                current = parent[current];
            }
            path.add(start);
            Collections.reverse(path);
            for (int vertex : path) {
                System.out.print(vertex + " ");
            }
        }
    }
}
