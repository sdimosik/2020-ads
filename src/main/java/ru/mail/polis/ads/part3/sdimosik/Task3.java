package ru.mail.polis.ads.part3.sdimosik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(Integer.MAX_VALUE);
        maxHeap.offer(Integer.MIN_VALUE);
        int mid = -1;
        int num;
        while (true) {
            num = in.nextInt();
            if (mid == -1 && maxHeap.size() != 0 && minHeap.size() != 0) {
                if (num < maxHeap.peek()) {
                    mid = maxHeap.poll();
                    maxHeap.offer(num);
                } else if (num > minHeap.peek()) {
                    mid = minHeap.poll();
                    minHeap.offer(num);
                } else {
                    mid = num;
                }
            } else {
                if (num < mid) {
                    minHeap.offer(mid);
                    maxHeap.offer(num);
                } else {
                    maxHeap.offer(mid);
                    minHeap.offer(num);
                }
                mid = -1;
            }
            if (maxHeap.size() != 0 && minHeap.size() != 0) {
                out.println(mid == -1 ? (maxHeap.peek() + (minHeap.peek() - maxHeap.peek()) / 2) : mid);
            }
        }
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
