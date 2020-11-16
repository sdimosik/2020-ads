package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node root;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp < 0) return get(node.left, key);
        if (comp > 0) return get(node.right, key);
        return node;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, RED);
        int comp = key.compareTo(x.key);

        if (comp < 0) x.left = put(x.left, key, value);
        else if (comp > 0) x.right = put(x.right, key, value);
        else x.value = value;

        return fixUp(x);
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) x = flipColors(x);
        return x;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        return node;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value node = get(key);
        if (node == null) {
            return null;
        }
        root.color = RED;
        root = delete(root, key);
        return node;
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        float compare = key.compareTo(x.key);
        if (compare < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) x = moveRedLeft(x);
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (compare == 0 && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) x = moveRedRight(x);
                if (key.compareTo(x.key) == 0) {
                    Node min = min(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = delete(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (!isRed(x.left.left)) x = rotateRight(x);
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;
        if (!isRed(x.left) && !isRed(x.left.left)) x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    @Nullable
    @Override
    public Key min() {
        Node node = min(root);
        return node == null ? null : node.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node node = min(root);
        return node == null ? null : node.value;
    }

    private Node min(Node node) {
        if (node == null) return null;
        if (node.left == null) return node;
        return min(node.left);
    }

    @Nullable
    @Override
    public Key max() {
        Node node = max(root);
        return node == null ? null : node.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node node = max(root);
        return node == null ? null : node.value;
    }

    private Node max(Node node) {
        if (node == null) return null;
        if (node.right == null) return node;
        return max(node.right);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node node = floor(root, key);
        return node == null ? null : node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp == 0) return node;

        if (comp < 0) return floor(node.left, key);

        Node right = floor(node.right, key);

        return right == null ? node : right;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(root, key);
        return node == null ? null : node.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null)
            return null;

        if (key == node.key) {
            return node;
        }

        if (key.compareTo(node.key) > 0) return ceil(node.right, key);

        Node left = ceil(node.left, key);

        return left == null ? node : left;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }
}
