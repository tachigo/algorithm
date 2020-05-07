package com.tachigo.algorithm.Graph;


/**
 * 检测一个图是否是简单的
 * 即，不包含自环（self loop）及平行边（parallel edge）
 */
public class SimplificationValidate {

    /**
     * 判断是否有自环
     * @param G
     * @return
     */
    public static int countSelfLoop(Graph G) {
        int count = 0;
        for (int i = 0; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                if (i == j) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 判断是否有平行边
     * 对于边没有其他属性的无向图，不会出现平行边
     * @param G
     * @return
     */
    public static boolean hasParallelEdges(Graph G) {
        boolean[] marked = new boolean[G.V()];

        for (int i = 0; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                if (marked[j]) {
                    // 如果已经遍历过了
                    return true;
                }
                marked[j] = true;
            }
        }
        return false;
    }
}
