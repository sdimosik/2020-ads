package ru.mail.polis.ads.part4.sdimosik;

import java.io.*;
import java.util.StringTokenizer;

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
        int i, a, k;
        int j = 0;
        int[] array = new int[1002];
        int max;
        a = in.nextInt();
        for (i = 1; i <= a; i++) {
            array[i] = in.nextInt();
        }
        k = in.nextInt();
        array[0] = 0;
        array[a + 1] = 0;
        for (i = 1; i <= a + 1; i++) {
            max = array[j];
            for (j = i - k; j < i; j++) {
                if (j < 0) j = 0;
                if (max < array[j]) max = array[j];
            }
            array[i] = max + array[i];
        }
        out.print(array[a + 1]);
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
