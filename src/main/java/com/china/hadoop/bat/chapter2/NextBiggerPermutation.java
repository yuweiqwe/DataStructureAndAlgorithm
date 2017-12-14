package com.china.hadoop.bat.chapter2;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: NextBigPermutation
 * @Package com.china.hadoop.bat.chapter2
 * @Description: 全排列：查找下一个刚好比原串大的串
 * 1、后找  找最后一个升序的位置i   s[i] < s[i+1]
 * 2、查找小大 s[i+1...size-1]中比s[i]大的最小值s[j]
 * 3、交换 s[i] s[j]
 * 4、翻转 s[i+1...size-1]
 * @date 2017/10/9
 */
public class NextBiggerPermutation {

    public static boolean getNextBiggerPermutation(int[] arr, int size){
        //后找
        int pos = size - 2;
        while(pos >= 0 && arr[pos] >= arr[pos + 1]){
            pos--;
        }
        if(pos < 0){
            return false;
        }

        //查找小大
        int min = size - 1;
        while(arr[min] <= arr[pos]){
            min--;
        }

        //交换
        swap(arr, min, pos);

        //翻转
        reverse(arr, pos + 1, size - 1);

        return true;
    }

    public static void reverse(int[] arr, int from, int to){
        int i = 0;
        while(from < to){
            swap(arr, from, to);
            from++;
            to--;
        }
    }

    public static void swap(int[] arr, int source, int target){
        int tmp = arr[source];
        arr[source] = arr[target];
        arr[target] = tmp;
    }

    public static void print(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr = {1,2,5,6,3};
        int size = arr.length;

        print(arr);//原始数组
        System.out.println("----------");
        while(getNextBiggerPermutation(arr, size)){
            print(arr);
        }
    }

}
