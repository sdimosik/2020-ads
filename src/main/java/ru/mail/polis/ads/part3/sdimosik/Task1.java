package ru.mail.polis.ads.part3.sdimosik;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

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
        int number = in.nextInt();
        int[] array = new int[number + 1];
        for(int i = 1; i < array.length; ++i) {
            array[i] = in.nextInt();
        }
        for (int i = 1; i < array.length; i++) {
            int left = 2 * i, right = 2 * i + 1;
            if (left < array.length && array[left] < array[i]) {
                out.write("NO\n");
                return;
            }
            if (right < array.length && array[right] < array[i]) {
                out.write("NO\n");
                return;
            }
            if (left >= array.length) {
                out.write("YES\n");
                return;
            }
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
}
