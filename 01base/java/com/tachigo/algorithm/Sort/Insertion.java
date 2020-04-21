package com.tachigo.algorithm.Sort;

/**
 * 插入排序
 * in-place 原地排序
 */
public class Insertion {

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
        for (int i = 1; i < n; i++) {
            // 将 a[i] 插入到 a[i-1], a[i-2],...之中
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                // a[j] < a[j-1]
                exchange(a, j, j - 1);
            }
        }
    }


    public static class Test {
        public static void main(String[] args) {

        }
    }
}
