package com.tachigo.algorithm.Graph.DFS;

import com.tachigo.algorithm.Graph.Graph;
import com.tachigo.algorithm.Stack.Stack;

import java.util.Iterator;

/**
 * 非递归的深度优先搜索O(E + V)
 * 使用一个栈来实现
 */
public class NonRecursiveDFS {


    private boolean[] connected;

    public NonRecursiveDFS(Graph G, int start) {
        connected = new boolean[G.V()];

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int i = 0; i < G.V(); i++) {
            adj[i] = G.adj(i).iterator();
        }

        // 栈中所有顶点都是与起点连通的
        Stack<Integer> stack = new Stack<>();
        connected[start] = true;
        stack.push(start);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                // 与v有连通顶点
                int w = adj[v].next();
                if (!connected[w]) {
                    // 入栈标记
                    connected[w] = true;
                    stack.push(w);
                }
            } else {
                // 没有与v连通的顶点
                stack.pop();
            }
        }
    }


    public boolean connected(int end) {
        return connected[end];
    }


    public static void main(String[] args) {
        // test
    }
}
