package ru.mail.polis.ads.part9;

import java.io.*;
import java.util.*;

public class Task3 {
    private Task3() {
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
        Graph graph = new Graph(edge);
        for (int i = 0; i < edge; i++) {
            graph.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }

        int[] dist = new int[vertex + 1];
        Arrays.fill(dist, 30000);
        dist[1] = 0;

        while (true) {
            boolean isChanged = false;
            for (int i = 0; i < edge; i++) {
                Integer v1 = graph.edges[i].start;
                Integer v2 = graph.edges[i].end;
                Integer value = graph.edges[i].value;

                if (dist[v1] < 30000 && dist[v1] + value < dist[v2]) {
                    dist[v2] = dist[v1] + value;
                    isChanged = true;
                }
            }
            if (!isChanged) {
                break;
            }
        }

        for (int i = 1; i <= vertex; i++) {
            System.out.print(dist[i] + " ");
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
        static class Edge {
            Integer start;
            Integer end;
            Integer value;

            public Edge(Integer start, Integer end, Integer value) {
                this.start = start;
                this.end = end;
                this.value = value;
            }
        }

        private final Edge[] edges;
        private int size = 0;

        Graph(int size) {
            edges = new Edge[size + 1];
        }

        void addEdge(int s, int e, int v) {
            edges[size] = new Edge(s, e, v);
            ++size;
        }
    }
}
