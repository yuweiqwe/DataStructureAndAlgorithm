package com.china.hadoop.bat.chapter9;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: MaxProfit
 * @Package com.china.hadoop.bat.chapter9
 * @Description: 股票最大收益
 * 给定数组A[0...N-1]，其中A[i]表示某股票第 i天的价格。如果允许最多只进行一次交易 (先买一次，再卖一次)，请计算何时买卖达 到最大收益，返回最大收益值。
 * 如:[7,1,5,3,6,4]，则最大收益为6-1=5。  如:[7,6,4,3,1]，则最大收益为0。
 * 一路下跌，则最好的方法是不进行交易。
 *
 * 若在第i天卖出，则应该在哪天买入更好?
 * 答:在A[0...i-1]的最小值处买入。
 * @date 2017/11/3
 */
public class MaxProfit {

    /**
     * @Title: calMaxProfit
     * @Description: 计算股票在什么时候买入和什么时候卖出收益最大
     * @param dayPrices
     * @param profits
     * @return int 最大收益
     * @throws
     * */
    public static int calMaxProfit(int[] dayPrices, int[] profits){
        int size = dayPrices.length;
        int max = 0;//股票最大收益

        int min = dayPrices[0];//股票价格最低的一天
        for (int i = 1; i < size; i++) {
            min = Math.min(min, dayPrices[i]);//股票价格最低的一天
            profits[i] = max = Math.max(max, dayPrices[i] - min);//今天最大收益
        }

        return max;
    }

    public static int calMaxProfit(int[] dayPrices, int k){
        int size = dayPrices.length;
        int[][] dp = new int[k + 1][size];//每天买股票的收益


        for (int i = 1; i < k; i++) {//k次
            for (int d = 1; d < size; d++) {//d天
                dp[k][d] = dp[k][d - 1];//初始化为d天不卖：收益为d-1天的值
                for (int j = 0; j < d; j++) {
                    //dp[k][d] 已计算出当天收益最大值，与k-1天卖收益+当天卖收益比较，取最大值
                    dp[k][d] = Math.max(dp[k][d], dp[k - 1][j] + dayPrices[d] - dayPrices[j]);//取当天卖与不卖的最大值
                }
            }
        }

        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }

        return dp[k][size - 1];
    }

    public static void main(String[] args) {
        int[] days = {7,1,5,3,6,4};
        int[] profits = new int[days.length];

        System.out.println(calMaxProfit(days, profits));

        System.out.println(Arrays.toString(profits));
        System.out.println("---------------");

        int k = 3;
        System.out.println(calMaxProfit(days, k));

    }

}
