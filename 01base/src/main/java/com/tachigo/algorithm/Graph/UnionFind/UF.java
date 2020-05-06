package com.tachigo.algorithm.Graph.UnionFind;

/**
 * 路径压缩的加权quick-union并查集
 * 采用路径减半压缩
 * union时，采用高度低的子树合并到高度高的子树。
 * 这样所有子树的高度都会小于或者等于lg(n)
 */
public class UF {

    private int[] parent;
    private int[] height; // 以i为子树的高度
    private int count; // 连通分量的数量


    public UF(int n) {
        count = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 0;
        }
    }

    public int count() {
        return count;
    }


    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // 路径减半压缩
            p = parent[p];
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

        if (height[pRoot] < height[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (height[pRoot] > height[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            height[pRoot]++;
        }
        count--;
    }


    public static class Test {
        public static void main(String[] args) {

        }
    }


}
