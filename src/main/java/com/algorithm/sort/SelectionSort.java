package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: SelectionSort
 * @Package com.algorithm.sort
 * @Description: 选择排序:表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好
 * 选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * 算法描述：
 * <br/>初始状态：无序区为R[1..n]，有序区为空；
 * <br/>第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，
 * 将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
 * <br/>n-1趟结束，数组有序化了。
 *
 * 算法分析：最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)
 *
 * @date 2018/3/7
 */
public class SelectionSort {

    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        int length = array.length;

        int min = 0;
        for (int i = 0; i < length; i++) {
            min = i;
            for (int j = i + 1; j < length; j++) {
                if(array[min].compareTo(array[j]) > 0){
                    min = j;
                }
            }

            swap(array, min, i);
        }

    }

    public static void selectionSort1(Integer[] array) {
        int length = array.length;

        int min = 0;
        for (int i = 0; i < length; i++) {
            min = i;
            for (int j = i + 1; j < length; j++) {
                if(array[min].compareTo(array[j]) > 0){
                    min = j;
                }
            }

            swap(array, min, i);
        }

    }

    public static <T extends Comparable<T>> void swap(T[] array, int source, int target) {
        T t = array[source];
        array[source] = array[target];
        array[target] = t;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{3,5,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }


}
