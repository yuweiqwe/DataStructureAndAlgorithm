package com.china.hadoop.bat.chapter8;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ShellSort
 * @Package com.china.hadoop.bat.chapter8
 * @Description: 希尔排序
 * @date 2017/11/2
 */
public class ShellSort {

    public static void sort(int[] array){
        int size = array.length;
        int gap = 1;

        while(gap < size / 3){
            gap = gap * 3 + 1;
        }

        int tmp;
        for (;gap > 0; gap = gap / 3) {//步长-收敛到1
            for (int i = gap; i < size; i++) {//插入排序--有一个迭代过程--迭代范围逐渐增大
                tmp = array[i];
                int j;
                for (j = i - gap; j >= 0 && array[j] > tmp; j = j - gap) {//一次迭代过程中只需和组内前一个交换
                    array[j + gap] = array[j];
                }
                array[j + gap] = tmp;
            }

        }

    }

    public static void sort1(int[] array){
        int size = array.length;
        int gap = 1;

        while(gap < size / 3){
            gap = gap * 3 + 1;
        }

        for (;gap > 0; gap = gap / 3) {
            for (int i = gap; i < size; i++) {
                for (int j = i - gap; j >= 0 && array[j] > array[j + gap]; j = j - gap) {
                    swap(array, j, j + gap);
                }
            }

        }

    }

    public static void sort2(int[] array){
        int size = array.length;
        int gap = 1;

        while(gap < size / 3){
            gap = gap * 3 + 1;
        }

        int tmp;
        for (;gap > 0; gap = gap / 3) {
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < size; j = j + gap) {
                    if(array[j] < array[j - gap]){
                        tmp = array[j];
                        int k = j - gap;
                        while (k >= 0 && array[k] > tmp)
                        {
                            array[k + gap] = array[k];
                            k -= gap;
                        }
                        array[k + gap] = tmp;
                    }

                }
            }

        }

    }

    public static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{8,4,9,13,15,20,7,3,76,0,5};
        sort2(array);

        System.out.println(Arrays.toString(array));
    }

}
