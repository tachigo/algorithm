package com.tachigo.algorithm.String;

public class KMP {

    private String pat;

    private int[][] dfa;


    public KMP(String pat) {
        this.pat = pat;
        // 构造dfa 确定优先状态自动机
        int m = pat.length();
        int r = 256; // 字母表的大小
        dfa = new int[r][m];
        dfa[pat.charAt(0)][0] = 1;

        for (int x = 0, j = 1; j < m; j++) {
            // 计算dfa
            for (int c = 0; c < r; c++) {
                // 复制匹配失败情况下的值
                dfa[c][j] = dfa[c][x];
            }
            // 设置匹配成功情况下的值
            dfa[pat.charAt(j)][j] = j + 1;
            // 更新重启状态
            x = dfa[pat.charAt(j)][x];
        }
    }


    public int search(String txt) {
        int i, j, n = txt.length(), m = pat.length();
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) {
            return i - m;
        } else {
            return n;
        }
    }



    public static class Test {
        public static void main(String[] args) {
            String pat = "AACAA";
            String txt = "AABRAACADABRAACAADABRA";

            KMP kmp = new KMP(pat);
            System.out.println("text:       " + txt);
            System.out.print("pattern:    ");
            int offset = kmp.search(txt);
            for (int i = 0; i < offset; i++) {
                System.out.print(" ");
            }
            System.out.println(pat);
        }
    }
}
