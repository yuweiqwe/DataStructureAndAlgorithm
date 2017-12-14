package com.china.hadoop.bat.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: FirstMissingNumber
 * @Package com.china.hadoop.bat.chapter4
 * @Description: 寻找：第一个缺失的整数
 * 给定一个数组A[0...N-1]，找到从1开始，第 一个不在数组中的正整数。如3,5,1,2,-3,7,14,8输出4。
 * 循环不变式
 * 思路:将找到的元素放到正确的位置上，如 果最终发现某个元素一直没有找到，则该元 素即为所求。
 * 循环不变式:如果某命题初始为真，且每次 更改后仍然保持该命题为真，则若干次更改 后该命题仍然为真。
 * 为表述方便，下面的算法描述从1开始数。
 * 利用循环不变式设计算法
 * 假定前i-1个数已经找到，并且依次存放在A[1,2,...,i-1]中，继续考察A[i]:
 * 若A[i]<i且A[i]≥1，则A[i]在A[1,2,...,i-1]中已经出现过， 可以直接丢弃。
 * 若A[i]为负，则更应该丢弃它。
 * 若A[i]>i且A[i]≤N，则A[i]应该置于后面的位置，即将A[A[i]]和A[i]交换。
 * 若A[i]>N，由于缺失数据≥N，则A[i]丢弃。
 * 若A[A[i]]=A[i]，则显然不必交换，直接丢弃A[i]即可。
 * 若A[i]=i，则A[i]位于正确的位置上，则i加1，循环不变 式扩大，继续比较后面的元素。
 * @date 2017/10/16
 */
public class FirstMissingNumber {

    public static int findFirstMissingNumber(int[] array, int size){
        int i = 0;

        while (i <= size) {
            if(array[i] == i){
                i++;
            }else if(array[i] < i || array[i] > size || array[i] == array[array[i]]){
                array[i] = array[size - 1];
                size--;
            }else{
                swap(array, array[i], i);
            }
        }

        return -999;
    }

    public static void swap(int[] array, int i, int j){
        int t = array[i];

        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,6,7,3,2,9,4,-1,0};
        System.out.println(findFirstMissingNumber(array, array.length));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(12);
        list.add(100);

        list.forEach(o -> {
            System.out.println(o);
            System.out.println();
        });

        new Thread(() ->{
            System.out.println("hello");
        }).start();

        System.out.println("---------");
        Predicate<String> condition = (str) -> str.startsWith("1");
        list.forEach(o -> {
            System.out.println(condition.test(o.toString()));
            System.out.println(o);
        });

        System.out.println("---------");
        Predicate<Integer> condition1 = (str) -> str.toString().startsWith("1");
        list.stream().filter(condition1).forEach((n) -> System.out.println(n));

        System.out.println("---------");
        list.stream().map((o) -> {
            return o + o * 10;
        }).forEach(n -> System.out.println(n));

        System.out.println("---------");
        int sum1 = list.stream().map((o) -> {
            return o + o * 10;
        }).reduce((sum, n) -> sum + n).get();
        System.out.println(sum1);

    }

}
