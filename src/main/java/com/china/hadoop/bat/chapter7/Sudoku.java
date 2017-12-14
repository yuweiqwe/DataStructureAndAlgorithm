package com.china.hadoop.bat.chapter7;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Sudoku
 * @Package com.china.hadoop.bat.chapter6
 * @Description: 数独
 * 若当前位置是空格，则尝试从1到9的所有数; 如果对于1到9的某些数字，当前是合法的， 则继续尝试下一个位置——调用自身即可。
 * @date 2017/10/29
 */
public class Sudoku {

    /**
     * @Title: jump
     * @Description: 递归-深度优先搜索-回溯
     * @param n 数独阶数
     * @param row 当前此次要填写位置的行
     * @param column 当前此次要填写位置的列
     * @param result 当前数独结果
     * @param totalResult 总解
     * @return void
     * @throws
     * */
    public static void backTrace(int n, int row, int column, int[][] result, List<int[][]> totalResult){
        if(row == 8 && column >= 9){//当达到最后一个位置时,row=8 column=8，此时继续计算column=9，表示已经得到数独的解，需要结束
            Arrays.stream(result).forEach(array -> System.out.println(Arrays.toString(array)));
            System.out.println("------------------");
            return;
        }

        if(column == 9){//当达到column=9时，需要换行，同时重置column=0
            row++;
            column = 0;
        }

        if(result[row][column] != 0){//当此格数不是0，直接填写下一个位置数
            backTrace(n, row, column + 1, result, totalResult);
            return;
        }

        for (int i = 1; i <= 9; i++) {

            if(place(n, row, column, i, result)){//判断是否可以放置i这个数
                result[row][column] = i;
                backTrace(n, row, column + 1, result, totalResult);//计算下一个位置的数
                result[row][column] = 0;//重置-回溯
            }

        }

    }

    private static boolean place(int n, int row, int column, int value, int[][] result) {
        int x = row/3;//方框的行位置
        int y = column/3;//方框的列位置

        for (int i = 0; i < n; i++) {
            if(i != column && result[row][i] == value){//同一行是否合法
                return false;
            }

            if(i != row && result[i][column] == value){//同一列是否合法
                return false;
            }

            // 方框内： i/3=第几行  i%3=第几列
            if(result[x*3 + i/3][y*3 + i%3] == value){//相同方框是否合法
                return false;
            }

        }

        return true;
    }

    public static void main(String[] args) {
        int n = 9;
        int row = 0;
        int column = 0;

        int[][] result = new int[][]{
                {1,0,0,0,2,0,0,0,3},
                {0,0,0,0,0,0,0,5,0},
                {0,0,2,0,0,0,1,0,0},
                {3,0,0,0,0,0,0,7,0},
                {0,2,0,0,5,0,8,0,0},
                {0,0,4,0,0,0,0,0,0},
                {0,0,1,0,6,0,4,0,0},
                {0,3,0,0,0,0,0,1,0},
                {4,0,0,0,3,0,0,0,9}
        };

        backTrace(n, row, column, result, null);

    }

}
