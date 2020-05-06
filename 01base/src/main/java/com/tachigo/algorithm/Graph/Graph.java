package com.tachigo.algorithm.Graph;

import com.tachigo.algorithm.LinkedList.SingleHeadLinkedList;

/**
 * 用邻接链表定义的无向图
 */
public class Graph {

    private final int V; // 顶点的数量

    private int E = 0; // 边的数量


    private SingleHeadLinkedList<Integer>[] adj; // adjacency 邻接

    public Graph(int V) {
        this.V = V;
        adj = (SingleHeadLinkedList<Integer>[]) new SingleHeadLinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new SingleHeadLinkedList<>();
        }
    }


    public int E() {
        return E;
    }

    public int V() {
        return V;
    }


    public void addEdge(int v, int w) {
        adj[v].put(w);
        adj[w].put(v);
        E++;
    }


    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 返回顶点的度，因为是无向图，所以不区分出度与入度
     * @param v
     * @return
     */
    public int degree(int v) {
        return adj[v].size();
    }


    public static void main(String[] args) {
        // test
    }
}
