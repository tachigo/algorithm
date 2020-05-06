package com.tachigo.algorithm.Graph.UnionFind;

public class PathCompressWeightedQuickUnionUnionFind {

    private int[] id;
    private int[] size;
    private int count;

    public PathCompressWeightedQuickUnionUnionFind(int n) {
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
//        while (p != id[p]) {
//            id[p] = id[id[p]]; // 通过这个方法将find要查找的路径减半
//            p = id[p];
//        }
//        return p;
        // 直接替换路径上所有节点的链接到根节点
        // 找到根q
        int q = p;
        while (q != id[q]) {
            q = id[q];
        }
        while (p != id[p]) {
            int tmp = p;
            p = id[p];
            id[tmp] = q;
        }
        return q;
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
        int reRoot;
        if (size[pRoot] < size[qRoot]) {
            // p到q
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
            reRoot = pRoot;
        } else {
            // q到p
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
            reRoot = qRoot;
        }
        // 最好再次find一下
        find(reRoot);
        count--;
    }

    public static class Test {
        public static void main(String[] args) {

        }
    }
}
