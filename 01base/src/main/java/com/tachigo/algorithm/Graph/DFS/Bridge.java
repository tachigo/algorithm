package com.tachigo.algorithm.Graph.DFS;

import com.tachigo.algorithm.Graph.Graph;

/**
 * 无向图的桥接边，当删去一个桥接边的时候，图的连通分量增加1
 */
public class Bridge {

    private int bridges; // 桥接边的数量
    private int cnt = 0;
    private int[] pre; // pre[v]表示dfs检测v节点的顺序
    private int[] low;


    public Bridge(Graph G) {
        low = new int[G.V()];
        pre = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            low[v] = -1;
            pre[v] = -1;
        }

        for (int v = 0; v < G.V(); v++) {
            if (pre[v] == -1) {
                dfs(G, v, v);
            }
        }
    }


    private void dfs(Graph G, int u, int v) {
        pre[v] = cnt++;
        low[v] = pre[v];
        for (int w : G.adj(v)) {
            if (pre[w] == -1) {
                // w顶点还没有遍历过
                dfs(G, v, w);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == pre[w]) {
                    // v-w是一个桥接边
                    bridges++;
                }
            }
            else if (w != u) {
                low[v] = Math.min(low[v], pre[w]);
            }
        }
    }

    /**
     * 可以有多少个连通分量
     * @return
     */
    public int components() {
        return bridges + 1;
    }
}
