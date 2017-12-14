package com.china.hadoop.bat.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: MaxSubArray
 * @Package com.china.hadoop.bat.chapter4
 * @Description: 最大子数组
 * @date 2017/10/16
 */
public class MaxSubArray {

    public static List<Integer> getMaxSubArray(int[] array, int size){
        int sum = 0;
        int result = sum;

        List<Integer> list = new ArrayList<>();

        int j = 0;
        int i = 0;
        while(i < size){
            if(sum > 0){
                sum += array[i];
            }else{
                sum = array[i];
            }

            if(result <= sum){
                result = sum;
                list.add(array[i]);
            }
            i++;
        }

        return list;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,1,6,1,0,-8,9,5};

        System.out.println(getMaxSubArray(array, array.length));
    }

}
