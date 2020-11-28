package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Hash<Key, Value> implements HashTable<Key, Value> {
    private static final float LOAD_FACTOR = 0.75F;

    private Node<Key, Value>[] table;
    private int size;
    private int capacity;

    private static class Node<Key, Value> {
        List<Key> keys;
        List<Value> values;

        public Node(Key key, Value value) {
            keys = new LinkedList<>();
            values = new LinkedList<>();
            keys.add(key);
            values.add(value);
        }

        public @Nullable Value get(@NotNull Key key) {
            int pos = keys.indexOf(key);
            if (pos == -1) {
                return null;
            }
            return values.get(pos);
        }

        public boolean put(@NotNull Key key, @NotNull Value value) {
            int pos = 0;
            for (Key token : keys) {
                if (key.equals(token)) {
                    values.set(pos, value);
                    return false;
                }
                pos++;
            }
            keys.add(key);
            values.add(value);
            return true;
        }

        public @Nullable Value remove(@NotNull Key key) {
            int pos = 0;
            for (Key token : keys) {
                if (key.equals(token)) {
                    return values.remove(pos);
                }
                pos++;
            }
            return null;
        }

        public int size() {
            return values.size();
        }

        public boolean isEmpty() {
            return values.size() == 0;
        }
    }

    private int hashCode(@NotNull Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public Hash() {
        capacity = 16;
        size = 0;
        table = new Node[capacity];
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int hash = hashCode(key);
        if (table[hash] == null) return null;
        return table[hash].get(key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hash = hashCode(key);
        if (table[hash] == null) {
            table[hash] = new Node<>(key, value);
            size++;
        } else if (table[hash].put(key, value)) size++;
        if (isOverflow()) reHashing();
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int hash = hashCode(key);
        if (table[hash] == null) return null;

        Value value = table[hash].remove(key);
        if (table[hash].isEmpty()) table[hash] = null;
        size--;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isOverflow() {
        return (float) size / capacity > LOAD_FACTOR;
    }

    private void reHashing() {
        capacity *= 2;
        Node<Key, Value>[] old = table;
        table = new Node[capacity];
        size = 0;
        for (Node<Key, Value> node : old) {
            if (node == null) continue;
            for (int i = 0; i < node.size(); i++) put(node.keys.get(i), node.values.get(i));
        }
    }
}