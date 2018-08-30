package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: InsertionSort
 * @Package com.algorithm.sort
 * @Description: 插入排序：是一种简单直观的排序算法。
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
 * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 *
 * 算法描述：
 * <br/>从第一个元素开始，该元素可以认为已经被排序；
 * <br/>取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * <br/>如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * <br/>重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * <br/>将新元素插入到该位置后；
 * <br/>重复步骤2~5。
 *
 * 算法分析：最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 *
 * @date 2018/3/7
 */
public class InsertionSort {

    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        int length = array.length;

        for (int i = 1; i < length; i++) {
            sort(i, array);
            //sort1(i, array);
        }

    }

    private static <T extends Comparable<T>> void sort(int i, T[] unsorted) {
        for (int j = i; j > 0 ; j--) {
            T cur = unsorted[j];
            T pre = unsorted[j - 1];
            if(cur.compareTo(pre) < 0){
                unsorted[j - 1] = cur;
                unsorted[j] = pre;
            }else{
                break;
            }
        }
    }

    private static <T extends Comparable<T>> void sort1(int i, T[] unsorted) {
        int j;
        T cur = unsorted[i];
        for (j = i; j > 0 ; j--) {
            T pre = unsorted[j - 1];
            if(cur.compareTo(pre) < 0){
                unsorted[j] = pre;
            }else{
                break;
            }
        }
        unsorted[j] = cur;
    }

    public static void insertionSort1(Integer[] array) {
        int length = array.length;

        int cur;
        int preIndex;
        for (int i = 1; i < length; i++) {
            cur = array[i];
            for (preIndex = i - 1; preIndex >= 0; preIndex--) {
                if(array[preIndex] > cur){
                    array[preIndex + 1] = array[preIndex];
                }else{
                    break;
                }
            }
            array[preIndex + 1] = cur;
        }

    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{5,3,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

}
