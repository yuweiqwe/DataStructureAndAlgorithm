package com.china.hadoop.bat.chapter2;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: LPS
 * @Package com.china.hadoop.bat.chapter2
 * @Description: Longest Palindromic Substring最长回文子串
 * @date 2017/10/10
 */
public class LPS {

    public static int[] manacher(String source){
        String target = preProcess(source);

        int size = target.length();

        int[] p = new int[size];
        p[0] = 1;

        int id = 0;
        int mx = 1;

        for (int i = 1; i < size; i++) {
            if(mx > i){
                //   mx对称点_______j(i的对称点=2*id-i)________id________i_______mx
                //mx = id + p[id]
                //当p[2 * id - i] <= mx - i时，取p[2 * id - i]，当p[2 * id - i] > mx - i时，取mx - i
                p[i] = min(p[2 * id - i], mx - i);
            }else{
                p[i] = 1;
            }

            //当i + p[id] <= mx时，此while循环不成立，当i + p[id] > mx计算在mx之外还有多少个相等的回文子文串
            while(i + p[i] < size
                    && target.charAt(i + p[i]) == target.charAt(i - p[i])){
                p[i] += 1;
            }

            //取当前i + p[i]最大：mx为最大，当生成一个新的i + p[i]，判断mx是否需要更新，同时更新mx对应的对称中心id
            if(mx < i + p[i]){
                mx = i + p[i];
                id = i;
            }
        }

        return p;
    }

    public static String preProcess(String source){
        if(source == null){
            return source;
        }

        StringBuilder sb = new StringBuilder("$");
        for (char c : source.toCharArray()) {
            sb.append("#").append(c);
        }
        sb.append("#");

        return sb.toString();
    }

    public static int min(int a, int b){
        return a > b ? b : a;
    }

    public static void main(String[] args) {
        String source = "12212321";
        System.out.println(Arrays.toString(manacher(source)));
    }

}
