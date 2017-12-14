package com.china.hadoop.bat.chapter4;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: NumberMaxGap
 * @Package com.china.hadoop.bat.chapter4
 * @Description: 数组的最大间隔
 * 给定整数数组A[0...N-1]，求这N个数排序后 最大间隔。如:1,7,14,9,4,13的最大间隔为4。排序后:1,4,7,9,13,14，最大间隔是13-9=4
 * 显然，对原数组排序，然后求后项减前项的最大值，即为解。
 * 可否有更好的方法?
 * 分析：
 * 假定N个数的最大最小值为max，min，则这 N个数形成N-1个间隔，其最小值是 (max-min)/N-1
 * 如果N个数完全均匀分布，则间距全部是 (max-min)/N-1 且最小;如果N个数不是均匀分布，间距不均衡，则最大间距必然大于(max-min)/N-1
 *
 * 思路:将N个数用间距(max-min)/N-1分成N-1个区间,则落在同一区间内的数不可能有最大间距。统计后一区间的最小值与前一区间的最大值的差即可。
 * 若没有任何数落在某区间，则该区间无效，不 参与统计。
 * 显然，这是借鉴桶排序/Hash映射的思想。
 * @date 2017/10/17
 */
public class NumberMaxGap {

    public static int calMaxGap(int[] a){
        int size = a.length;
        int max = a[0];
        int min = a[0];
        //求最大值和最小值
        for (int i : a) {
            if(i > max){
                max = i;
            }else if(i < min){
                min = i;
            }
        }

        //根据a数组的长度，计算分桶长度、桶数目
        int maxLen = max - min;
        int averageGap = maxLen / a.length;

        //存储一个桶中最大值和最小值
        Bucket[] buckets = new Bucket[size];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }

        int len = 0;
        int bucketIndex = 0;
        for (int i : a) {
            len = i - min;
            bucketIndex = len * size / maxLen;

            if(bucketIndex >= size){
                bucketIndex = size - 1;
            }

            buckets[bucketIndex].put(i);
        }

        //计算最大间隙gap
        int s = 0;//Bucket前一个
        int gap = 0;//2个桶之间 间隙--临时变量
        int maxGap = averageGap;//最大间隔


        for (int i = 1; i < buckets.length; i++) {
            if(!buckets[i].isValid()){
                continue;
            }

            gap = buckets[i].getMin() - buckets[s].getMax();
            if(gap > maxGap){
                maxGap = gap;
            }
            s = i;
        }

        return maxGap;
    }

    public static void main(String[] args) {
        int[] a = new int[]{8,11,5,61,9,11,55,33,24};
        System.out.println(calMaxGap(a));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static class Bucket{
        private boolean isValid;
        private int max;
        private int min;

        public Bucket() {
            isValid = false;
        }

        public void put(int i){
            if(!isValid){
                max = min = i;
                isValid = true;
            }else{
                if(i >= max){
                    max = i;
                }else{
                    min = i;
                }
            }
        }

        public boolean isValid() {
            return isValid;
        }

        public int getMax() {
            return max;
        }

        public int getMin() {
            return min;
        }
    }

}
