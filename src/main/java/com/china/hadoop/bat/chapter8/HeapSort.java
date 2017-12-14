package com.china.hadoop.bat.chapter8;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: HeapSort
 * @Package com.china.hadoop.bat.chapter8
 * @Description: 堆排序
 * @date 2017/11/2
 */
public class HeapSort<T extends Comparable<T>> {

    private T[] t;
    private int size;

    public HeapSort(T[] t) {
        this.t = t;
        this.size = t.length;
    }

    public void buildHeap() {
        int lastIndex = size - 1;//数组最后一个index
        int lastParent = (lastIndex - 1) / 2;//树对应最后一个父节点在数组中的index
        while (lastParent >= 0) {
            adjustHeap(lastParent--, lastIndex);
        }
    }

    public void adjustHeap(int parentIndex, int lastIndex) {
        int biggerIndex = parentIndex;
        int leftChildIndex = parentIndex * 2 + 1;
        int rightChildIndex = parentIndex * 2 + 2;

        //左右孩子都存在
        if (rightChildIndex <= lastIndex) {
            if (t[biggerIndex].compareTo(t[leftChildIndex]) < 0
                    || t[biggerIndex].compareTo(t[rightChildIndex]) < 0) {
                biggerIndex = t[leftChildIndex].compareTo(t[rightChildIndex]) > 0 ? leftChildIndex : rightChildIndex;
            }
            //只存在左孩子
        } else if (leftChildIndex <= lastIndex) {
            if (t[biggerIndex].compareTo(t[leftChildIndex]) < 0) {
                biggerIndex = leftChildIndex;
            }
        }

        if (biggerIndex > parentIndex) {
            swap(parentIndex, biggerIndex);//将父节点和较大的子节点交换

            //调整子树--因为整个调整过程是从最后一个父节点开始的，从数组上来看就是倒序往前，
            //从树上看是从最下层往最上层调整，所以调整父节点和子节点位置时，可能破坏子树，故需要调整
            adjustHeap(biggerIndex, lastIndex);
        }

    }

    public void sortHeap() {
        //建立 最大堆
        this.buildHeap();
        this.printTree(size);

        int lastIndex = size - 1;
        //遍历堆顶，去掉堆顶，重新调整最大堆
        while (lastIndex > 0) {
            swap(0, lastIndex);
            if (--lastIndex == 0) {
                break;
            }
            this.adjustHeap(0, lastIndex);
            this.printTree(lastIndex + 1);
        }

    }

    public void printTree(int len) {
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
                System.out.print(t[beginIndex++] + "");
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

    public void print() {
        System.out.println(Arrays.toString(t));
    }

    public void swap(int i, int j) {
        T a = t[i];
        t[i] = t[j];
        t[j] = a;
    }

    public static void main(String[] args) {
        Integer[] a = {7, 1, 9, 2, 5, 10, 6, 4, 3, 8};

        HeapSort<Integer> heapSort = new HeapSort<>(a);

        heapSort.printTree(a.length);

        heapSort.print();

        heapSort.sortHeap();

        heapSort.print();

    }

}
