package ru.mail.polis.ads.part2.sdimosik;

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

    private static void InsertionSort(int[] arr) {
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i - step; j >= 0 && ((arr[j] % 10 > arr[j + step] % 10) || (arr[j] % 10 == arr[j + step] % 10 && arr[j] > arr[j + step])); j -= step) {
                    int num = arr[j];
                    arr[j] = arr[j + step];
                    arr[j + step] = num;
                }
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int num = in.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = in.nextInt();
        }
        InsertionSort(arr);
        for (int i = 0; i < num; i++) {
            out.write(arr[i] + " ");
        }
        out.close();
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

