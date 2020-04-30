package com.tachigo.algorithm.String;

public class RabinKarp {


    private String pat;

    private long patHash;

    private int m; // 模式字符串的长度

    private long q; // 一个很大的素数

    private int r = 256; // 字母表的大小

    private long rm; // r^(m-1) % q

    public RabinKarp(String pat) {
        this.pat = pat;
        m = pat.length();
        q = 997; // 或者一个很大的素数
        rm = 1;
        for (int i = 1; i <= m - 1; i++) {
            rm = (r * rm) % q;
        }
        patHash = hash(pat, m);
    }


    private long hash(String key, int m) {
        // Horner 方法
        long h = 0;
        for (int i = 0; i < m; i++) {
            h = (h * r + key.charAt(i)) % q;
        }
        return h;
    }


    private boolean check(int i) {
        // 如果 q 是一个很大很大的素数 例如大于10的20次幂的数
        // 那么 hash后产生冲突的概率小于 1 / 10^20
        // 所以基本上可以直接返回 true 即 hash 相等 则 值相等
        return true;
    }


    public int search(String txt) {
        int n = txt.length();
        long txtHash = hash(txt, m);
        if (patHash == txtHash && check(0)) {
            return 0;
        }
        for (int i = m; i < n; i++) {
            // t[i] = txt.charAt(i)
            // 对于i开始的含有m个字符的子字符串 x[i] = t[i]*r^(m-1) + t[i+1]*r^(m-2) + ... + t[i+m-1]*r^0
            // 则 x[i+1] = (x[i] - t[i]*r^(m-1))*r + t[i+m]
            // h(x[i]) = x[i] % q 在此处为 txtHash
            // h(x[i+1]) = ((x[i] - t[i]*r^(m-1))*r + t[i+m]) % q
            //           = ((x[i] % q - t[i] * r^(m-1) % q) * r + t[i+m]) % q
            //           = ((txtHash - t[i] * r^(m-1) % q) * r + t[i+m]) % q
            // 减去第一个数 charAt(i-m), 这里 txtHash + q 保证所有结果都是正数
            txtHash = (txtHash + q - txt.charAt(i - m) * rm % q) % q;  // ((txtHash - t[i] * r^(m-1) % q) % q
            // 乘以r再加上最后一个数 charAt(i)
            txtHash = (txtHash * r + txt.charAt(i)) % q;
            // 比较
            if (patHash == txtHash) {
                if (check(i - m + 1)) {
                    // 起始位置为 i - m + 1
                    return i - m + 1;
                }
            }
        }
        return n;
    }


    public static class Test {
        public static void main(String[] args) {
            String pat = "AACAA";
            String txt = "AABRAACADABRAACAADABRA";

            RabinKarp rabinKarp = new RabinKarp(pat);
            System.out.println("text:       " + txt);
            System.out.print("pattern:    ");
            int offset = rabinKarp.search(txt);
            for (int i = 0; i < offset; i++) {
                System.out.print(" ");
            }
            System.out.println(pat);
        }
    }
}
