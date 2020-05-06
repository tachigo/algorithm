package com.tachigo.algorithm.PriorityQueue;

/**
 * 大根堆
 * @param <T>
 */
public class MaxPQ<T extends Comparable<T>> {

    private T[] pq;

    private int N = 0;

    public MaxPQ(int n) {
        // 数据存储于 pg[1..n]中，pg[0]没有使用，以方便操作
        pq = (T[]) new Comparable[n + 1];
        // pq = new T[n]; 这样写会报错
    }


    public boolean isEmpty() {
        return N == 0;
    }


    public int size() {
        return N;
    }


    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private boolean less(int i, int j) {
        return less(pq, i, j);
    }


    private static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
