package com.tachigo.algorithm.Sort;

/**
 * 希尔排序
 */
public class Shell {

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
        // 计算h
        int h = 1;
        while (h < n / 3) {
            // factor = 3
            h = h * 3 + 1; // 1,4,13,40,121,364,... (1/2)*(3^k - 1) k=1,2,3,4,5
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exchange(a, j, j - h);
                }
            }

            h = h / 3;
        }
    }


    public static class Test {
        public static void main(String[] args) {

        }
    }
}
