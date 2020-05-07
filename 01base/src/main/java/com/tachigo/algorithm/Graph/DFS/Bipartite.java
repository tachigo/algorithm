package com.tachigo.algorithm.Graph.DFS;

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
}
