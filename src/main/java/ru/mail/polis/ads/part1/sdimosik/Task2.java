package ru.mail.polis.ads.part1.sdimosik;

import java.io.*;
import java.util.*;
import java.lang.*;

public class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int count = Integer.parseInt(in.next());
        for (int i = 0; i < count; ++i) {
            String str = in.next();
            Stack<Node> stack = new Stack<>();
            for (char symbol : str.toCharArray()) {
                if (Character.isLowerCase(symbol)) {
                    stack.push(new Node(symbol, null, null));
                } else {
                    Node left = stack.pop();
                    Node right = stack.pop();
                    stack.push(new Node(symbol, left, right));
                }
            }
            out.write(levelOrder(stack.pop()) + '\n');
            out.flush();
        }
        out.close();
    }

    static class Node {
        private char symbol;
        private Node left, right;

        public Node(char ch, Node left, Node right) {
            this.symbol = ch;
            this.left = left;
            this.right = right;
        }
    }

    public static String levelOrder(Node node) {
        LinkedList<Node> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder("");
        queue.addFirst(node);
        while (queue.size() != 0) {
            Node temp = queue.pollFirst();
            if (temp.right != null) {
                queue.addLast(temp.right);
            }
            if (temp.left != null) {
                queue.addLast(temp.left);
            }
            result.insert(0, temp.symbol);
        }
        return result.toString();
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
