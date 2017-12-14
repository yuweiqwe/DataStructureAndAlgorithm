package com.china.hadoop.bat.chapter9;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: LeastOperationTimes
 * @Package com.china.hadoop.bat.chapter9
 * @Description: 操作最少次数
 * 变量x从1开始变化，规则是:要么变成x+1， 要么变成2*x，问:若想将x变成整数2015， 最少需要多少次变化?
 * 一种可行的操作变化: 1,2,3,6,7,14,15,30,31,62,124,125,250,251,502,503, 1006,1007,2014,2015
 * 如何思考?
 * 任何一个数字都可以，如:20161006  --> 1001100111010000111101110
 * <p>
 * 设dp(n)表示从1到n的最少操作步数
 * 若n为奇数，则n的前一步只能是n-1
 * 若n为偶数，则n的前一步是n-1和n/2的操作步数 的小者
 * n是奇数 d(n-1) + 1
 * n是偶数 min[dp(n/2),dp(n-1)] + 1
 * @date 2017/11/4
 */
public class LeastOperationTimes {

    /**
     * @Title: cal
     * @Description: 从1变道n，操作最少次数
     * @param n 1变成的最终的数
     * @param dp 动态规划每一个数从1变成本数需要的次数
     * @param pre 每一个数从1变成 本数 需要的次数 的前一个数
     * @return int 次数
     * @throws
     * */
    public static int cal(int n, int[] dp, int[] pre) {
        if (n == 1) {
            return 0;
        }

        if (n % 2 == 1) {//奇数
            if (dp[n - 1] == 0) {
                dp[n - 1] = cal(n - 1, dp, pre);
            }
            dp[n] = dp[n - 1] + 1;
            pre[n] = n - 1;
        } else {//偶数
            if (dp[n - 1] == 0) {
                dp[n - 1] = cal(n - 1, dp, pre);
            }
            if (dp[n / 2] == 0) {
                dp[n / 2] = cal(n / 2, dp, pre);
            }

            if(dp[n - 1] > dp[n / 2]){
                dp[n] = dp[n / 2] + 1;
                pre[n] = n / 2;
            }else{
                dp[n] = dp[n - 1] + 1;
                pre[n] = n - 1;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 2015;
        int[] dp = new int[n + 1];
        int[] pre = new int[n + 1];

        System.out.println(cal(n, dp, pre));

        System.out.println(Arrays.toString(dp));
    }

}
