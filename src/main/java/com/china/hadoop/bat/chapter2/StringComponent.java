package com.china.hadoop.bat.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: StringComponent
 * @Package com.china.hadoop.bat.chapter2
 * @Description: 仅由三个字符A、B、C构成字符串，且字符 串任意三个相邻元素不能完全相同。如 “ACCCAB”不合法，“ABBCBCA”合法。 求满足条件的长度为n的字符串个数。
 * 假定不考虑整数溢出
 * 要求时间和空间复杂度不高于O(N)。
 * 若当前已经有了所有长度为n-1的合法字符 串，如何在末端增加一个字符，形成长度为 n的字符串呢?
 * 将长度为n-1字符串分成“末尾两个字符不 相等”和“末尾两个字符相等”两种情况， 各自数目记做dp[n-1][0], dp[n-1][1]:
 * @date 2017/10/11
 */
public class StringComponent {

    public static int three(int n){
        int noRepeat = 3;
        int repeat = 0;

        int i = 2;
        int t = 0;
        while(i <= n){
            t = noRepeat;
            noRepeat = 2 * (noRepeat + repeat);
            repeat = t;
            i++;
        }

        return noRepeat + repeat;
    }

    public static void main(String[] args) {
        System.out.println(three(10));
    }

}
