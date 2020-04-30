package com.tachigo.algorithm.String;

public class BoyerMoore {


    private int[] right;

    private String pat;


    public BoyerMoore(String pat) {
        this.pat = pat;
        int m = pat.length();
        int r = 256; // 字母表大小
        right = new int[r];
        // 初始化
        for (int i = 0; i < r; i++) {
            // 不包括在模式中的字符为-1
            right[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            // 包含在模式中的字符的值为他在其中出现的最右的位置
            right[pat.charAt(i)] = i;
        }
    }


    public int search(String txt) {
        int n = txt.length();
        int m = pat.length();
        int skip;

        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) {
                        skip = 1;
                    }
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
        }
        return n;
    }



    public static class Test {
        public static void main(String[] args) {
            String pat = "AACAA";
            String txt = "AABRAACADABRAACAADABRA";

            BoyerMoore boyerMoore = new BoyerMoore(pat);
            System.out.println("text:       " + txt);
            System.out.print("pattern:    ");
            int offset = boyerMoore.search(txt);
            for (int i = 0; i < offset; i++) {
                System.out.print(" ");
            }
            System.out.println(pat);
        }
    }
}
