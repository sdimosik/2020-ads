package ru.mail.polis.ads.part3.sdimosik;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static boolean isContain(int[] array, int num) {
        int left = 0;
        int right = array.length - 1;
        int mid = (right - left) / 2;

        while (left < right) {
            if (num > array[mid]) {
                left = mid + 1;
            } else if (num < array[mid]) {
                right = mid - 1;
            } else {
                return true;
            }
            mid = (right - left) / 2 + left;
        }

        return num == array[left];
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
        int sizeArr = in.nextInt();
        int numCommand = in.nextInt();
        int[] array = new int[sizeArr];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }

        for (int i = 0; i < numCommand; i++) {
            if (isContain(array, in.nextInt())) {
                out.write("YES\n");
            }
            else {
                out.write("NO\n");
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
