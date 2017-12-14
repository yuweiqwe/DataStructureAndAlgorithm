package com.china.hadoop.bat.chapter4;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: QuickSort
 * @Package com.china.hadoop.bat.chapter4
 * @Description: 快速排序
 * @date 2017/10/17
 */
public class QuickSort {

    public static int[] sort(int[] a, int i, int j){
        if(i >= j){
            return a;
        }

        int b = partition(a, i, j);

        sort(a, i, b - 1);//以b为位置的值split为分割线 递归排序前半部分
        sort(a, b + 1, j);//以b为位置的值split为分割线 递归排序后半部分

        return a;
    }

    public static int partition(int[] a, int i, int j){
        int b = i;
        int e = j;
        int split = a[i];

        while(b < e){

            while(a[e] >= split && b < e){
                e--;
            }
            a[b] = a[e];

            while(a[b] <= split && b < e){
                b++;
            }
            a[e] = a[b];

        }
        a[b] = split;//此时b==e

        System.out.println(Arrays.toString(a));

        return b;
    }

    public static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int[] sort1(int[] a, int i, int j) {
        int b = i;
        int e = j;
        int split = a[i];

        while(b < e){
            while(a[e] >= split && b < e){
                e--;
            }
            swap(a, b, e);
            //b++;

            while(a[b] <= split && b < e){
                b++;
            }
            swap(a, b, e);
            //e--;

        }

        System.out.println(Arrays.toString(a));

        sort(a, i, b - 1);//以b为位置的值split为分割线 递归排序前半部分
        sort(a, b + 1, j);//以b为位置的值split为分割线 递归排序后半部分

        return a;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,1,6,1,0,-8,9,5};

        sort1(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }

}
