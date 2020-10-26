package ru.mail.polis.ads.part4.sdimosik;

import java.io.*;
import java.util.StringTokenizer;

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

    private static int findMaxLength(int[] array, int[] array1, int size, int size1) {
        int[][] result = new int[size + 1][size1 + 1];
        for (int i = 1; i <= size; ++i) {
            for (int j = 1; j <= size1; ++j) {
                if ((array[i - 1] == array1[j - 1])) {
                    result[i][j] = 1 + result[i - 1][j - 1];
                } else {
                    result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]);
                }
            }
        }
        return result[size][size1];
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int size, size1;
        size = in.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = in.nextInt();
        }
        size1 = in.nextInt();
        int[] array1 = new int[size1];
        for (int i = 0; i < size1; ++i) {
            array1[i] = in.nextInt();
        }
        out.println(findMaxLength(array, array1, size, size1));
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
