package com.tachigo.algorithm.Graph.UnionFind;

/**
 * 为了避免基于quick-union算法最坏情况出现一个单链表，
 * 即在union过程中形成的树不平衡。
 * 记录每棵树的大小，并总是将较小的树链接到较大的树上。
 * 使得树的高度远远小于未加权的版本所构造的树的高度
 */
public class WeightedQuickUnionUnionFind {


    private int[] id; // 父链接数组
    private int[] size; // 各个根节点所对应的分量大小，即其节点的数量

    private int count; // 连通分量的数量


    public WeightedQuickUnionUnionFind(int n) {
        count = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }


    public int count() {
        return count;
    }


    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }


    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (size[pRoot] < size[qRoot]) {
            // p这棵树的节点数量小于q这棵树的节点数量
            // 将p链接到q
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        count--;
    }


    public static class Test {
        public static void main(String[] args) {

        }
    }
}
