package com.tachigo.algorithm.LinkedList;

import java.util.Iterator;

/**
 * 支持尾插法的单向链表
 * 附加尾结点
 */
public class SingleTailLinkedList<T> implements Iterable<T> {

    private class Node {
        private T val;
        private Node prev;

        public Node(T val, Node prev) {
            this.val = val;
            this.prev = prev;
        }
    }


    private Node tail = new Node(null, null); // 尾指针
    private int size = 0;


    public void put(T val) {
        tail.prev = new Node(val, tail.prev);
        size++;
    }

    public int size() {
        return size;
    }


    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private Node current = tail.prev;

        @Override
        public void remove() {

        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            Node ret = current;
            current = current.prev;
            return ret.val;
        }
    }


    public static void main(String[] args) {
        // 测试

    }
}
