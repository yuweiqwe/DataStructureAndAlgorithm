package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: MergeSort
 * @Package com.algorithm.sort
 * @Description: 归并排序：性能不受输入数据的影响，表现比选择排序好的多，因为始终都是O(n log n的时间复杂度。代价是需要额外的内存空间。
 * 归并排序是建立在归并操作上的一种有效的排序算法。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 归并排序是一种稳定的排序方法。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合并成一个有序表，称为2-路归并。
 * <p>
 * 算法描述：
 * <br/>把长度为n的输入序列分成两个长度为n/2的子序列；
 * <br/>对这两个子序列分别采用归并排序；
 * <br/>将两个排序好的子序列合并成一个最终的排序序列。
 * <p>
 * 算法分析：最佳情况：T(n) = O(n)  最差情况：T(n) = O(nlogn)  平均情况：T(n) = O(nlogn)
 * @date 2018/3/8
 */
public class MergeSort {

    public static <T extends Comparable<T>> void mergeSort(T[] array, int low, int high) {
        while (low >= high) {
            return;
        }

        int middle = (low + high) / 2;
        mergeSort(array, low, middle);
        mergeSort(array, middle + 1, high);
        merge(array, low, middle, high);
    }

    public static <T extends Comparable<T>> void merge(T[] array, int low, int mid, int high) {
        Comparable[] extendSpace = new Comparable[high - low + 1];
        int size = 0;
        int left = low;
        int right = mid + 1;

        while (left <= mid && right <= high) {
            if (array[left].compareTo(array[right]) < 0) {
                extendSpace[size++] = array[left++];
            } else {
                extendSpace[size++] = array[right++];
            }
        }

        while (left <= mid) {
            extendSpace[size++] = array[left++];
        }
        while (right <= high) {
            extendSpace[size++] = array[right++];
        }

        for (int k = 0; k < size; k++) {
            array[low + k] = (T) extendSpace[k];
        }

    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{5,-3,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

}
