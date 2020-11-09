package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;
    private Value tmpValue;
    private int size = 0;

    public AvlBst() {
        this.root = null;
    }

    public AvlBst(Node node) {
        this.root = node;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = findNodeByKey(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = insert(root, key, value);
        size++;
    }

    @Override
    public Value remove(@NotNull Key key) {
        root = removeQ(root, key);
        size--;
        return tmpValue;
    }

    @Override
    public Key min() {
        Node node = findMin(root);
        return node != null ? node.key : null;
    }

    @Override
    public Value minValue() {
        Node node = findMin(root);
        return node != null ? node.value : null;
    }

    @Override
    public Key max() {
        Node node = findMax(root);
        return node != null ? node.key : null;
    }

    @Override
    public Value maxValue() {
        Node node = findMax(root);
        return node != null ? node.value : null;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node node = root;

        while (node != null) {
            if (key.equals(node.key)) {
                return node.left == null ? node.key : findMax(node.left).key;
            }

            if (lessThen(key, node.key)) {
                if (node.left == null) {
                    return null;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    return node.key;
                }
                if (!lessThen(node.right.key, key) && !key.equals(node.right.key)) {
                    return node.key;
                }
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node node = root;

        while (node != null) {
            if (key.equals(node.key)) {
                return node.key;
            }

            if (lessThen(key, node.key)) {
                if (node.left == null) {
                    return node.key;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    return lessThen(node.key, key) ? null : node.key;
                }
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root == null ? 0 : root.height;
    }

    private Node findNodeByKey(Node node, Key key) {
        Node nodeTmp = node;
        while (nodeTmp != null) {
            if (nodeTmp.key.equals(key)) {
                return nodeTmp;
            }
            if (lessThen(key, nodeTmp.key)) {
                nodeTmp = nodeTmp.left;
            } else {
                nodeTmp = nodeTmp.right;
            }
        }
        return null;
    }

    private boolean lessThen(Key key1, Key key2) {
        return key1.compareTo(key2) < 0;
    }

    private int heightJust(Node node) {
        return node == null ? 0 : node.height;
    }

    int bFactor(Node node) {
        return heightJust(node.right) - heightJust(node.left);
    }

    private void fixHeight(Node node) {
        int left = heightJust(node.left);
        int right = heightJust(node.right);
        node.height = Math.max(left, right) + 1;
    }

    private Node rotateR(Node node) {
        Node tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        fixHeight(node);
        fixHeight(tmp);
        return tmp;
    }

    private Node rotateL(Node node) {
        Node tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        fixHeight(node);
        fixHeight(tmp);
        return tmp;
    }

    private Node balance(Node node) {
        fixHeight(node);
        if (bFactor(node) == 2) {
            if (bFactor(node.right) < 0) {
                node.right = rotateR(node.right);
            }
            return rotateL(node);
        }
        if (bFactor(node) == -2) {
            if (bFactor(node.left) > 0) {
                node.left = rotateL(node.left);
            }
            return rotateR(node);
        }
        return node;
    }

    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }
        if (lessThen(key, node.key)) {
            node.left = insert(node.left, key, value);
        } else if (lessThen(node.key, key)) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
            size--;
        }
        return balance(node);
    }

    private Node findMin(Node node) {
        if (node == null) {
            return null;
        }
        return node.left != null ? findMin(node.left) : node;
    }

    private Node findMax(Node node) {
        if (node == null) {
            return null;
        }
        return node.right != null ? findMax(node.right) : node;
    }

    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return balance(node);
    }

    private Node removeQ(Node node, Key key) {
        if (node == null) {
            size++;
            tmpValue = null;
            return null;
        }
        if (lessThen(key, node.key)) {
            node.left = removeQ(node.left, key);
        } else if (lessThen(node.key, key)) {
            node.right = removeQ(node.right, key);
        } else {
            tmpValue = node.value;
            Node left = node.left;
            Node right = node.right;
            if (right == null) {
                return left;
            }
            Node min = findMin(right);
            min.right = removeMin(right);
            min.left = left;
            return balance(min);
        }
        return balance(node);
    }
}
