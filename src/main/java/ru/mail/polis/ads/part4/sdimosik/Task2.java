package ru.mail.polis.ads.part4.sdimosik;

import java.io.*;
import java.util.StringTokenizer;

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
        /*
        3 2 4
        1 5 1

        1 5 1
        3 2 4
         */
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] map = new long[100][100];
        StringBuilder ans = new StringBuilder("");
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) map[i][j] = in.nextInt();
        }
        for (int i = 1; i < n; i++) {
            map[i][0] += map[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            map[0][j] += map[0][j - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                map[i][j] += Math.max(map[i - 1][j], map[i][j - 1]);
            }
        }
        /*System.out.println("----------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j]);
                if (map[i][j] >= 10) {
                    System.out.print(" ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }*/
        int k = n - 1, t = m - 1;
        while (k > 0 || t > 0) {
            if (k > 0 && t > 0) {
                if (map[k - 1][t] > map[k][t - 1]) {
                    ans.append("F");
                    k--;
                } else {
                    ans.append("R");
                    t--;
                }
            } else if (k == 0) {
                ans.append("R");
                t--;
            } else if (t == 0) {
                ans.append("F");
                k--;
            }
        }
        String reverse = new StringBuffer(ans.toString()).reverse().toString();
        out.println(reverse);
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
