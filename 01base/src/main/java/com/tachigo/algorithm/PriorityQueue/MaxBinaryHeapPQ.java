package com.tachigo.algorithm.PriorityQueue;

/**
 * 大根完全二叉堆实现的优先级队列
 * @param <T>
 */
public class MaxBinaryHeapPQ<T extends Comparable<T>> {

    private T[] pq;

    private int n = 0;


    public MaxBinaryHeapPQ(int n) {
        // 数据存储于 pg[1..n]中，pg[0]没有使用，以方便操作
        pq = (T[]) new Comparable[n + 1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    /**
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return less(pq, i, j);
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    /**
     * 交换
     * @param i
     * @param j
     */
    private void exchange(int i, int j) {
        exchange(pq, i, j);
    }


    private static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    /**
     * 上浮
     * @param k
     */
    private void swim(int k) {
        // 因为pq[0]没有使用到，所以top=1
        swim(pq, k, 1);
    }


    private static void swim(Comparable[] a, int k, int top) {
        while (k > top && less(a,k / 2, k)) {
            exchange(a, k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 下沉
     * @param k
     */
    private void sink(int k) {
        sink(pq, k, size());
    }

    private static void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, k, j)) {
                break;
            }
            exchange(a, k, j);
            k = j;
        }
    }


    /**
     * 插入数据
     * @param item
     */
    public void insert(T item) {
        // 将新元素加入到数组末尾，增加堆的大小并让这个新元素上浮
        pq[++n] = item;
        swim(n);
    }


    /**
     * 删除最大元素
     * @return
     */
    public T delMax() {
        // 从数组顶端删去最大元素，将数组最后一个元素放到顶端，
        // 减小数组的大小，并让新顶端的元素下沉到合适位置
        T max = pq[1];
        exchange(1, n--);
        pq[n + 1] = null; // 释放游离对象
        sink(1);
        return max;
    }


    /**
     * 堆排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        // 从堆的有倒数第一层的倒数第二层元素开始构造堆
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
        // 排序
        while (n > 1) {
            // 将最大的元素和数组末尾元素兑换位置
            exchange(a, 1, n--);
            // 将新的顶端元素下沉至合适的位置，重新构造堆
            sink(a, 1, n);
        }
        // 对于大根堆，堆排序后的数组为升序
    }



    public static void main(String[] args) {

    }
}
