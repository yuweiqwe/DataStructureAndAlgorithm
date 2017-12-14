package com.china.hadoop.bat.chapter2;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Permutation
 * @Package com.china.hadoop.bat.chapter2
 * @Description: 全排列
 * 康托展开 X=an*(n-1)!+an-1*(n-2)!+...+ai*(i-1)!+...+a2*1!+a1*0!
 * @date 2017/10/9
 */
public class Permutation {

    /**
    * @Title: permutation
    * @Description: 去重全排列
    * @param arr
    * @param start
    * @param size
    * @return void
    * @throws
    */
    public static void permutation(int[] arr, int start, int size){
        if(start == size - 1){
            print(arr);
            return;
        }

        for (int i = start; i < size; i++) {
            if(isDuplicate(arr, start, i)){
                continue;
            }
            swap(arr, start, i);
            permutation(arr, start + 1, size);
            swap(arr, i, start);
        }

    }

    /**
     * @Title: permutation
     * @Description: 不去重全排列
     * @param arr
     * @param start
     * @param size
     * @return void
     * @throws
     */
    public static void permutation1(int[] arr, int start, int size){
        if(start == size - 1){
            print(arr);
            return;
        }

        for (int i = start; i < size; i++) {
            swap(arr, start, i);
            permutation(arr, start + 1, size);
            swap(arr, i, start);
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

    public static boolean isDuplicate(int[] arr, int start, int last){
        while(start < last){
            if(arr[start] == arr[last]){
                return true;
            }
            start++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,4};
        permutation(arr, 0, 5);
    }

}
