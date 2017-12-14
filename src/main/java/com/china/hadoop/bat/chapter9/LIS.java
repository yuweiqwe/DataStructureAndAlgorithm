package com.china.hadoop.bat.chapter9;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: LIS
 * @Package com.china.hadoop.bat
 * @Description: 最长递增子序列
 * @date 2017/11/3
 */
public class LIS {

    public static int lis1(int[] array){
        int size = array.length;
        int[] len = new int[size];//array每一个位置最长子串长度

        //初始化每一个位置最长子串长度 等于1
        for (int i = 0; i < len.length; i++) {
            len[i] = 1;
        }

        int max = 0;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if(array[j] <= array[i]){//查找i>j，最大的j-->可以使用双游标查找：二分查找
                    len[i] = Math.max(len[i], len[j] + 1);
                }
            }

            max = Math.max(max, len[i]);
        }

        return max;
    }

    public static int lis2(int[] array, Integer index){
        int size = array.length;
        int[] len = new int[size];//array每一个位置最长子串长度
        int[] pre = new int[size];//前驱

        //初始化每一个位置最长子串长度 等于1
        for (int i = 0; i < len.length; i++) {
            len[i] = 1;
        }

        int max = 0;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if(array[j] <= array[i] && len[i] < len[j] + 1){
                    len[i] = len[j] + 1;
                    pre[i] = j;
                }
            }

            if(len[i] > max){
                max = len[i];
                index = i;
            }
            max = Math.max(max, len[i]);
        }

        System.out.println(Arrays.toString(pre));
        return max;
    }

    public static void main(String[] args) {
        int[] array = {1,4,6,2,8,9,7};

        System.out.println(lis1(array));
        System.out.println(lis2(array, 0));
    }

}
