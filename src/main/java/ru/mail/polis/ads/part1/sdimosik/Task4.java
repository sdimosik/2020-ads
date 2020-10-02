package ru.mail.polis.ads.part1.sdimosik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Stack<Integer> stack = new Stack<>();
        String input = in.next();
        while (!input.equals("exit")) {
            switch (input) {
                case "push":
                    stack.push(in.nextInt());
                    out.write("ok" + '\n');
                    break;
                case "pop":
                    if (stack.getSize() == 0) {
                        out.write("error\n");
                    } else {
                        out.write(stack.pop() + "\n");
                    }
                    break;
                case "back":
                    if (stack.getSize() == 0) {
                        out.write("error\n");
                    } else {
                        out.write(stack.back() + "\n");
                    }
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

    static class Stack<T> {
        private class Node {
            T data;
            Node next = null, prev = null;

            public Node(T data) {
                this.data = data;
            }
        }

        private Node head;
        private int size;

        public Stack() {
            head = null;
            size = 0;
        }

        public void push(T data) {
            Node node = new Node(data);

            if (head != null) {
                node.next = head;
                head.prev = node;
            }
            head = node;
            size++;
        }

        public T pop() {
            if (size == 0) {
                return null;
            }
            T temp = head.data;
            head = head.next;
            size--;
            return temp;
        }

        public T back() {
            if (size == 0) {
                return null;
            }
            return head.data;
        }

        public void clear() {
            head = null;
            size = 0;
        }

        public int getSize() {
            return size;
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
