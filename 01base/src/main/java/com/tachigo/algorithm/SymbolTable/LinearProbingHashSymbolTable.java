package com.tachigo.algorithm.SymbolTable;

/**
 * 基于开放地址的线性探测的符号表
 * @param <K>
 * @param <V>
 */
public class LinearProbingHashSymbolTable<K, V> {


    private int n = 0; // 符号表中键值对的总数
    private int m = 16; // 线性探测表的大小

    private K[] keys;
    private V[] vals;

    public LinearProbingHashSymbolTable(int cap) {
        m = cap;
        keys = (K[]) new Object[m];
        vals = (V[]) new Object[m];
    }


    public LinearProbingHashSymbolTable() {
        keys = (K[]) new Object[m];
        vals = (V[]) new Object[m];
    }


    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }


    public void put(K key, V val) {

        // 可以resize
        if (n > 0 && n >= m / 2) {
            resize(m * 2);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }



    public V get(K key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }


    private void resize(int cap) {
        LinearProbingHashSymbolTable<K, V> t;
        t = new LinearProbingHashSymbolTable<>(cap);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        m = t.m;
    }


    public boolean contains(K key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }


    public void delete(K key) {
        if (!contains(key)) {
            return;
        }

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }
        keys[i] = null;
        vals[i] = null;

        // 下一个开始
        i = (1 + 1) % m;
        while (keys[i] != null) {
            K keyToRedo = keys[i];
            V valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % m;
        }
        n--;
        // resize
        if (n > 0 && n <= m / 8) {
            resize(m / 2);
        }
    }




    public static class Test {
        public static void main(String[] args) {

        }
    }

}
