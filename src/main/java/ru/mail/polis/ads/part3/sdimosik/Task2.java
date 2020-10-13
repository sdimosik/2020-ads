package ru.mail.polis.ads.part3.sdimosik;

import java.io.*;
import java.util.StringTokenizer;

public class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static class Heap {
        private final int[] data;
        private int size;

        public Heap(int size) {
            data = new int[size];
            this.size = 0;
        }

        public int getMax() {
            int result = data[1];
            swap(data, 1, size--);
            siftDown(1);
            return result;
        }

        public void insert(int x) {
            size++;
            data[size] = x;
            siftUp(size);
        }

        private void siftDown(int i) {
            while (2 * i <= size) {
                int left = 2 * i;
                int right = 2 * i + 1;
                int j = left;
                if (left < size && data[left] < data[right]) {
                    j++;
                }
                if (data[i] >= data[j]) {
                    break;
                }
                swap(data, i, j);
                i = j;
            }
        }

        private void siftUp(int i) {
            while (i > 1 && data[i] > data[i / 2]) {     // i  0 — мы в корне
                swap(data, i, i / 2);
                i = i / 2;
            }
        }

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
        Heap heap = new Heap(100000);
        int number = in.nextInt();
        for (int i = 0; i < number; ++i) {
            int command = in.nextInt();
            if (command == 0) {
                heap.insert(in.nextInt());
            } else if (command == 1) {
                out.write(heap.getMax() + "\n");
            } else {
                break;
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
