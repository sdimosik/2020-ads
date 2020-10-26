package ru.mail.polis.ads.part4.sdimosik;

import java.io.*;
import java.util.StringTokenizer;

public class Task1 {
    private static int[][] d;
    private static int[][] e;

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

    private static boolean isCorrect(Character left, Character right) {
        return (left == '(' && right == ')')
                || (left == '[' && right == ']');
    }

    private static String rec(String s, int l, int r) {
        if (l == r) {
            return s.charAt(l) == '(' || s.charAt(r) == ')' ? "()" : "[]";
        }

        if (e[l][r] == -1) {
            return s.charAt(l) + rec(s, l + 1, r - 1) + s.charAt(r);
        }
        if (d[l][r] == 0) {
            return s.substring(l, r + 1);
        }

        return rec(s, l, e[l][r]) + rec(s, e[l][r] + 1, r);
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String s;
        s = in.next();
        d = new int[s.length()][s.length()];
        e = new int[s.length()][s.length()];

        for (int j = 0; j < s.length(); j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    d[i][j] = 1;
                    continue;
                }

                e[i][j] = i;
                int min = Integer.MAX_VALUE;

                if (isCorrect(s.charAt(i), s.charAt(j))) {
                    min = d[i + 1][j - 1];
                    e[i][j] = -1;
                }
                for (int k = i; k < j; k++) {
                    if (d[i][k] + d[k + 1][j] < min) {
                        min = d[i][k] + d[k + 1][j];
                        e[i][j] = k;
                    }
                }
                d[i][j] = min;
            }
        }
        String stsd = rec(s, 0, d.length - 1);
        System.out.println(stsd);
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
