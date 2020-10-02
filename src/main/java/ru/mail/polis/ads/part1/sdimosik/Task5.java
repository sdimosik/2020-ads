package ru.mail.polis.ads.part1.sdimosik;

import java.io.*;
import java.util.StringTokenizer;


public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Queue<Integer> stack = new Queue<>();
        String input = in.next();
        while (!input.equals("exit")) {
            switch (input) {
                case "push":
                    stack.push(in.nextInt());
                    out.write("ok" + '\n');
                    break;
                case "pop":
                    out.write(stack.pop() + "\n");
                    break;
                case "front":
                    out.write(stack.front() + "\n");
                    break;
                case "size":
                    out.write(stack.getSize() + "\n");
                    break;
                case "clear":
                    stack.clear();
                    out.write("ok\n");
                    break;
            }
            input = in.next();
        }
        out.write("bye\n");
        out.close();
    }

    private static class Queue<T> {
        private static class Node<T> {
            private final T data;
            private Node<T> next;

            Node(T data) {
                this.data = data;
            }
        }

        private Node<T> head, tail;
        private int size;

        public void push(T data) {
            Node<T> node = new Node<>(data);
            if (size == 0) {
                tail = head = node;
                ++size;
                return;
            }
            tail.next = node;
            tail = node;
            ++size;
        }

        public T pop() {
            T data = head.data;
            head = head.next;
            --size;
            return data;
        }

        public T front() {
            return head.data;
        }

        public int getSize() {
            return size;
        }

        public void clear() {
            head = tail = null;
            size = 0;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
