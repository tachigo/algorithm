package com.tachigo.algorithm.Sort;

/**
 * 选择排序
 * in-place 原地排序算法
 */
public class Selection {

    /**
     * a < b ? true : false
     * @param a
     * @param b
     * @return
     */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * swap i and j
     * @param a
     * @param i
     * @param j
     */
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * 排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    // a[j] < a[min]
                    min = j; // 找到最小的
                }
            }
            exchange(a, i, min);
        }
    }


    public static class Test {
        public static void main(String[] args) {

        }
    }
}
