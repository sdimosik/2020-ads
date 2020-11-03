package ru.mail.polis.ads.part5.sdimosik;

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

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int length = in.nextInt();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }

        int[] dynamic = new int[length];
        for (int i = 0; i < array.length; ++i) {
            int max = 1;
            for (int j = 0; j < i; ++j) {
                if (array[j] == 0) continue;
                if (array[i] % array[j] == 0 && dynamic[j] >= max) {
                    max = dynamic[j] + 1;
                }
            }
            dynamic[i] = max;
        }

        int max = 1;
        for (int item : dynamic) {
            if (max < item) {
                max = item;
            }
        }
        out.print(max);
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
