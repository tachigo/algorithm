package com.tachigo.algorithm.Sort;

/**
 * 归并排序
 */
public class Merge {


    private static Comparable[] aux;

    /**
     * 自顶向下
     * @param a
     */
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    /**
     * 自底向上 非递归
     * @param a
     */
    public static void sortBU(Comparable[] a) {
        aux = new Comparable[a.length];
        int n = a.length;
        for (int sz = 1; sz < n; sz = sz + sz) {
            // sz: 1,2,4,8,16,....
            for (int lo = 0; lo < n - sz; lo = lo + sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    /**
     * 自顶向下 递归
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        // merge
        merge(a, lo, mid, hi);
    }

    /**
     * 原地归并
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 复制出一个辅助数组
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                // 左部分用完 用右部分
                a[k] = aux[j++];
            }
            else if (j > hi) {
                // 右部分用完 用作部分
                a[k] = aux[i++];
            }
            else if (less(a[i], a[j])) {
                // 如果 i < j
                a[k] = aux[i++];
            }
            else {
                a[k] = aux[j++];
            }
        }
    }


    /**
     * a < b ? true : false
     * @param a
     * @param b
     * @return
     */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }



    public static class Test {
        public static void main(String[] args) {

        }
    }
}
