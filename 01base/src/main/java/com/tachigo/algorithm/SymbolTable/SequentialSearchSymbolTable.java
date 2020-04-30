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


    private int size = 0;


    public int size() {
        return size;
    }


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
        size++;
    }


    public void delete(K key) {
        // 如果首节点匹配
        if (key.equals(first.key)) {
            first = first.next;
        } else {
            Node p = first;
            for (Node x = first.next; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    // 找到 进行删除
                    p.next = x.next;
                    return;
                } else {
                    p = x;
                }
            }
        }
        size--;
    }

}
