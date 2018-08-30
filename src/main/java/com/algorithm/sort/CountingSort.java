package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: CountingSort
 * @Package com.algorithm.sort
 * @Description: 计数排序(Counting sort)是一种稳定的排序算法。
 * 计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。
 * 然后根据数组C来将A中的元素排到正确的位置。它只能对整数进行排序。
 *
 * 算法描述：
 * <br/>找出待排序的数组中最大和最小的元素；
 * <br/>统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 * <br/>对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 * <br/>反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 *
 * 算法分析：最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n+k)  平均情况：T(n) = O(n+k)
 * 当输入的元素是n 个0到k之间的整数时，它的运行时间是 O(n + k)。计数排序不是比较排序，排序的速度快于任何比较排序算法。
 * 由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），
 * 这使得计数排序对于数据范围很大的数组，需要大量时间和内存。
 *
 * @date 2018/3/9
 */
public class CountingSort {

    public static void countingSort(int[] array, int max){
        Integer[] bucket = new Integer[max + 1];
        int sortedIndex = 0;
        int length = array.length;
        int bucketLen = max + 1;

        for (int i = 0; i < length; i++) {
            if (bucket[array[i]] == null) {
                bucket[array[i]] = 0;
            }
            bucket[array[i]]++;
        }

        for (int j = 0; j < bucketLen; j++) {
            while(bucket[j] != null && bucket[j] > 0) {
                array[sortedIndex++] = j;
                bucket[j]--;
            }
        }

    }

    public static void main(String[] args) {
        int[] array = new int[]{3,5,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        countingSort(array, 16);
        System.out.println(Arrays.toString(array));
    }

}
