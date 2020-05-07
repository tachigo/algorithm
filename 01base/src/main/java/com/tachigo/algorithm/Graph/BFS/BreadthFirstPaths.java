package com.tachigo.algorithm.Graph.BFS;

import com.tachigo.algorithm.Graph.Graph;
import com.tachigo.algorithm.Queue.Queue;
import com.tachigo.algorithm.Stack.Stack;

/**
 * 使用广度优先搜索来查找与给定顶点连通的路径
 */
public class BreadthFirstPaths {


    private boolean[] connected;
    private final int start; // 起点

    private int[] edgeTo;


    public BreadthFirstPaths(Graph G, int start) {
        this.start = start;
        connected = new boolean[G.V()];
        bfs(G, start);
    }


    private void bfs(Graph G, int s) {
        // 使用队列进行广度优先遍历
        Queue<Integer> queue = new Queue<>();
        connected[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                connected[w] = true;
                edgeTo[w] = v;
                queue.enqueue(w);
            }
        }
    }


    public boolean hasPathTo(int end) {
        return connected[end];
    }

    /**
     * 起点和重点的连通的路径
     * 由于广度优先搜索在遍历过程中，总是以起点为中心，一层一层的遍历与它连通的顶点。
     * 所以计算得到的路径是从起点到终点最短的路径.
     * @param end
     * @return
     */
    public Iterable<Integer> pathTo(int end) {
        if (!hasPathTo(end)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = end; x != start; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(start);
        return path;
    }


    public static void main(String[] args) {
        // test
    }
}
