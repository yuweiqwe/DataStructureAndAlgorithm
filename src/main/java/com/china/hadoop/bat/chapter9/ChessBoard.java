package com.china.hadoop.bat.chapter9;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ChessBoard
 * @Package com.china.hadoop.bat.chapter9
 * @Description: 走棋盘
 * 给定m×n的矩阵，每个位臵是一个非负整数， 在左上角放一机器人，它每次只能朝右和下 走，直到右下角，求所有路径中，总和最小 的那条路径
 *
 * 走的方向决定了同一个格 子不会经过两次。
 * 若当前位于(x,y)处，它来自于哪些格子呢?
 * dp[0,0]=a[0,0] / 第一行(列)累积
 * dp[x,y] = min(dp[x-1,y]+a[x,y],dp[x,y-1]+a[x,y])  即:dp[x,y] = min(dp[x-1,y],dp[x,y-1]) +a[x,y]
 * 思考:若将上述问题改成“求从左上到右下 的最大路径”呢?
 *
 * 滚动数组:dp(j) = min[dp(j),dp(j-1)] + chess[i][j]
 *
 * @date 2017/11/4
 */
public class ChessBoard {

    public static int calMinPath(int[][] chess, int m, int n){
        int[] pathLength = new int[n];

        for (int i = 1; i < n; i++) {
            pathLength[i] = pathLength[i - 1] + chess[0][i];
        }

        for (int i = 1; i < m; i++) {
            pathLength[0] += chess[i][0];
            for (int j = 1; j < n; j++) {
                if(pathLength[j - 1] < pathLength[j]){
                    pathLength[j] = pathLength[j - 1] + chess[i][j];
                }else{
                    pathLength[j] += chess[i][j];
                }

            }
        }

        return pathLength[n - 1];
    }

    public static void main(String[] args) {
        int[][] chess = {
                {2,5,3,4,7,2,1,3,9},
                {2,5,3,4,7,2,1,3,9},
                {2,5,3,4,7,2,1,3,9},
                {2,5,3,4,7,2,1,3,9},
                {2,5,3,4,7,2,1,3,9},
                {2,5,3,4,7,2,1,3,9},
                {2,5,3,4,7,2,1,3,9},
                {2,5,3,4,7,2,1,3,9},
                {2,5,3,4,7,2,1,3,9},
        };

        System.out.println(calMinPath(chess, 9, 9));
    }

}
