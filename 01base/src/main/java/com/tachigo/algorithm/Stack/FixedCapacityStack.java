package com.tachigo.algorithm.Stack;

import java.util.Iterator;

/**
 * 固定容量的栈
 * @param <T>
 */
public class FixedCapacityStack<T> implements Iterable<T> {

    private T[] a;

    private int n = 0;

    FixedCapacityStack(int cap) {
        //  a = new T[cap]; // error: type parameter 'T' cannot be instantiated directly
        a = (T[]) new Object[cap];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(T item) {
        a[n++] = item;
    }

    public T pop() {
        return a[--n];
    }


    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }


    private class ReverseArrayIterator implements Iterator<T> {

        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return a[--i];
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
