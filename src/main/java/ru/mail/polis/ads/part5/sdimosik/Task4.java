package ru.mail.polis.ads.part5.sdimosik;

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

    private static boolean[][] getDynamicMatrix(String word, String pattern) {
        boolean[][] dynamic = new boolean[pattern.length() + 1][word.length() + 1];
        dynamic[0][0] = true;

        for (int i = 1; i <= pattern.length(); i++) {
            for (int j = 1; j <= word.length(); j++) {
                if (pattern.charAt(i - 1) == word.charAt(j - 1) || pattern.charAt(i - 1) == '?') {
                    dynamic[i][j] = dynamic[i - 1][j - 1];
                } else if (pattern.charAt(i - 1) == '*') {
                    dynamic[i][j] = dynamic[i - 1][j - 1] || dynamic[i][j - 1] || dynamic[i - 1][j];
                } else {
                    dynamic[i][j] = false;
                }
            }
        }
        return dynamic;
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String str1 = in.next();
        String str2 = in.next();
        if (str1.equals(str2)) {
            out.print("YES");
            return;
        }

        String word, pattern;
        if (str2.contains("?") || str2.contains("*")) {
            word = str1;
            pattern = str2;
        } else {
            word = str2;
            pattern = str1;
        }
        boolean[][] dynamic = getDynamicMatrix(word, pattern);

        System.out.print(dynamic[pattern.length()][word.length()] ? "YES" : "NO");
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
