package com.china.hadoop.bat.chapter8;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: MergeSort
 * @Package com.china.hadoop.bat.chapter8
 * @Description: 归并排序
 * 基本思想:将数组A[0...n-1]中的元素分成两 个子数组:A1[0...n/2]和A2[n/2+1...n-1]。
 * 分别对这两个子数组单独排序，然后将已排序 的两个子数组归并成一个含有n个元素的有序数组。
 * @date 2017/11/1
 */
public class MergeSort {

    private static int[] tmp = new int[100];

    public static void sort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;

        sort(array, low, mid);
        sort(array, mid + 1, high);
        merge(array, low, mid, high);
    }

    public static void merge(int[] array, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int size = 0;

        for (;i <= mid && j <= high; size++) {
            if(array[i] < array[j]){
                tmp[size] = array[i++];
            }else{
                tmp[size] = array[j++];
            }
        }

        while(i <= mid){
            tmp[size++] = array[i++];
        }
        while(j <= high){
            tmp[size++] = array[j++];
        }

        for (int k = 0; k < size; k++) {
            array[low + k] = tmp[k];
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,7,9,1,13,16,8,90,8,4,56};
        sort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }

}
