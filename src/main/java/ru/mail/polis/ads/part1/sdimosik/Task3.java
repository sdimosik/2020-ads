package ru.mail.polis.ads.part1.sdimosik;

import java.io.*;
import java.util.EmptyStackException;
import java.util.StringTokenizer;

public class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Stack<Character> stack = new Stack<>();
        String input = in.next();
        try {
            for (char ch : input.toCharArray()) {
                if (ch == '(') {
                    stack.push('(');
                } else if (ch == ')') {
                    if (stack.back() != '(') {
                        out.write("NO");
                        return;
                    }
                    stack.pop();
                }
            }
        } catch (EmptyStackException exception) {
            out.write("NO");
            return;
        }
        if (!stack.isEmpty()) {
            out.write("NO");
        } else {
            out.write("YES");
        }
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

        public T pop() throws EmptyStackException {
            if (size == 0) {
                throw new EmptyStackException();
            }
            T temp = head.data;
            head = head.next;
            size--;
            return temp;
        }

        public T back() throws EmptyStackException {
            if (size == 0) {
                throw new EmptyStackException();
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

        public boolean isEmpty() {
            return size == 0;
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
