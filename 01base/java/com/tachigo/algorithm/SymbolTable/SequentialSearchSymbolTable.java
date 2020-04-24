package com.tachigo.algorithm.SymbolTable;

/**
 * 基于无序链表的符号表
 * 采用顺序查找
 */
public class SequentialSearchSymbolTable<K, V> {

    private class Node {
        private K key;
        private V val;
        private Node next;

        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }


    private Node first;



    public V get(K key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }


    public void put(K key, V val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        // 头插法
        first = new Node(key, val, first);
    }

}
