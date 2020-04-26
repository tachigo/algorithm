package com.tachigo.algorithm.SymbolTable;

/**
 * 基于开放地址的线性探测的符号表
 * @param <K>
 * @param <V>
 */
public class LinearProbingHashSymbolTable<K, V> {


    private int n; // 符号表中键值对的总数
    private int m = 16; // 线性探测表的大小

    private K[] keys;
    private V[] vals;

    public LinearProbingHashSymbolTable() {
        keys = (K[]) new Object[m];
        vals = (V[]) new Object[m];
    }


    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }


    public void put(K key, V val) {

        // 可以resize

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



}
