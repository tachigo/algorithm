package com.tachigo.algorithm.Queue;

import java.util.Iterator;

/**
 * 双向链表实现的队列
 * @param <T>
 */
public class LinkedListQueue<T> implements Iterable<T> {

    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private Node last;
    private int n = 0;


    public boolean isEmpty() {
        return first == null; // n == 0 也可以
    }

    public int size() {
        return n;
    }


    public void enqueue(T item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            // 如果表为空，first last 必须指向同一个节点
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }


    public T dequeue() {
        T item = first.item;
        first = first.next;
        if (isEmpty()) {
            // 如果表为空
            last = null;
        }
        n--;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }


    private class ListIterator implements Iterator<T> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }


    public static void main(String[] args) {

    }
}
