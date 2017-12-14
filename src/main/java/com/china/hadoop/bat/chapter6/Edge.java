package com.china.hadoop.bat.chapter6;

import lombok.Data;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Edge
 * @Package com.china.hadoop.bat.chapter6
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2017/10/28
 */
@Data
public class Edge implements Comparable<Edge> {

    private int x;
    private int y;
    private double weight;

    public Edge(int x, int y, double weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight > o.weight ? 1 : -1;
    }

}
