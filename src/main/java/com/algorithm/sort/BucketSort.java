package com.algorithm.sort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BucketSort
 * @Package com.algorithm.sort
 * @Description: 桶排序:是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。
 * 桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，
 * 每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排
 *
 * 算法描述：
 * <br/>设置一个定量的数组当作空桶；
 * <br/>遍历输入数据，并且把数据一个一个放到对应的桶里去；
 * <br/>对每个不是空的桶进行排序；
 * <br/>从不是空的桶里把排好序的数据拼接起来。
 *
 * 算法分析：
 *
 * @date 2018/3/9
 */
public class BucketSort {

    public static void bucketSort(Double[] a) {
        int n = a.length;

        /**
         * 创建链表（桶）集合并初始化，集合中的链表用于存放相应的元素
         */
        int bucketNum = 10; // 桶数
        LinkedList<LinkedList<Double>> buckets = new LinkedList<LinkedList<Double>>();
        for(int i = 0; i < bucketNum; i++){
            LinkedList<Double> bucket = new LinkedList<Double>();
            buckets.add(bucket);
        }
        // 把元素放进相应的桶中--桶之间本身也是有顺序的
        for(int i = 0; i < n; i++){
            int index = (int) (a[i] / bucketNum);
            buckets.get(index).add(a[i]);
        }
        // 对每个桶中的元素排序，并放进a中
        int index = 0;
        for (LinkedList<Double> linkedList : buckets) {
            int size = linkedList.size();
            if (size == 0) {
                continue;
            }
            /**
             * 把LinkedList<Double>转化为Double[]的原因是，之前已经实现了
             * 对数组进行排序的算法
             */
            Double[] temp = new Double[size];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = linkedList.get(i);
            }
            // 利用插入排序对temp排序
            InsertionSort.insertionSort(temp);
            for (int i = 0; i < temp.length; i++) {
                a[index] = temp[i];
                index++;
            }
        }

    }

    public static void main(String[] args) {
        Double[] array = new Double[]{3.0,5.0,7.0,11.0,9.0,3.0,16.0,1.0,88.0,52.0};
        System.out.println(Arrays.toString(array));
        bucketSort(array);
        System.out.println(Arrays.toString(array));
    }

}
