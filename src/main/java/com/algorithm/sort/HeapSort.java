package com.algorithm.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: HeapSort
 * @Package com.algorithm.sort
 * @Description: 堆排序（Heapsort）：是指利用堆这种数据结构所设计的一种排序算法。
 * 堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
 *
 * 算法描述：
 * <br/>将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
 * <br/>将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
 * <br/>由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，
 * 得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
 *
 * 算法分析：佳情况：T(n) = O(nlogn) 最差情况：T(n) = O(nlogn) 平均情况：T(n) = O(nlogn)
 *
 * @date 2018/3/8
 */
public class HeapSort {

    public static <T extends Comparable<T>> void heapSort(T[] array) {
        //构建大顶堆
        buildMaxHeap(array);
        printTree(array, array.length);

        //此时大顶堆是无序的，将堆顶与自己的最后一个对象交换位置，形成2个区域：前部分-大顶堆-无序；后部分-有序
        //依次对堆顶与大顶堆区域的最后一个对象交换位置，当大顶堆只有一个元素时（后部分到达索引0）有序
        int lastIndex = array.length - 1;
        while(lastIndex > 0){
            swap(array, 0, lastIndex--);
            adjustHeap(array, 0, lastIndex);
        }

    }

    public static <T extends Comparable<T>> void buildMaxHeap(T[] array) {
        int length = array.length;
        int lastIndex = length - 1;
        //从最后一个父节点开始，以每个父节点为子树，调整子树为大顶堆，直到根节点
        for(int p = length  / 2 - 1; p >= 0; p--){
            adjustHeap(array, p, lastIndex);
        }
    }

    public static <T extends Comparable<T>> void adjustHeap(T[] array, int p, int lastIndex) {
        int biggerIndex = p;
        int leftChildIndex = p * 2 + 1;
        int rightChildIndex = p * 2 + 2;

        if(leftChildIndex <= lastIndex && array[leftChildIndex].compareTo(array[biggerIndex]) > 0){
            biggerIndex = leftChildIndex;
        }

        if(rightChildIndex <= lastIndex && array[rightChildIndex].compareTo(array[biggerIndex]) > 0){
            biggerIndex = rightChildIndex;
        }

        if(biggerIndex != p){
            //调整子树--因为整个调整过程是从最后一个父节点开始的，从数组上来看就是倒序往前，
            //从树上看是从最下层往最上层调整，所以调整父节点和子节点位置时，可能破坏子树，故需要调整
            swap(array, p, biggerIndex);
            adjustHeap(array, biggerIndex, lastIndex);
        }
    }

    public static <T extends Comparable<T>> void swap(T[] array, int source, int target) {
        T t = array[source];
        array[source] = array[target];
        array[target] = t;
    }

    public static <T extends Comparable<T>> void printTree(T[] array, int len) {
        int layers = (int) Math.floor(Math.log((double) len) / Math.log((double) 2)) + 1;  //树的层数
        int maxWidth = (int) Math.pow(2, layers) - 1;  //树的最大宽度
        int endSpacing = maxWidth;
        int spacing;
        int numberOfThisLayer;
        for (int i = 1; i <= layers; i++) {  //从第一层开始，逐层打印
            endSpacing = endSpacing / 2;  //每层打印之前需要打印的空格数
            spacing = 2 * endSpacing + 1;  //元素之间应该打印的空格数
            numberOfThisLayer = (int) Math.pow(2, i - 1);  //该层要打印的元素总数

            int j;
            for (j = 0; j < endSpacing; j++) {
                System.out.print("  ");
            }

            int beginIndex = (int) Math.pow(2, i - 1) - 1;  //该层第一个元素对应的数组下标
            for (j = 1; j <= numberOfThisLayer; j++) {
                System.out.print(array[beginIndex++] + "");
                for (int k = 0; k < spacing; k++) {  //打印元素之间的空格
                    System.out.print("  ");
                }
                if (beginIndex == len) {  //已打印到最后一个元素
                    break;
                }
            }

            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{5,3,7,11,9,-3,16,1};
        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

}
