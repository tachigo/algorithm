package com.tachigo.algorithm.LinkedList;

import java.util.Iterator;

/**
 * 双向链表
 * 附加首尾节点
 * @param <T>
 */
public class DoubleLinkedList<T> {

    private class Node {
        private T val;
        private Node next;
        private Node prev;


        public Node(T val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }


    private Node head;
    private Node tail;
    private int size;


    public DoubleLinkedList() {
        head = new Node(null, null, null);
        tail = head;
        size = 0;
    }


    public int size() {
        return size;
    }


    public void headPut(T val) {
        head.next = new Node(val, head.next, head.prev);
        size++;
    }


    public void tailPut(T val) {
        tail.prev = new Node(val, tail.next, tail.prev);
        size++;
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public Iterator<T> reverseIterator() {
        return new ReverseIterator();
    }

    private class ListIterator implements Iterator<T> {

        private Node current = head.next;

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
            current = current.next;
            return ret.val;
        }
    }


    private class ReverseIterator implements Iterator<T> {

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
        // test
    }

}
