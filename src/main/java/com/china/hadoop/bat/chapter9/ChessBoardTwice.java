package com.china.hadoop.bat.chapter9;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ChessBoardTwice
 * @Package com.china.hadoop.bat.chapter9
 * @Description: 二次走棋盘
 * 给定m×n的矩阵，每个位臵为非负权值，机 器人从左上角开始，每次只能朝右和下，走 到达右下角。
 * 然后每次只能朝左和朝上，走 回左上角。求权值总和最大的路径。
 * 若相同格子走过两次，则该位臵权值只算一次
 *
 * 用dp[s,i,j]记录两次所走的路径获得的最大值，其中 s表示走的步数，i和j表示在s步后第1次走的位臵和 第2次走的位臵。
 * 由于s=m+n-2，0≤i<n，0≤j<m， 所以共有O(n3)个状态。
 * @date 2017/11/4
 */
public class ChessBoardTwice {
    
    public static int H = -10000;

    public static int calMinPath(int[][] chess, int n) {
        int[][][] dp = new int[2 * n][n][n];

        int t = 2 * n - 2;//最终步数
        int i,j,step;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                dp[0][i][j] = H;
            }
        }

        for (step = 1; step <= t; step++) {
            for (i = 0; i < n; i++) {
                for (j = i; j < n; j++) {
                    dp[step][i][j] = 0;
                    if(!isValid(step, i, j, n)){
                        continue;
                    }

                    if(i != j){//第一次和第二次不再同一个位置
                        dp[step][i][j] = Math.max(dp[step][i][j], getValue(dp, step - 1, i - 1, j - 1, n));
                        dp[step][i][j] = Math.max(dp[step][i][j], getValue(dp, step - 1, i - 1, j, n));
                        dp[step][i][j] = Math.max(dp[step][i][j], getValue(dp, step - 1, i, j - 1, n));
                        dp[step][i][j] = Math.max(dp[step][i][j], getValue(dp, step - 1, i, j, n));

                        dp[step][i][j] += chess[i][step - j] + chess[j][step - j];
                    }else{//同一个位置
                        dp[step][i][j] = Math.max(dp[step][i][j], getValue(dp, step - 1, i - 1, j - 1, n));
                        dp[step][i][j] = Math.max(dp[step][i][j], getValue(dp, step - 1, i - 1, j, n));
//                        dp[step][i][j] = Math.max(dp[step][i][j], getValue(dp, step - 1, i, j - 1, n));//等于上面等式，可以不需要计算
                        dp[step][i][j] = Math.max(dp[step][i][j], getValue(dp, step - 1, i, j, n));

                        dp[step][i][j] += chess[i][step - j];//同一个位置，只计算一次权值
                    }

                }
            }
        }

        return dp[t][n - 1][n - 1];
    }

    public static boolean isValid(int step, int x1, int x2, int n){
        int y1 = step - x1, y2 = step - x2;
        return x1 >= 0 && x1 < n && x2 >= 0 && x2 < n && y1 >=0 && y1 < n && y2 >= 0 && y2 < n;
    }

    public static int getValue(int[][][] dp, int step, int x1, int x2, int n) {
        return isValid(step, x1, x2, n) ? dp[step][x1][x2] : H;
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

        System.out.println(calMinPath(chess, 9));
    }

}