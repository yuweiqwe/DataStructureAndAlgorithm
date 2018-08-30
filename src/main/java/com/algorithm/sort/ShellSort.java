package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ShellSort
 * @Package com.algorithm.sort
 * @Description: 希尔排序：第一个突破O(n^2)的排序算法，是简单插入排序的改进版
 * 它会优先比较距离较远的元素，希尔排序又叫缩小增量排序。
 * 希尔排序的核心在于间隔序列的设定。既可以提前设定好间隔序列，也可以动态的定义间隔序列。
 *
 * 算法描述：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序
 * <br/>选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * <br/>按增量序列个数k，对序列进行k 趟排序；
 * <br/>每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
 * <br/>仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 *
 * 算法分析：最佳情况：T(n) = O(n)  最坏情况：T(n) = O(n^2)  平均情况：T(n) =O(nlog2 n)　
 *
 * @date 2018/3/8
 */
public class ShellSort {

    public static final int FACTOR = 3;

    public static <T extends Comparable<T>> void shellSort(T[] array) {
        int length = array.length;
        int gap = getDynamicGap(length);

        while(gap >= 1){
            for (int i = gap; i < length; i++) {
                sort(i, gap, array);
            }

            gap = gap / FACTOR;
        }

    }

    private static <T extends Comparable<T>> void sort(int i, int gap, T[] unsorted) {
        for (int j = i - gap; j >= 0 ; j = j - gap) {
            T cur = unsorted[j + gap];
            T pre = unsorted[j];
            if(cur.compareTo(pre) < 0){
                unsorted[j] = cur;
                unsorted[j + gap] = pre;
            }else{
                break;
            }
        }
    }

    private static <T extends Comparable<T>> void sort1(int i, int gap, T[] unsorted) {
        int j;
        T cur = unsorted[i];
        for (j = i - gap; j >= 0 ;  j = j - gap) {
            T pre = unsorted[j];
            if(cur.compareTo(pre) < 0){
                unsorted[j + gap] = pre;
            }else{
                break;
            }
        }
        unsorted[j + gap] = cur;
    }

    public static void shellSort1(Integer[] array) {
        int length = array.length;
        int gap = getDynamicGap(length);

        int cur;
        int preIndex;
        while(gap >= 1){
            for (int i = gap; i < length; i++) {
                cur = array[i];
                for (preIndex = i - gap; preIndex >= 0; preIndex = preIndex - gap) {
                    if(array[preIndex] > cur){
                        array[preIndex + gap] = array[preIndex];
                    }else{
                        break;
                    }
                }

                array[preIndex + gap] = cur;
            }

            gap = gap / FACTOR;
        }

    }

    public static int getDynamicGap(int length){
        int gap = 1;
        while(gap < length / FACTOR){
            gap = gap * FACTOR + 1;
        }
        return gap;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{5,3,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }

}
