package com.china.hadoop.bat.chapter6;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: HeuristicAlgorithm_AStar
 * @Package com.china.hadoop.bat.chapter6
 * @Description: 启发式搜索-A*搜索
 * 给定有向图G，求从S到E的最短路径。
 * 思考:从S到E的路径探索中，假定当 前位于结点i，则与i相连的结点中，应 选择哪个?
 * f[j]= g[j]+h[j]
 * g[j]:从起始结点S到当前结点j的距离(一直处于迭代更新中)
 * 定义 h[j]:从当前结点j到终止结点E的距离则。
 * 其中h是启发函数,h[j]也叫启发值,启发值是一个理想值
 * h'[j]我们称之为j节点到终点E的实际值
 * h[j]和h'[j]关系：h[j] <= h'[j]，一般来说启发式算法中，启发值要比实际值才能得出最优化解，但是也不要小太多，一个合理的启发函数很重要
 * 比如说：图中可以是2点的直线距离、树中可以是height
 *
 * 结点分成三类:活跃结点、待计算结点和计算完成 结点。
 * 1、每次从活跃结点集合中取出f值最小的结点j
 * 2、对于j的相邻结点k，更新g[k]=min(g[k], g[k]>g[j]+graph[j][k]) ；更新f[k]=g[k]+h[k]；k归类到活跃结点
 * 3、j归类到计算完成结点
 * 注意:某结点即使是计算完成结点，仍然有可能因为g[k]的更新回到活跃结点。
 * @date 2017/10/27
 */
public class HeuristicAlgorithm_AStar {

    public static int[] aStar(double[][] graph, int start, int end){
        int length = graph.length;

        //状态空间：存储每个点的状态；其中有3个状态，表示3个状态空间：init状态空间、open状态空间、close状态空间
        //init状态 0：表示节点当前不可达
        //open状态 1：表示节点当前可达，待计算
        //close状态 2：表示节点当前可达，并且计算完毕
        int[] statusSpace = new int[length];

        double[] f = new double[length];
        double[] g = new double[length];
        double[] h = new double[length];

        int[] path = new int[length];//路径

        List<Integer> open = Lists.newArrayList();

        //初始化
        for (int i = 0; i < h.length; i++) {
            g[i] = graph[start][i];//实际距离
            h[i] = getStraightDistance(start, start, i, i);
            f[i] = g[i] + h[i];
            statusSpace[i] = 0;

            path[i] = -1;
        }

        statusSpace[start] = 1;
        g[start] = 0;
        f[start] = g[start];
        open.add(start);
        path[start] = start;

        double min = -1;
        int pos = start;
        while(pos != end){//遍历open

            min = -1;//每一个节点第一次遍历的时候，取f[i]
            //找到open中起点到某节点最短距离的节点
            for (int i = 0; i < length; i++) {

                if (statusSpace[i] != 1) {
                    continue;
                }

                if (min == -1 || min > f[i]) {
                    min = f[i];
                    pos = i;
                }
            }

            //从pos位置，遍历所有能到达的点，把init初始状态的节点，放到open中，表示可到达（待计算）
            //通过open空间中的点，来优化迭代缩小其他点
            for (int j = 0; j < length; j++) {
                if(graph[pos][j] >= H){//路径不通
                    continue;
                }

                if(statusSpace[j] == 0){//init状态：加入到open中，并且判断g值，是否更新
                    if(g[j] > g[pos] + graph[pos][j]){
                        g[j] = g[pos] + graph[pos][j];
                        f[j] = g[j] + h[j];
                    }
                    statusSpace[j] = 1;
                    path[j] = pos;
                }else if(statusSpace[j] == 2){//close状态
                    if(g[j] > g[pos] + graph[pos][j]){
                        g[j] = g[pos] + graph[pos][j];
                        f[j] = g[j] + h[j];
                        statusSpace[j] = 1;
                        path[j] = pos;
                    }
                }else if(statusSpace[j] == 1){//open状态
                    if(g[j] > g[pos] + graph[pos][j]){
                        g[j] = g[pos] + graph[pos][j];
                        f[j] = g[j] + h[j];
                        path[j] = pos;
                    }
                }

            }

            statusSpace[pos] = 2;//pos位置节点的状态open -> close
        }

        return path;
    }

    public static double[] calHeuristaic(double[][] graph, int start){
        //初始化，根据启发函数计算h(i)启发值
        double[] h = new double[graph.length];
        for (int i = 0; i < h.length; i++) {
            h[i] = getStraightDistance(start, start, i, i);
        }

        return h;
    }

    /**
     * @Title: getStraightDistance
     * @Description: 计算2点之间直线距离
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @return 距离
     * @throws
     * */
    public static double getStraightDistance(int x, int y, int x1, int y1){
        double distance = Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
        return distance;
    }
    
    public static double H = Double.POSITIVE_INFINITY;

    public static void main(String[] args) {
        System.out.println(getStraightDistance(1, 1, 2, 2));

        double[][] graph = new double[][]{
                {H,H,1,H,H,H},
                {H,H,H,H,1,5},
                {H,1,H,H,H,6},
                {H,H,H,H,H,1},
                {H,H,H,1,H,H},
                {H,1,H,H,H,H},
        };

        int start = 0;
        int end = 5;
        int[] pre =aStar(graph, 0, 5);

        System.out.println(Arrays.toString(pre));

        StringBuilder path = new StringBuilder();
        path.append(end);

        int t = end;
        while(path.length() != pre.length){
            t = pre[t];
            path.append(t);
        }

        System.out.println(path.reverse());
    }

}
