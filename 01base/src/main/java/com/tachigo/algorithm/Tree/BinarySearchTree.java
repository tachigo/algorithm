package com.tachigo.algorithm.Tree;

import com.tachigo.algorithm.Queue.LinkedListQueue;

/**
 * 二叉查找树 BST
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int n; // 以该节点为跟的子树中的节点总数，包含根

        public Node(K key, V val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }



    private Node root; // 根节点


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



    public V get(K key) {
        return get(root, key);
    }


    private V get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        }
        else if (cmp > 0) {
            return get(x.right, key);
        }
        else {
            return x.val;
        }
    }


    public void put(K key, V val) {
        root = put(root, key, val);
    }


    private Node put(Node x, K key, V val) {
        // 如果key存在于以x为根节点的子树中则更新它的值
        // 否则将以key和val为键值对的新节点插入到该子树中
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, val);
        }
        else {
            x.val = val;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }



    public K min() {
        return min(root).key;
    }


    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }


    public K max() {
        return max(root).key;
    }


    public Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    /**
     * 向下取整：最接近key的比key小的节点
     * @param key
     * @return
     */
    public K floor(K key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        else {
            return x.key;
        }
    }


    private Node floor(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        else if (cmp < 0) {
            // key < x.key
            return floor(x.left, key);
        }
        else {
            // key > x.key
            Node t = floor(x.right, key);
            // 最终返回的地方在这里
            if (t != null) {
                return t;
            } else {
                return x;
            }
        }
    }

    /**
     * 向上取整：最接近key的比key大的节点
     * @param key
     * @return
     */
    public K ceil(K key) {
        Node x = ceil(root, key);
        if (x == null) {
            return null;
        }
        else {
            return x.key;
        }
    }



    private Node ceil(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        else if (cmp > 0) {
            // key > x.key
            return ceil(x.right, key);
        }
        else {
            // key < x.key
            Node t = ceil(x.left, key);
            // 真正返回的是在这里
            if (t != null) {
                return t;
            } else {
                return x;
            }
        }
    }


    /**
     * 选择出排行为rank的节点的key
     * @param rank
     * @return
     */
    public K select(int rank) {
        Node x = select(root, rank);
        if (x == null) {
            return null;
        }
        else {
            return x.key;
        }
    }


    private Node select(Node x, int rank) {
        if (x == null) {
            return null;
        }
        int size = size(x.left);
        if (size == rank) {
            return x;
        }
        else if (size > rank) {
            return select(x.left, rank);
        }
        else {
            return select(x.right, rank - size - 1);
        }
    }



    public int rank(K key) {
        return rank(key, root);
    }

    private int rank(K key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return size(x.left);
        }
        else if (cmp < 0) {
            // key < x.key
            return rank(key, x.left);
        }
        else {
            return size(x.left) + 1 + rank(key, x.right);
        }
    }



    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x == null) {
            return null;
        }
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }


    public void deleteMax() {
        root = deleteMax(root);
    }


    private Node deleteMax(Node x) {
        if (x == null) {
            return null;
        }
        x.right = deleteMax(x.right);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }



    public void delete(K key) {
        root = delete(root, key);
    }


    private Node delete(Node x, K key) {
        if (x == null) {
            return null;
        }
        // 要删除的节点在x的左/右子树中 x.sub = delete(x.sub, key);
        // 1 --> 2 --> 3  ==> 1 --> 3： 删除操作返回的是需要向上连接的节点3
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            // key < x.key 要删除的节点在x的左子树中
            x.left = delete(x.left, key);
        }
        else if (cmp > 0) {
            // key > x.key 要删除的节点在x的右子树中
            x.right = delete(x.right, key);
        }
        else {
            // 找到要删除的节点就是x
            if (x.right == null) {
                // 如果右子树为空，将左子树向上连接
                return x.left;
            }
            else if (x.left == null) {
                // 如果左子树为空，将右子树向上连接
                return x.right;
            }
            else {
                // 左右子树都不为空
                Node t = x;
                // 从右子树中找最小也就是最接近x.key的节点,替换这个节点
                x = min(x.right);
                // 删除右子树最小那个节点的引用
                x.right = deleteMin(x.right);
                // 原要删除的节点的左子树顺势向上连接
                x.left = t.left;
            }
        }
        // 更新节点总数
        x.n = size(x.left) + 1 + size(x.right);
        // 返回删除
        return x;
    }


    public Iterable<K> keys() {
        return keys(min(), max());
    }


    public Iterable<K> keys(K lo, K hi) {
        // 返回一个队列, 使用自己实现的一个队列
        LinkedListQueue<K> queue = new LinkedListQueue<>();
        keys(root, queue, lo, hi);
        return queue;
    }


    private void keys(Node x, LinkedListQueue<K> queue, K lo, K hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        // 中序遍历
        // 进入左子树遍历
        if (cmplo < 0) {
            // lo < x.key lo存在于左子树中
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            // x.key ∈ [lo, hi]
            queue.enqueue(x.key);
        }
        // 进入右子树遍历
        if (cmphi > 0) {
            // hi > x.key hi存在于右子树中
            keys(x.right, queue, lo, hi);
        }
    }

    public static void main(String[] args) {

    }
}
