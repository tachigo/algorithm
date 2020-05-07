package com.tachigo.algorithm.Graph.DFS;

import com.tachigo.algorithm.Graph.Graph;

/**
 * 深度优先搜索 O(E + V)
 * 判断连通性
 */
public class DepthFirstSearch {

    private boolean[] connected; // 是否连通
    private int count; // 与给定顶点连通的顶点数

    public DepthFirstSearch(Graph G, int start) {
        connected = new boolean[G.V()];
        dfs(G, start);
    }


    private void dfs(Graph G, int v) {
        connected[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!connected[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * start与end是否相连
     * @param end
     * @return
     */
    public boolean connected(int end) {
        return connected[end];
    }

    /**
     * 与start连通的顶点数量
     * @return
     */
    public int count() {
        return count;
    }


    public static void main(String[] args) {
        // test
    }

}
