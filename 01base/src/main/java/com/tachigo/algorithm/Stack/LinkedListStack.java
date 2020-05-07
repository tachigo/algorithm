package com.tachigo.algorithm.Stack;

import java.util.Iterator;

/**
 * 单链表实现的栈
 * @param <T>
 */
public class LinkedListStack<T> implements Iterable<T> {

    private class Node {
        T item;
        Node next;
    }

    private Node first;

    private int n = 0;

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    /**
     * 头插法
     * @param item
     */
    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }


    public T pop() {
        T item = first.item;
        first = first.next;
        n--;
        return item;
    }


    public T peek() {
        return first.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
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



    public static class Test {
        public static void main(String[] args) {

        }
    }
}
