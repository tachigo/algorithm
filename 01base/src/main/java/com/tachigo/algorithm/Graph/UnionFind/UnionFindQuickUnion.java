package com.tachigo.algorithm.Graph.UnionFind;

/**
 * 基于quick-union算法的并查集
 * 相对于quick-find算法，提高union的速度
 */
public class UnionFindQuickUnion {

    private int[] id; // 用父链接的形式表示了一片森林
    private int count; // 连通分量的数量

    public UnionFindQuickUnion(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }


    public int count() {
        return count;
    }


    public int find(int p) {
        // 一直向上查找，找到p的根节点
        // 速度取决于p在其树中的深度
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
        id[pRoot] = qRoot;
        count--;
    }





    public static class Test {
        public static void main(String[] args) {

        }
    }
}
