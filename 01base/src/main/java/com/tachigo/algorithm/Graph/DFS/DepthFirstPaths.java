package com.tachigo.algorithm.Graph.DFS;

import com.tachigo.algorithm.Graph.Graph;
import com.tachigo.algorithm.Stack.Stack;

/**
 * 使用深度优先搜索查找无向图中的路径
 */
public class DepthFirstPaths {


    private boolean[] connected; // 是否连通
    private int[] edgeTo; // 给定起点到一个顶点的已知的路径上的最后一个顶点
    private final int start; // 起点


    public DepthFirstPaths(Graph G, int start) {
        this.start = start;
        connected = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, start);
    }


    private void dfs(Graph G, int v) {
        connected[v] = true;
        for (int w : G.adj(v)) {
            if (!connected[w]) {
                edgeTo[w] = v; // 连接到w的是v，生成的数据类似一个父查找树
                dfs(G, w);
            }
        }
    }

    /**
     * start 与 end 是否连通
     * 从 start 到 end 是否有连通的路径
     * @param end
     * @return
     */
    public boolean hashPathTo(int end) {
        return connected[end];
    }

    /**
     * 返回一个可以进行遍历操作的从start到end的路径
     * @param end
     * @return
     */
    public Iterable<Integer> pathTo(int end) {
        if (!hashPathTo(end)) {
            return null;
        }
        // 因为edgeTo是一棵父查找树
        // 从终点开始回溯路径，按照原来顺序进行返回的话，需要逆序
        // 所以用栈作为返回
        Stack<Integer> path = new Stack<>();
        for (int x = end; x != start; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(start); // 栈顶是起点
        return path;
    }


    public static void main(String[] args) {
        // test
    }
}
