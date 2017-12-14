package com.china.hadoop.bat.chapter5;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @Title: Calalan
 * @Package com.china.hadoop.bat.chapter5
 * @Description: Calalan数
 * @date 2017/10/22
 */
public class Calalan {

    public static int[] getCalalan(int n){
        int[] calalan = new int[n + 1];

        calalan[0] = 1;
        calalan[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                calalan[i] = calalan[i] + calalan[j] * calalan[i -j -1];
            }
        }

        return calalan;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getCalalan(10)));
    }

}
