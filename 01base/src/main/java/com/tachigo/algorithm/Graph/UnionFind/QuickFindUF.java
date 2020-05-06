package com.tachigo.algorithm.Graph.UnionFind;

/**
 * 基于quick-find算法的并查集
 * 保证当且仅当 id[p] == id[q] 时，p与q相连
 */
public class QuickFindUF {

    private int[] id; // 分量ID
    private int count = 0;


    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }


    public int count() {
        return count;
    }


    /**
     * quick-find 算法
     * 保证当且仅当 id[p] == id[q] 时，p与q相连
     * 即p和q属于同一个连通分量（connected component）
     * 所有的属于一个连通分量的点i,id[i] 的值都是一样的
     * 查找可以在常数时间内完成
     * @param p
     * @return
     */
    private int find(int p) {
        return id[p];
    }

    /**
     * 基于quick-find算法的union
     * p和q将属于同一个连通分量，需要使id[p] == id[q]
     * 需要遍历所有数据，最坏情况下，O(n)
     * @param p
     * @param q
     */
    private void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            // 已经是在一个连通分量中
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        count--;
    }


    /**
     * 判断两个点是否相连
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }



    public static void main(String[] args) {

    }
}
