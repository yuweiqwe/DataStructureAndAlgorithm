package com.china.hadoop.bat.chapter6;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: MST_Prim
 * @Package com.china.hadoop.bat.chapter6
 * @Description: 最小生成树MST Prim算法
 * 最小生成树要求从一个带权无向连通图中选择n-1 条边并使这个图仍然连通(也即得到了一棵生成树)， 同时还要考虑使树的权最小。MST最著名的是Prim 算法和Kruskal算法，它们都是贪心算法。
 * Prim算法:从某个(任意一个)结点出发，选择与该结点邻 接的权值最小的边;随着结点的不断加入，每次都选择 这些结点发出的边中权值最小的:重复n-1次。
 * Kruskal算法:将边按照权值递增排序，每次选择权值最 小并且不构成环的边，重复n-1次。
 *
 * Prim算法：
 * 首先以一个结点作为最小生成树的初始结点， 然后以迭代的方式找出与最小生成树中各结 点权重最小边，并加入到最小生成树中。
 * 加入之后如果产生回路则跳过这条边，选择下 一个结点。
 * 当所有结点都加入到最小生成树 中之后，就找出了连通图中的最小生成树了。
 * @date 2017/10/28
 */
public class MST_Prim {

    public static double H = Double.POSITIVE_INFINITY;

    public static boolean prim(double[][] graph, List<Edge> result){
        int start = 0;
        int n = graph.length;

        List<Edge> totalEdges = Lists.newArrayList();
        boolean[] status = new boolean[n];//每个节点的状态：表示节点是否被加入树中

        addEdge(graph, totalEdges, start, status);//将start为起点的边全部放入totalEdges

        Edge minEdge;
        while(result.size() != graph.length){
            if(totalEdges.isEmpty()){//没有边了，结束
                return false;
            }

            minEdge = getMinEdge(totalEdges);//找到权值最小的边
            result.add(minEdge);//将边放入mst中
            status[start++] = true;//标识节点已经被加入到mst中了
            delete(totalEdges, minEdge.getY());//删除totalEdges中所以以minEdge的终点为终点的边,防止形成环
            addEdge(graph, totalEdges, minEdge.getY(), status);//将minEdge的终点为起点的所有边加入totalEdges中
        }

        return true;
    }

    private static void delete(List<Edge> totalEdges, int to){

    }

    private static Edge getMinEdge(List<Edge> totalEdges){
        totalEdges.sort((o1, o2) -> o1.getWeight() > o2.getWeight() ? 1 : -1);

        return totalEdges.get(0);
    }

    private static void addEdge(double[][] graph, List<Edge> totalEdges, int from, boolean[] status){
        for (int j = 0; j < graph.length; j++) {
            if(graph[from][j] != Double.POSITIVE_INFINITY
                    && !status[j]){
                totalEdges.add(new Edge(from, j, graph[from][j]));
            }
        }
    }


}
