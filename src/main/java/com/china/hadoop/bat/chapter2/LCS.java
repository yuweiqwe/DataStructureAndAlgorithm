package com.china.hadoop.bat.chapter2;

import java.util.Stack;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: LCS
 * @Package com.china.hadoop.bat.chapter2
 * @Description: 最长子串  Long Common SubString
 * 1、当Xm=Yn，LCS(Xm,Yn) = LCS(Xm-1,Yn-1) + Xm<br/>
 * 2、当Xm!=Yn，LCS(Xm,Yn) = LCS(Xm-1,Yn) + Xm<br/>
 * @date 2017/10/3
 */
public class LCS {


    public static int lcs(String source, String target){
        int sLen = source.length();
        int tLen = target.length();

        int[][] len = new int[sLen + 1][tLen + 1];

        int i,j;
        for (i = 1; i <= sLen; i++) {
            for (j = 1; j <= tLen; j++) {
                if(source.charAt(i - 1) == target.charAt(j - 1)){
                    len[i][j] = len[i - 1][j - 1] + 1;
                }else{
                    len[i][j] = max(len[i - 1][j], len[i][j - 1]);
                }

            }
        }

        Stack<Character> stack = new Stack<>();

        i = sLen;
        j = tLen;
        while((i != 0) && (j != 0)){
            if(source.charAt(i - 1) == target.charAt(j - 1)){
                stack.push(source.charAt(i - 1));
                i--;
                j--;
            }else{
                if(len[i][j - 1] > len[i - 1][j]){
                    j--;
                }else{
                    i--;
                }
            }
        }

        System.out.println(stack.toString());
        return stack.size();
    }

    public static int max(int a, int b){
        return a > b ? a : b;
    }

    public static void main(String[] args) {
//        String source = "TCGGATCGACTT";
//        String target = "AGCCTACGTA";
        String source = "ABCBDA";
        String target = "BDCABA";

        int len = lcs(source, target);
        System.out.println(len);
    }

}
