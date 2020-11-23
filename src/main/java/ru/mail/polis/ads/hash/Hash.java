package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Hash<Key, Value> implements HashTable<Key, Value> {
    private final static float LOAD_FACTOR = 0.75f;
    private int capacity = 16;
    private int size = 0;
    private Node<Key, Value>[] data = new Node[capacity];

    private static class Node<Key, Value> {
        private List<Key> keys;
        private List<Value> values;

        Node() {
            keys = new ArrayList<>();
            values = new ArrayList<>();
        }

        Node(Key key, Value value) {
            keys = new ArrayList<>(1);
            keys.add(key);
            values = new ArrayList<>(1);
            values.add(value);
        }

        public void add(Key key, Value value) {
            int place = keys.indexOf(key);
            if (place == -1) {
                keys.add(key);
                values.add(value);
                return;
            }
            values.set(place, value);
        }

        @Nullable
        public Value remove(Key key) {
            for (int i = 0; i < keys.size(); ++i) {
                if (keys.get(i).equals(key)) {
                    keys.remove(i);
                    return values.remove(i);
                }
            }
            return null;
        }

        @Nullable
        public Value get(Key key) {
            int place = keys.indexOf(key);
            if (place == -1) {
                return null;
            }
            return values.get(place);
        }

        public int size() {
            return values.size();
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = getHashNum(key);
        if (data[hash] == null) {
            return null;
        }
        return data[hash].get(key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (size > capacity * LOAD_FACTOR) {
            increaseCapacity();
        }
        int hash = getHashNum(key);
        if (data[hash] == null) {
            data[hash] = new Node<>(key, value);
            ++size;
        } else {
            data[hash].add(key, value);
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int place = getHashNum(key);
        if (data[place] != null) {
            --size;
            Value val = data[place].remove(key);
            if (data[place].size() == 0) {
                data[place] = null;
            }
            return val;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getHashNum(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void increaseCapacity() {
        capacity *= 2;
        size = 0;
        Node<Key, Value>[] oldElementData = data;
        data = new Node[capacity];
        for (Node<Key, Value> oldElementDatum : oldElementData) {
            if (oldElementDatum != null) {
                for (int keyNum = 0; keyNum < oldElementDatum.size(); keyNum++) {
                    Node<Key, Value> oldElem = oldElementDatum;
                    put(oldElem.keys.get(keyNum), oldElem.values.get(keyNum));
                }
            }
        }
    }
}
