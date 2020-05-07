package com.tachigo.algorithm.Graph.DFS;

import com.tachigo.algorithm.Graph.Graph;
import com.tachigo.algorithm.Stack.Stack;

/**
 * 使用深度优先搜索检查图是否有环
 */
public class Cycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public Cycle(Graph G) {
        if (hasSelfLoop(G)) {
            return;
        }
        if (hasParallelEdges(G)) {
            return;
        }
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, -1, v);
            }
        }
    }


    private boolean hasSelfLoop(Graph G) {
        for (int i = 0; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                if (i == j) {
                    cycle = new Stack<>();
                    cycle.push(i);
                    cycle.push(j);
                    return true;
                }
            }
        }
        return false;
    }


    private boolean hasParallelEdges(Graph G) {
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                if (!marked[j]) {
                    marked[j] = true;
                } else {
                    cycle = new Stack<>();
                    cycle.push(i);
                    cycle.push(j);
                    cycle.push(i);
                    return true;
                }
            }
            // 这里很重要
            for (int j : G.adj(i)) {
                marked[j] = false;
            }
        }
        return false;
    }


    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }


    /**
     * 顶点u是一轮dfs后上一轮dfs的起点
     * @param G
     * @param u
     * @param v
     */
    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, v, w);
            }
            else if (w != u) {
                // 已经访问过了这个顶点w
                // 这个顶点w不是上一轮dfs的起点，即不是v->w的一条反向边
                // 顶点w只可能是一个小于v处就已经遍历过了
                // 如果是这样的话，下一轮dfs就会回到小于v处的顶点，在向后执行，出现死循环
                // 所以这里就是一个环
                cycle = new Stack<>();
                // 从v开始向上查找
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);

                cycle.push(v);
                return;
            }
        }
    }


    public static void main(String[] args) {
        // test
    }
}
