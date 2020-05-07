package com.tachigo.algorithm.Graph.DFS;

import com.tachigo.algorithm.Graph.Graph;
import com.tachigo.algorithm.Stack.Stack;

/**
 * 是否是一个二分图
 */
public class Bipartite {

    private boolean isBipartite;
    private boolean[] color; // 进行着色
    private boolean[] marked; // 遍历是否访问过
    private int[] edgeTo; // 顶点的父查找树路径
    private Stack<Integer> cycle; // 环


    public Bipartite(Graph G) {
        isBipartite = true;
        color = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }


    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(G, w);
            }
            else if (color[w] == color[v]) {
                isBipartite = false;
                // 这里有环 而且这个换是节点个数为奇数
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public boolean color(int v) {
        return color[v];
    }

    public Iterable<Integer> oddCycle() {
        return cycle;
    }


    public static void main(String[] args) {
        // test
    }
}
