package com.china.hadoop.bat.chapter6;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ArticulationEdge
 * @Package com.china.hadoop.bat.chapter6
 * @Description: 无向连通图的割边
 * 给定某无向连通图G，若删除某边E，则图G 变成非连通图，则边E称为图G的割边
 * @date 2017/10/25
 */
public class ArticulationEdge {

    /**
     * @Title: getArticulationEdge
     * @Description: 获取无向连通图的割点
     * 序号--节点的排序号--数组行号
     * 次序号--dfs遍历访问次序
     * 该算法是R.Tarjan发明的。观察DFS搜索树，我们可以发现有两类节点可以成为割点：
     *  1、对根节点u，若其有两棵或两棵以上的子树，则该根结点u为割点；
     *  2、对非叶子节点u（非根节点），若其子树的节点均没有指向u的祖先节点的回边，说明删除u之后，根结点与u的子树的节点不再连通；则节点u为割点。
     * 对于根结点，显然很好处理；但是对于非叶子节点，怎么去判断有没有回边是一个值得深思的问题。
     * 我们用dfn[u]记录节点u在DFS过程中被遍历到的次序号，low[u]记录节点u或u的子树通过非父子边追溯到最早的祖先节点（即DFS次序号最小），
     * 那么low[u]的计算过程如下：
     * low[u]={min{low[u], low[v]}  (u,v)为树边
     * low[u]=min{low[u], dfn[v]}   (u,v)为回边且v不为u的父亲节点
     *
     * @param graph 图的数组表示
     * @param index 图中下一个访问的节点的序号--按照顺序排列--行数
     * @param parent 图中下一个访问的节点的父节点的序号
     * @param root 图的第一个节点的序号--自定义
     * @param dfn 每个节点通过dfs遍历访问次序的数组
     * @param low 每个节点不经过父节点 能回溯到的节点中的最小的次序号（排序）
     * @param n 当前节点访问序号
     * @param flags 节点是否割点的标志数组
     * @return void
     * @throws
     * */
    public static void getArticulationEdge(int[][] graph, int index, int parent, int root, int[] dfn, int[] low, Integer n, boolean[][] flags){
        dfn[index] = index;
        low[index] = index;

        int size = graph.length;
        int childrens = 0;
        for (int j = 0; j < size; j++) {
            if(graph[index][j] != 0){
                if(dfn[j] == -1){
                    childrens++;

                    getArticulationEdge(graph, j, index, root, dfn, low, n, flags);

                    low[index] = Math.min(low[index], low[j]);

                    if(low[j] > low[index]){
                        flags[index][j] = true;
                    }

                }else if(j != parent){
                    low[index] = Math.min(low[index], dfn[j]);
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0,0,1,0,0,0},
                {0,0,0,0,1,0},
                {0,1,0,0,0,0},
                {0,0,0,0,0,1},
                {0,0,0,1,0,0},
                {0,1,0,0,0,0},
        };

        int index = 0;
        int parent = 0;
        int root = 0;
        int[] dfn = new int[graph.length];
        int[] low = new int[graph.length];
        int n = 0;

        for (int i = 0; i < dfn.length; i++) {
            dfn[i] = -1;
        }

        boolean[][] flags = new boolean[graph.length][graph[0].length];
        getArticulationEdge(graph, index, parent, root, dfn, low, n, flags);

        for (boolean[] flag : flags) {
            System.out.println(Arrays.toString(flag));
        }
    }

}
