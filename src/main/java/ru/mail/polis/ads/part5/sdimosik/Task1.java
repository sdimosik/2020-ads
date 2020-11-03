package ru.mail.polis.ads.part5.sdimosik;

import java.io.*;
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
        final double ACCURACY = 0.0000001;
        final double num = Double.parseDouble(in.next());
        double left = 0, right = num;
        double c = 0;

        while (Math.abs(num - c) > ACCURACY) {
            double middle = (left + right) / 2;
            c = Math.pow(middle, 2) + Math.sqrt(middle);
            if (c > num) {
                right = middle;
            } else {
                left = middle;
            }
        }
        out.println(left);
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
