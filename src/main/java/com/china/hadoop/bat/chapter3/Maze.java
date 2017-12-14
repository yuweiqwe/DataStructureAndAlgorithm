package com.china.hadoop.bat.chapter3;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Maze
 * @Package com.china.hadoop.bat.chapter3
 * @Description: 迷宫问题：老鼠吃奶酪
 * 一只老鼠位于迷宫左上角(0, 0)，迷宫中的数 字9处有块大奶酪。0表示墙，1表示可通过 路径。试给出一条可行的吃到奶酪的路径; 若没有返回空。
 * 假定迷宫是4连通的
 * @date 2017/10/15
 */
public class Maze {

    private static int[] iNext = new int[]{-1,1,0,0};
    private static int[] jNext = new int[]{0,0,-1,1};

    public static Queue<Pair> searchPath(int[][] maze, int i, int j){
        Queue<Pair> path = new LinkedBlockingQueue<>();

        int row = maze.length;
        int col = maze[0].length;

        boolean[][] visit = new boolean[row][col];

        if(maze[i][j] == 0){
            return path;
        }

        path.offer(new Pair(i, j));
        visit[i][j] = true;

        search(maze, i, j, path, visit);

        return path;
    }

    public static boolean search(int[][] maze, int i, int j, Queue<Pair> path, boolean[][] visit){
        if(maze[i][j] == 9){
            return true;
        }

        int row = maze.length;
        int col = maze[0].length;

        int rowNext,colNext;

        for (int k = 0; k < iNext.length; k++) {
            rowNext = i + iNext[k];
            colNext = j + jNext[k];

            if(rowNext < 0 || rowNext > row || colNext < 0 || colNext > col){
                continue;
            }

            if(maze[rowNext][colNext] == 0 || visit[rowNext][colNext] == true){
                continue;
            }

            path.offer(new Pair(rowNext, colNext));
            visit[rowNext][colNext] = true;

            if(search(maze, rowNext, colNext, path, visit)){
                return true;
            }

            path.poll();
            visit[rowNext][colNext] = false;
        }

        return false;
    }

    public static int[][] init(){
        int[][] maze = new int[][]{
                {1,1,0,0,0,0,0,0,0,0},
                {1,1,1,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,0,0,0,0,0,0,1,0},
                {0,1,1,1,0,1,0,0,1,0},
                {0,0,1,0,0,1,1,1,1,0},
                {0,0,1,0,0,0,0,0,1,0},
                {0,0,1,0,9,1,1,1,1,0},
                {0,0,1,1,1,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                };
        maze[0][0] = 1;

        for (int i = 1; i < maze.length; i++) {
            maze[0][i] = 0;
            maze[i][0] = 0;
        }

        maze[1][0] = maze[1][2] = maze[1][8] = 1;
        maze[2][1] = maze[2][2] = maze[2][3] = 1;

        return maze;
    }

    public static class Pair{
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[][] maze = init();

        Queue<Pair> queue = searchPath(maze, 0, 0);

        System.out.println(Arrays.toString(queue.toArray()));
    }

}
