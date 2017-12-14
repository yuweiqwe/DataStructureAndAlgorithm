package com.china.hadoop.bat.chapter4;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: HollandFlag
 * @Package com.china.hadoop.bat.chapter4
 * @Description: 荷兰国旗问题
 * @date 2017/10/17
 */
public class HollandFlag {

    public static void sort(int[] a){
        int begin = 0;
        int end = a.length - 1;
        int cur = end;

        while(cur >= begin){
            if(a[cur] == 2){
                swap(a, cur, end);
                cur--;
                end--;
            }else if(a[cur] == 1){
                cur--;
            }else{
                swap(a, cur, begin);
                begin++;
            }
        }

    }

    public static void sort1(int[] a){
        int begin = 0;
        int end = a.length - 1;
        int cur = 0;

        while(cur <= end){
            if(a[cur] == 2){
                swap(a, cur, end);
                end--;
            }else if(a[cur] == 1){
                cur++;
            }else{
                if(a[begin] == a[cur]){
                    begin++;
                    cur++;
                }else{
                    swap(a, cur, begin);
                    begin++;
                    cur++;//a[cur] == 0且 a[begin] != a[cur]，那么必然a[begin] == 1
                }
            }
        }

    }

    public static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,0,1,2,0,1,2,0};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
