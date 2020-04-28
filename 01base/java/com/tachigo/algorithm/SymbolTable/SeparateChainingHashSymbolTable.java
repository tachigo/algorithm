package com.tachigo.algorithm.SymbolTable;

/**
 * 基于拉链法的散列表
 * @param <K>
 * @param <V>
 */
public class SeparateChainingHashSymbolTable<K, V> {


    private int n = 0; // 键值对总数
    private int m; // 散列表的大小

    private SequentialSearchSymbolTable<K, V>[] st; // 存放链表对象的数组


    SeparateChainingHashSymbolTable(int m) {
        this.m = m;
        st = (SequentialSearchSymbolTable<K, V>[]) new SequentialSearchSymbolTable[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchSymbolTable<>();
        }
    }

    SeparateChainingHashSymbolTable() {
        this(997);
    }


    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public V get(K key) {
        return st[hash(key)].get(key);
    }


    public void put(K key, V val) {
        // resize
        if (n > 0 && n >= m / 2) {
            resize(m * 2);
        }
        int hash = hash(key);
        SequentialSearchSymbolTable<K, V> list = st[hash];
        int oldSize = list.size();
        // list内部采用的是头插法
        list.put(key, val);
        if (oldSize != list.size()) {
            n++;
        }
    }


    public void delete(K key) {
        int hash = hash(key);
        SequentialSearchSymbolTable<K, V> list = st[hash];
        int oldSize = list.size();

        list.delete(key);
        if (oldSize != list.size()) {
            n--;
        }
        // resize
        if (n > 0 && n <= m / 8) {
            resize(m / 2);
        }
    }


    private void resize(int cap) {

    }

}
