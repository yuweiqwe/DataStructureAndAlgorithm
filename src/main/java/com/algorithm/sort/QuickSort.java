package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: QuickSort
 * @Package com.algorithm.sort
 * @Description: 快速排序：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
 * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * <p>
 * 算法描述:
 * <br/>从数列中挑出一个元素，称为 “基准”（pivot）
 * <br/>重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * <br/>递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * <p>
 * 算法分析：最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)
 * @date 2018/3/8
 */
public class QuickSort {

    public static <T extends Comparable<T>> void quickSort(T[] array, int left, int right) {
        if (left < right) {
            int partitionIndex = partition1(array, left, right);

            quickSort(array, left, partitionIndex - 1);

            quickSort(array, partitionIndex + 1, right);
        }
    }

    public static <T extends Comparable<T>> int partition1(T[] array, int left, int right) {
        T pivot = array[left];

        while(left < right){

            while(array[right].compareTo(pivot) >= 0 && left < right){//当右侧指针对应对象都比pivot基准大，指针左移
                right--;
            }

            while(array[left].compareTo(pivot) <= 0 && left < right){//当左侧指针对应对象都比pivot基准小，指针右移
                left++;
            }

            //从左往右找到第一个对象比pivot基准小，把这个对象放到左侧指针指向位置（小往左放）
            //从右往左找到第一个对象比pivot基准大，把这个对象放到右侧指针指向位置（大往右放）
            swap(array, left, right);
        }

        return left;
    }

    public static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int pivot = left;//基准-第一个
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {//遍历每个对象，需要遍历的对象个数 = right - left - 1
            if (array[i].compareTo(array[pivot]) < 0) {//i位置对象比基准小，i对象放到比基准小的数列中index位置
                swap(array, i, index);
                index++;
            }
        }

        swap(array, pivot, index - 1);//pivot基准对象 与 比基准小的数列中最后一个位置的对象（index - 1位置）交换
        return index - 1;
    }

    public static <T extends Comparable<T>> void swap(T[] array, int source, int target) {
        T t = array[source];
        array[source] = array[target];
        array[target] = t;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{-3, 5, 7, 11, 9, 3, 16, 1};
        System.out.println(Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

//        swap(array, 0, array.length - 1);
//        System.out.println(Arrays.toString(array));
    }

}
