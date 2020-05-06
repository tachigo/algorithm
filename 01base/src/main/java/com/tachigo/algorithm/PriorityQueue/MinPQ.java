package com.tachigo.algorithm.PriorityQueue;

/**
 * 小根堆
 * @param <T>
 */
public class MinPQ<T extends Comparable<T>> {

    private T[] pq;

    private int N = 0;

    public MinPQ(int N) {
        pq = (T[]) new Comparable[N];
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
     * 将较大的数据进行下沉
     * @param a
     * @param k
     * @param max
     */
    private static void sink(Comparable[] a, int k,  int max) {
        while (2 * k <= max) {
            int i = 2 * k;
            // 选出子节点较小的一个
            if (i < max && !less(a, i, i + 1)) {
                // a[i] > a[i + 1]
                i++;
            }
            // 子节点较小的一个和父节点比较
            if (less(a, k, i)) {
                // a[k] < a[i]
                break;
            }
            // 下沉
            exchange(a, k, i);
            k = i;
        }
    }


    private void sink(int k) {
        sink(pq, k, size());
    }


    private static void swim(Comparable[] a, int k, int min) {
        while (k > min && less(a, k, k / 2)) {
            int i = k / 2;
            // a[k] < a[i]
            exchange(a, k, i);
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


    public T deleteTop() {
        T ret = pq[1];
        pq[1] = pq[N];
        pq[N] = null;
        N--;
        sink(1);
        return ret;
    }


    /**
     * 小根堆排序
     * 最后a是降序
     * @param a
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        while (N > 1) {
            exchange(a, 1, N);
            N--;
            sink(a, 1, N);
        }
    }


    public static void main(String[] args) {
        // test
    }
}
