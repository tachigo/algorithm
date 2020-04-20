package com.tachigo.algorithm.Stack;

import java.util.Iterator;

/**
 * 动态调整数组大小的栈
 * @param <T>
 */
public class ResizingArrayStack<T> implements Iterable<T> {

    private T[] a = (T[]) new Object[1];

    private int n = 0;

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int max) {
        T[] tmp = (T[]) new Object[max];
        for (int i = 0; i < n; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }

    public void push(T item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    public T pop() {
        T item = a[--n];
        a[n] = null; // prevent 游离
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
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
