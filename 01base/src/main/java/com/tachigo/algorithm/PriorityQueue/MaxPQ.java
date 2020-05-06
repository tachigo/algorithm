package com.tachigo.algorithm.PriorityQueue;

/**
 * 大根完全二叉堆
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


    private void exchange(int i, int j) {
        exchange(pq, i, j);
    }


    /**
     * 小的数据进行下沉
     * @param a
     * @param k
     * @param max
     */
    private static void sink(Comparable[] a, int k, int max) {
        while (k * 2 <= max) {
            int i = k * 2;
            if (i < max && less(a, i, i + 1)) {
                // a[i] < a[i + 1]
                i++; // i++ 为较大者
            }
            // 进行交换 下沉
            if (!less(a, k, i)) {
                // a[k] > a[i]
                break;
            }
            exchange(a, k, i);
            k = i;
        }
    }


    private void sink(int k) {
        sink(pq, k, size());
    }

    /**
     * 上浮
     * @param a
     * @param k
     * @param min
     */
    private static void swim(Comparable[] a, int k, int min) {
        while (k > min && less(a, k / 2, k)) {
            // a[k] > a[i] 上浮
            int i = k / 2;
            exchange(a, i, k);
            k = i;
        }
    }


    private void swim(int k) {
        swim(pq, k, 1);
    }

    public void append(T val) {
        pq[++N] = val;
        swim(N);
    }

    /**
     * 取出堆顶的数据
     * @return
     */
    public T deleteTop() {
        T top = pq[1];
        // 把最小的提到顶部再进行下沉
        exchange(pq, 1, N);
        pq[N] = null; // 防止游离
        N--;
        sink(pq, 1, N);
        return top;
    }


    /**
     * 使用大根堆进行排序
     * 大根堆排序出来的结果是升序的
     * @param a
     */
    public static void sort(Comparable[] a) {
        // 不管a是否有序，假设已经是一个完全二叉堆结构了
        // a[0]没有数据是前提，如果a[0]有数据用O(N)的时间构造一个a[0]没有数据的数组
        // 下面先构造成一个大根堆
        // 从倒数第二层开始，自底向上进行sink下沉
        int N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        // 将堆顶数据和最后一个元素交换，并减少N, 再将新的对顶数据进行sink下沉
        while (N > 1) { // 当N = 1的时候就已经有序了
            exchange(a, 1, N);
            N--;
            sink(a, 1, N);
        }
    }


    public static void main(String[] args) {
        // test
    }

}
