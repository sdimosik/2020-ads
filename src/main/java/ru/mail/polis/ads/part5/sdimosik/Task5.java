package ru.mail.polis.ads.part5.sdimosik;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task5 {
    private Task5() {
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

    private static int getIndexOfTail(List<Integer> permutation) {
        for (int itemNum = permutation.size() - 1; itemNum > 0; itemNum--) {
            if (permutation.get(itemNum - 1) < permutation.get(itemNum)) {
                return itemNum;
            }
        }
        return 0;
    }

    private static int getIndexOfFirstMaxTailItem(int compareIndex, List<Integer> permutation) {
        for (int itemNum = permutation.size() - 1; itemNum > 0; itemNum--) {
            if (permutation.get(compareIndex) < permutation.get(itemNum)) {
                return itemNum;
            }
        }
        return 0;
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int numOfItems = in.nextInt();
        List<Integer> permutation = IntStream.rangeClosed(1, numOfItems)
                .boxed()
                .collect(Collectors.toList());

        out.println(permutation.stream()
                .map(item -> item + " ")
                .collect(Collectors.joining()));

        while (getIndexOfTail(permutation) != 0) {
            int indexOfTail = getIndexOfTail(permutation);
            int indexOfFirstMaxTailItem = getIndexOfFirstMaxTailItem(indexOfTail - 1, permutation);
            Collections.swap(permutation, indexOfTail - 1, indexOfFirstMaxTailItem);
            List<Integer> tail = permutation.subList(indexOfTail, permutation.size());
            Collections.reverse(tail);
            for (int permNum = indexOfTail, tailNum = 0;
                 permNum < permutation.size();
                 permNum++, tailNum++) {
                permutation.set(permNum, tail.get(tailNum));
            }
            out.println(permutation.stream()
                    .map(item -> item + " ")
                    .collect(Collectors.joining()));
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
