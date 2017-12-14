package com.china.hadoop.bat.chapter7;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: EightQueen
 * @Package com.china.hadoop.bat.chapter6
 * @Description: 八皇后问题
 * 问题：在国际象棋棋盘上（8*8）放置八个皇后，使得任意两个皇后之间不能在同一行，同一列，也不能位于同于对角线上。问共有多少种不同的方法，并且指出各种不同的放法。
 *
 * @date 2017/10/29
 */
public class EightQueen {

    /**
     * @Title: recursion
     * @Description: 递归算法
     * 算法思路：
    　　首先我们分析一下问题的解，我们每取出一个皇后，放入一行，共有八种不同的放法，然后再放第二个皇后，同样如果不考虑规则，还是有八种放法。于是我们可以用一个八叉树来描述这个过程。从根节点开始，树每增加一层，便是多放一个皇后，直到第8层（根节点为0层），最后得到一个完全八叉树。　　
    　　紧接着我们开始用深度优先遍历这个八叉树，在遍历的过程中，进行相应的条件的判断。以便去掉不合规则的子树。
    　　那么具体用什么条件来进行子树的裁剪呢？
    　　我们先对问题解的结构做一个约定。
    　　用X[i]来表示，在第i行，皇后放在了X[i]这个位置。
    　　于是我们考虑第一个条件，不能再同一行，同一列于是我们得到x[i]不能相同。剩下一个条件是不能位于对角线上，这个条件不是很明显，我们经过分析得到，设两个不同的皇后分别在j，k行上，x[j],x[k]分别表示在j,k行的那一列上。那么不在同一对角线的条件可以写为abs((j-k))!=abs(x[j]-x[k])，其中abs为求绝对值的函数。
    　　于是下面我们便可以利用一个递归的调用来遍历八叉树。
     * @param curNum 当前要放置queen的行数
     * @param queenNum 要放置queen总数
     * @param result 每一个放置的queen所在列
     * @param totalResult 总共解
     * @return void
     * */
    public static void recursion(int curNum, int queenNum, int[] result, List<int[]> totalResult){
        if(curNum == queenNum){
            int[] dest = new int[queenNum];
            System.arraycopy(result, 0, dest, 0, queenNum);

            totalResult.add(dest);
        }else{
            for (int i = 0; i < queenNum; i++) {//放置curNum行的queen，总共有queenNum种放法：每一列一种
                result[curNum] = i;//将queen放置在i列

                if(place(curNum, result)){//判断curNum行，i列 是否能放置queen
                    // 注释：这里不能使用 ++curNum，如果使用了，回溯时curNum被修改了；curNum+1使用新值，对原curNum没有影响
                    recursion(curNum + 1, queenNum, result, totalResult);//计算下一个queen放置位置，
                }

            }
        }

    }

    public static boolean place(int curNum, int[] result){
        for (int i = 0; i < curNum; i++) {
            if(Math.abs(result[i] - result[curNum]) == Math.abs(i - curNum)//放置位置 不能在同一斜线
                    || result[i] == result[curNum]){//放置位置 不能在同一列
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int curNum = 0;
        int queenNum = 8;
        int[] result = new int[queenNum];
        List<int[]> totalResult = Lists.newArrayList();

        recursion(curNum, queenNum, result, totalResult);

        System.out.println(totalResult.size());

        for (int[] one : totalResult) {
            System.out.println(Arrays.toString(one));
        }
    }

}
