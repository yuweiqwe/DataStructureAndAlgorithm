package com.china.hadoop.bat.chapter4;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BinarySearch
 * @Package com.china.hadoop.bat.chapter4
 * @Description: 折半查找-二分查找
 * @date 2017/10/15
 */
public class BinarySearch {

    public static int search(int[] array, int size, int value){
        int from = 0;
        int to = size - 1;

        int index = 0;

        while(from <= to){
            index = (from + to)/2;

            if(array[index] == value){
                return index;
            }

            if(array[index] > value){
                to = index - 1;
            }else{
                from = index + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arrray = new int[]{1,2,2,3,4,5,5,6,7,8,9};

        System.out.println("index : " + search(arrray, arrray.length, 5));
    }

}
