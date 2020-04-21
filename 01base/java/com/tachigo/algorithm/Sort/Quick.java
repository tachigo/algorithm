package com.tachigo.algorithm.Sort;

/**
 * 快速排序
 */
public class Quick {
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
     * 切分
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi;

        Comparable v = a[lo];

        while (true) {
            while (less(a[i], v)) {
                // a[i] < v
                i++;
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[j])) {
                // a[j] > v
                j--;
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(a, i, j);
        }

        exchange(a, lo, j);

        return j;
    }


    public static void sort(Comparable[] a) {
        // shuffle to make input array randomized
        sort(a, 0, a.length - 1);
    }


    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(a, 0, a.length - 1);
        sort(a, lo, j);
        sort(a, j + 1, hi);
    }



    public static void sort3way(Comparable[] a) {
        // shuffle to make input array randomized
        sort3way(a, 0, a.length - 1);
    }


    private static void sort3way(Comparable[] a, int lo, int hi) {
        if (hi >= lo) {
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                // a[i] < v
                exchange(a, lt, i);
                lt++;
                i++;
            }
            else if (cmp > 0) {
                // a[i] > v
                exchange(a, i, gt);
                gt--;
            }
            else {
                // a[i] == v
                i++;
            }
        }
        sort3way(a, lo, lt - 1);
        sort3way(a, gt + 1, hi);
    }
}
