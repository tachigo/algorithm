package com.tachigo.algorithm.SymbolTable;

public class RedBlackBinarySearchTree<K extends Comparable<K>, V> {


    private static final boolean RED = true;
    private static final boolean BLACK = false;


    private class Node {

        private K key;
        private V val;
        private Node left, right;
        private int n;
        private boolean color;


        public Node(K key, V val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }


    private Node root;


    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }


    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        else {
            return x.n;
        }
    }


    /**
     * 如果节点h的位置，有一条红色的右连接
     *        |
     *        h ->红色-> x
     *      /         /   \
     * h.left   x.left   x.right
     * 需要对h位置为根的子树旋转出红色的左连接
     *                  |
     *        h <-红色<- x
     *      /  \          \
     * h.left  h.right    x.right
     * x.left => h.right
     * 最后x节点替换到原来h节点的位置
     * 返回的就是 h位置的新的节点 x
     * 调用方式为 h = rotateLeft(h);
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        // 改变颜色 h为红色
        // 这一步没有直接将x设置为黑色， 因为不知道原来指向h位置的链接是什么颜色，即不知道h节点原来是什么颜色
        x.color = h.color;
        h.color = RED;
        // 只需要修改 x 和 h 的 n
        // 原来的 h.n = size(h.left) + x.n
        x.n = h.n; // 这一步是因为原来h是子树的根，子树节点没有增减，所以新节点作为子树的根，节点总数不变
        h.n = size(h.left) + 1 + size(h.right);
        // 也可以用这种方式重新在h.n后边结算x.n = h.n + 1 + size(x.right);
        return x;
    }


    /**
     * 如果节点h的位置，有一条红色的左连接
     *                  |
     *        x <-红色<- h
     *      /  \          \
     * x.left  x.right    h.right
     * 需要对h位置为根的子树旋转出红色的右连接
     *        |
     *        x ->红色-> h
     *      /         /   \
     * x.left   h.left   h.right
     * x.right => h.left
     * 最后x节点替换到原来h节点的位置
     * 返回的就是 h位置的新的节点 x
     * 调用方式为 h = rotateRight(h);
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        // 改变颜色 h为红色
        // 这一步没有直接将x设置为黑色， 因为不知道原来指向h位置的链接是什么颜色，即不知道h节点原来是什么颜色
        x.color = h.color;
        h.color = RED;
        // 只需要修改 x 和 h 的 n
        // 原来的 h.n = size(h.left) + x.n
        x.n = h.n; // 这一步是因为原来h是子树的根，子树节点没有增减，所以新节点作为子树的根，节点总数不变
        h.n = size(h.left) + 1 + size(h.right);
        // 也可以用这种方式重新在h.n后边结算x.n = h.n + 1 + size(x.right);
        return x;
    }

    /**
     * 当一个节点的左右节点都是红色的时候，即左连接和右连接都是红色的时候
     * 需要进行颜色转换：
     * 将子节点夜色修改为黑色，将根节点修改为红色
     * @param h
     */
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }



    public void put(K key, V val) {
        root = put(root, key, val);
        root.color = BLACK; // 根节点总是黑色的
    }



    private Node put(Node h, K key, V val) {
        if (h == null) {
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            // key < h.key 左插
            h.left = put(h.left, key, val);
        }
        else if (cmp > 0) {
            // key > h.key 右插
            h.right = put(h.right, key, val);
        }
        else {
            // 相等 修改值即可
            h.val = val;
        }

        // 修改颜色
        if (isRed(h.right) && !isRed(h.left)) {
            /*
             *        |
             *        h
             * (黑色)/ \(红色)
             *         x
             * 左旋转成
             *
             *        |
             *        x
             * (红色)/ \
             *      h
             */
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            /*
             *           |
             *           h
             *    (红色)/  \
             *        x
             * (红色)/  \
             *     y
             * 右旋转成
             *           |
             *           x
             *    (红色)/ \(红色)
             *        y   h
             */
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            /*
             *          |
             *          h
             *   (红色)/  \(红色)
             * 替换颜色成
             *          |(红色)
             *          h
             *   (黑色)/ \(黑色)
             */
            flipColors(h);
        }


        // 计算h.n
        h.n = size(h.left) + 1 + size(h.right);
        return h;
    }
}
