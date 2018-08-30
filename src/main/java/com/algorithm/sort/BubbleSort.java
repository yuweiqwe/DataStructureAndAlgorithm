package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BubbleSort
 * @Package com.algorithm.sort
 * @Description: 冒泡排序 :冒泡排序是一种简单的排序算法。
 * 它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 *
 * 算法描述：
 * <br/>比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * <br/>对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * <br/>针对所有的元素重复以上的步骤，除了最后一个；
 * <br/>重复步骤1~3，直到排序完成。
 *
 * 算法分析：最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 *
 * @date 2018/3/7
 */
public class BubbleSort {

    public static <T extends Comparable<T>> void bubbleSort(T[] array){
        int length = array.length;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if(array[j].compareTo(array[j + 1]) > 0){
                    T t = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = t;
                }

            }
        }

    }

    public static void bubbleSort1(int[] array){
        int length = array.length;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if(array[j] > array[j + 1]){
                    int t = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = t;
                }

            }
        }

    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{3,5,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

}
