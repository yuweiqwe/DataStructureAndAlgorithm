package com.china.hadoop.bat.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: CantorArray
 * @Package com.china.hadoop.bat.chapter4
 * @Description: 康拓数组
 * @date 2017/10/17
 */
public class CantorArray {

    /**
     * @Title: getCantorArray
     * @Description: 获取康拓数组
     * 已知数组A[0...N-1]乱序着前N个正整数，现 统计后缀数组A[i+1...N-1]中小于元素A[i]的 数目，
     * 并存放在数组C[i]中。如给定数组 A={4,6,2,5,3,1}，得到数组C={3,4,1,2,1,0}。
     * 问:给定数组C={3,4,1,2,1,0}，如何恢复数 组A?
     * 我们称A为原数组，C为Cantor数组
     * @param a
     * @return int[]
     * @throws
     * */
    public static int[] getCantorArray(int[] a){
        int size = a.length;

        int[] cantor = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if(a[i] > a[j]){
                    cantor[i]++;
                }
            }
        }

        return cantor;
    }

    /**
     * @Title: getOriginalArrayByCantorArray
     * @Description: 通过康拓数组还原 原数组
     * Cantor数组:{3,4,1,2,1,0} 原数组 :{4,6,2,5,3,1}
     * 给定顺序数组B={1,2,3...N-1,N}，从0开始数 考察Cantor数组的首位C[0]:
     * 小于A[0]的个数为C[0]，则A[0]为B[C[0]]
     * 在序列数组B中删除B[C[0]]，仍然满足以上性质。
     * @param cantor
     * @param sortList
     * @return ${return_type}    返回类型
     * @throws
     * */
    public static int[] getOriginalArrayByCantorArray(int[] cantor, List<Integer> sortList){
        int[] origin = new int[cantor.length];

        for (int i = 0; i < cantor.length; i++) {
            int value = sortList.get(cantor[i]);
            origin[i] = value;
            sortList.remove(cantor[i]);
        }

        return origin;
    }

    /**
     * @Title: getOriginalArrayByCantorArray1
     * @Description: 通过康拓数组还原 原数组
     * 以上代码空间复杂度为O(N)，时间复杂度为O(N2)， 若允许更改数组C，可否降低空间复杂度?
     * Cantor数组:{3,4,1,2,1,0} 原数组 :{4,6,2,5,3,1}
     * 考察Cantor数组中第一个出现0的位置:它表示位于该位置右侧的所有元素都大于该元素，则该元素必然是最小的。
     * 每次找到第一个0后，将0左侧的Cantor值都减一，重复以 上操作。
     * 空间复杂度为O(1)。
     * @param cantor
     * @param sortList
     * @return ${return_type}    返回类型
     * @throws
     * */
    public static int[] getOriginalArrayByCantorArray1(int[] cantor, List<Integer> sortList){
        int[] origin = new int[cantor.length];
        int[] a = Arrays.copyOf(cantor, cantor.length);
        System.out.println("copy cantor : " + Arrays.toString(a));

        int i,j;
        for (i = 0; i < cantor.length; i++) {//i标识原数组要找的数起始位置,0表示第一个数，1表示第二个数,以此类推
            for (j = 0; j < cantor.length; j++) {//j用来找到第一个cantor值为0的数
                if(origin[j] != 0){
                    continue;
                }
                if(a[j] == 0){
                    break;
                }
                a[j]--;
            }
//            origin[j] = i + 1;
            origin[j] = sortList.get(i);
        }

        return origin;
    }

    public static void main(String[] args) {
        int[] a = new int[]{4,6,2,5,3,1};
        int[] cantor = getCantorArray(a);

        System.out.println("cantor : " + Arrays.toString(cantor));

        List<Integer> sortList = getSortList(a.length);
        int[] origin = getOriginalArrayByCantorArray(cantor, sortList);


        System.out.println("origin : " + Arrays.toString(a));
        System.out.println("result : " + Arrays.toString(origin));


        System.out.println("cantor : " + Arrays.toString(cantor));
        int[] origin1 = getOriginalArrayByCantorArray1(cantor, getSortList(cantor.length));
        System.out.println("result : " + Arrays.toString(origin1));

    }

    public static List<Integer> getSortList1(int size){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i + 1);
        }

        return list;
    }

    public static List<Integer> getSortList(int size){
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i + 1);
        }

        return list;
    }

}
