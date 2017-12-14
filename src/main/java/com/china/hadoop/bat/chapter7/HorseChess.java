package com.china.hadoop.bat.chapter7;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: HorseChess
 * @Package com.china.hadoop.bat.chapter7
 * @Description: 马踏棋盘
 * 给定m×n的棋盘，将棋子“马”放在任意位 置上，按照走棋规则将“马”移动，要求每 个方格只能进入一次，最终使得“马”走遍 棋盘的所有位置。
 * 如给定8×8的国际象棋棋盘(右) 如给定8×9的中国象棋棋盘(左)
 *
 * 方法：
 * 1、深度优先搜索 显然，如果从A点能够跳到B点，则从B点也 能够跳到A点。所以，马的起始位置可以从 任意一点开始，不妨从左上角开始。
 * 若当前位置为(i,j)，则遍历(i,j)的八邻域，如 果邻域尚未经过，则跳转。
 *
 * 2、若棋盘规模较大，则在较深的棋位才能发现“无路 可走”而不得不回溯。
 * 贪心的启发式策略:最多情况下，每个棋位有8个后继。由于棋盘边界和已经遍历的原因，往往是少于8个的。
 * 当前棋位可以跳转的后继棋位记为x个，这x个棋位的后继棋位数目记做h1h2...hx，优先选择最小的hi。
 * 策略:优先选择孙结点数目最少的那个子结点
 * 原因:孙结点最少的子结点，如果当前不跳转则最容易 在后期无法跳转
 *
 * @date 2017/10/31
 */
public class HorseChess {

    public static int row = 8;
    public static int column = 9;

    public static int[] offsetX = new int[]{-2,-2,-1,+1,+2,+2,+1,-1};//马能跳的位置x偏移量
    public static int[] offsetY = new int[]{-1,+1,+2,+2,+1,-1,-2,-2};//马能跳的位置y偏移量

    /**
     * @Title: jump
     * @Description: 马踏棋盘
     * @param chess 棋盘-初始化值0步
     * @param i 横坐标
     * @param j 纵坐标
     * @param step 当前已跳步数
     * @return boolean
     * @throws
     * */
    public static boolean jump(int[][] chess, int i, int j, int step){
        //如果最后一步已经走了，结束
        if(step == row * column){
            return true;
        }

        int nextX;
        int nextY;

        //依次遍历8个可走位置
        for (int pos = 0; pos < 8; pos++) {
            nextX = i + offsetX[pos];//下一步X坐标
            nextY = j + offsetY[pos];//下一步Y坐标

            if(canJump(chess, nextX, nextY)){//是否可以跳
                chess[nextX][nextY] = step + 1;//使用步数来标记已跳
                if(jump(chess, nextX, nextY, step + 1)){//进行下一跳
                    return true;
                }
                chess[nextX][nextY] = 0;//失败，回溯
            }

        }

        return false;
    }

    public static boolean canJump(int[][] chess, int i, int j){
        if((i < 0 || i >= row || (j < 0 || j >= column))) {
            return false;
        }
        return chess[i][j] == 0;
    }

    public static boolean jump2(int[][] chess, int i, int j, int step) {
        //如果最后一步已经走了，结束
        if(step == row * column){
            return true;
        }

        List<NextPosition> nextPositions = gatherHorseDirect(chess, i, j, step == row * column -1);

        int nextX;
        int nextY;
        int direct;
        for (int pos = 0; pos < nextPositions.size(); pos++) {
            direct = nextPositions.get(pos).getDirect();

            nextX = i + offsetX[direct];//下一步X坐标
            nextY = j + offsetY[direct];//下一步Y坐标
            chess[nextX][nextY] = step + 1;//使用步数来标记已跳
            if(jump2(chess, nextX, nextY, step + 1)){//进行下一跳
                return true;
            }
            chess[nextX][nextY] = 0;//失败，回溯

        }

        return false;
    }

    public static List<NextPosition> gatherHorseDirect(int[][] chess, int i, int j, boolean isLast){
        List<NextPosition> nextPositions = Lists.newArrayListWithCapacity(offsetX.length);

        int nextX;
        int nextY;
        NextPosition nextPosition;
        for (int d = 0; d < offsetX.length; d++) {
            nextX = i + offsetX[d];//下一步X坐标
            nextY = j + offsetY[d];//下一步Y坐标
            if(canJump(chess, nextX, nextY)) {
                if (isLast) {
                    nextPosition = new NextPosition(d, 1);
                    nextPositions.add(nextPosition);
                    break;
                }

                nextPosition = getNextPosition(chess, nextX, nextY, d);
                if (nextPosition != null) {
                    nextPositions.add(nextPosition);
                }
            }
        }

        nextPositions.sort((o1, o2) -> o1.getValidStep() > o2.getValidStep() ? 1 : -1);

        return nextPositions;
    }

    public static NextPosition getNextPosition(int[][] chess, int nextX, int nextY, int direct){
        int sonX;
        int sonY;
        int count = 0;
        for (int d = 0; d < offsetX.length; d++) {
            sonX = nextX + offsetX[d];//下一步X坐标
            sonY = nextY + offsetY[d];//下一步Y坐标

            if(canJump(chess, sonX, sonY)){
                count++;
            }
        }

        return new NextPosition(direct, count);
    }

    public static void main(String[] args) {
        int[][] chess = new int[row][column];
        chess[0][0] = 1;

        jump2(chess, 0, 0, 1);

        for (int[] ints : chess) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static class NextPosition {

        private int direct;//下一跳的方向
        private int validStep;//下一跳有多少合法位置可走

        public NextPosition(int direct, int validStep) {
            this.direct = direct;
            this.validStep = validStep;
        }

        public int getDirect() {
            return direct;
        }

        public void setDirect(int direct) {
            this.direct = direct;
        }

        public int getValidStep() {
            return validStep;
        }

        public void setValidStep(int validStep) {
            this.validStep = validStep;
        }

    }

}
