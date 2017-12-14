package com.china.hadoop.bat.chapter6;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Dijkstra_ShortestPathFirst
 * @Package com.china.hadoop.bat.chapter6
 * @Description: 求加权图的最短路径
 * 将Prim算法稍做调整即得Dijkstra算法:
 * 1、结点集V初始化为源点S一个元素:V={S}，到 每个点的最短路径的距离初始化为 dist[u]=graph[S][u];
 * 2、选择最小的dist[u]:则v是当前找到的不在V中且 距离S最近的结点，更新V=V∪{v}，调整 dist[u]=min{dist[u],dist[v]+graph[v][u]};
 * 3、重复n-1次
 * @date 2017/10/25
 */
public class Dijkstra_ShortestPathFirst {

    /**
     * @Title: dijkstra
     * @Description: 通过dijkstra算法求spf最短路径
     * @param graph 加权图
     * @param origin 起点
     * @param spf 起点到每个点最短距离的数组
     * @return void
     * @throws
     * */
    public static void dijkstra(int[][] graph, int origin, int[] spf){
        spf = graph[origin];//初始化起点到每个点距离最短

        int n = graph.length;
        int minPath;//原点到某一个点最短距离--临时变量
        int k;//原点到某一个点最短距离的位置--临时变量

        boolean[] isArrived = new boolean[n];
        isArrived[origin] = true;//初始化遍历标志：起点已遍历

        for (int i = 1; i < n; i++) {//点的遍历顺序

            minPath = -1;
            k = -1;

            for (int j = 0; j < n; j++) {
                if(isArrived[j]){//跳过已访问的节点
                    continue;
                }
                if(minPath < 0 || minPath > spf[j]){//除了已访问的点，距离原点最近的未遍历过的点
                    minPath = spf[j];
                    k = j;
                }
            }

            isArrived[k] = true;//标记当前遍历点，已遍历过

            for (int j = 0; j < n; j++) {
                if(isArrived[j]){//跳过已遍历的点
                    continue;
                }

                //判断 起点到每一个点  与 起点"距离原点最近的未遍历过的点" + "距离原点最近的未遍历过的点"到每一个点距离，取最小
                spf[j] = Math.min(spf[j], spf[k] + graph[k][j]);
            }

        }

    }



}
