package com.china.hadoop.bat.chapter6;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ArticulationPoint
 * @Package com.china.hadoop.bat.chapter6
 * @Description: 无向连通图的割点
 * 在无向连通图中，删除一个顶点v及其相连的边后，原图从一个连通分量变成了两个或多个连通分量，则称顶点v为割点，同时也称关节点(Articulation Point)。
 * 一个没有关节点的连通图称为重连通图(biconnected graph)。若在连通图上至少删去k 个顶点才能破坏图的连通性，则称此图的连通度为k。
 *
 * 关节点和重连通图在实际中较多应用。显然，一个表示通信网络的图的连通度越高，其系统越可靠，无论是哪一个站点出现故障或遭到外界破坏，都不影响系统的正常工作；
 * 又如，一个航空网若是重连通的，则当某条航线因天气等某种原因关闭时，旅客仍可从别的航线绕道而行；
 * 再如，若将大规模的集成电路的关键线路设计成重连通的话，则在某些元件失效的情况下，整个片子的功能不受影响，
 * 反之，在战争中，若要摧毁敌方的运输线，仅需破坏其运输网中的关节点即可。
 * @date 2017/10/24
 */
public class ArticulationPoint {

    /**
     * @Title: getArticulationPoint
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
    public static void getArticulationPoint(int[][] graph, int index, int parent, int root, int[] dfn, int[] low, Integer n, boolean[] flags){
        dfn[index] = index;//赋值dfs访问次序
        low[index] = index;//初始化节点访问次序为自己
        n++;

        int size = graph.length;
        int childrens = 0;//每个节点子树-删除本节点后，原图变成几个连通分量
        for (int j = 0; j < size; j++) {
            if(graph[index][j] != 0){//图连通
                if(dfn[j] == -1){//dfs遍历未遍历过
                    childrens++;

                    getArticulationPoint(graph, j, index, root, dfn, low, n, flags);

                    low[index] = Math.min(low[index], low[j]);

                    if(index != root && low[j] >= dfn[index]){
                        flags[index] = true;
                    }else if(index == root && childrens >= 2){
                        flags[index] = true;
                    }
                }else{
                    if(j != parent){
                        low[index] = Math.min(low[index], dfn[j]);//index的child中最小访问序号
                    }
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

        boolean[] flags = new boolean[graph.length];
        getArticulationPoint(graph, index, parent, root, dfn, low, n, flags);

        System.out.println(Arrays.toString(flags));
    }

}
