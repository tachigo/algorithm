package com.tachigo.algorithm.LinkedList;

import java.util.Iterator;

/**
 * 支持头插法的单向链表
 * 附加头结点
 */
public class SingleHeadLinkedList<T> implements Iterable<T> {

    private class Node {
        private T val;
        private Node next;

        public Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }
    }


    private Node head = new Node(null, null); // 头指针, 不存储值

    private int size = 0;


    public int size() {
        return size;
    }

    /**
     * 头插法
     * @param val
     */
    public void put(T val) {
        head.next = new Node(val, head.next);
        size++;
    }


    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private Node current = head.next;

        @Override
        public void remove() {

        }

        @Override
        public T next() {
            Node ret = current;
            current = current.next;
            return ret.val;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }
    }



    public static void main(String[] args) {
        // 测试

    }
}
