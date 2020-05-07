package com.tachigo.algorithm.Graph.BFS;

import com.tachigo.algorithm.Graph.Graph;
import com.tachigo.algorithm.Queue.Queue;

/**
 * 广度优先搜索 O(E + V)
 * 可以判断连通性
 */
public class BreadthFirstSearch {

    private boolean[] connected; // 点对于给定的起点是否是连通的
    private int count = 0; // 与起点连通的顶点数量


    public BreadthFirstSearch(Graph G, int start) {
        connected = new boolean[G.V()];
        bfs(G, start);
    }

    private void bfs(Graph G, int s) {
        // 广度优先搜索使用一个队列来记录下一个需要遍历的节点，这个节点需要是和起点连通的
        Queue<Integer> queue = new Queue<>();
        connected[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                // 遍历和v连通的顶点
                connected[w] = true;
                // 加入队列
                queue.enqueue(w);
            }
        }
    }

    public boolean connected(int v) {
        return connected[v];
    }

    public int count() {
        return count;
    }


    public static void main(String[] args) {

    }
}
