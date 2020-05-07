package com.tachigo.algorithm.Graph;

/**
 * 无向图的连通分量
 * 使用深度优先遍历或者广度优先遍历都能计算出图的所有的连通分量
 * O(E + V)
 */
public class ConnectedComponent {

    private boolean[] marked; // 记录顶点在遍历过程中是否已经遍历过了
    private int[] id; // 每个顶点连通分量的ID
    private int count = 0; // 连通分量的个数

    public ConnectedComponent(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                // 如果没有遍历过s，以s作为起点进行深度优先搜索
                dfs(G, s);
                // 搜索完成以后，所有以s作为起点，与s连通的顶点就都遍历过了。
                // 也就是找到了一个连通分量，连通分量的个数增加1个
                count++;
            }
        }
    }


    private void dfs(Graph G, int v) {
        marked[v] = true; // 标记为已经遍历过了
        id[v] = count; // 顶点v属于第count个连通分量
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * 判断是否连通，即是否属于同一个连通分量
     * @param s
     * @param e
     * @return
     */
    public boolean connected(int s, int e) {
        return id[s] == id[e];
    }

    /**
     * 获得顶点v所属的连通分量的ID，ID的值从0开始
     * @param v
     * @return
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * 图连通分量的个数
     * @return
     */
    public int count() {
        return count;
    }


    public static void main(String[] args) {
        // test
    }
}
