package practise.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: HeapSort
 * @Package practise.sort
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/8/2
 */
public class HeapSort {

    public static <T extends Comparable<T>> void heapSort(T[] array){
        createMaxHeap(array);//构建大顶堆

        int lasstIndex = array.length - 1;
        while(lasstIndex > 0){//遍历到length = 1时，做完调整，已经排序好
            swap(array, 0, lasstIndex--);//将当前堆顶最大元素放到已排序队列的前面--大顶堆的最后一个位置
            adjustHeap(array, 0, lasstIndex);//调整堆为大顶堆
        }
    }

    public static <T extends Comparable<T>> void createMaxHeap(T[] array){
        int length = array.length;
        int lastIndex = length - 1;
        //从最后一个父节点开始，以每个父节点为子树，调整子树为大顶堆，直到根节点
        for (int parentIndex = lastIndex / 2 - 1; parentIndex >= 0; parentIndex--) {
            adjustHeap(array, parentIndex, lastIndex);
        }
    }

    public static <T extends Comparable<T>> void adjustHeap(T[] array, int p, int lastIndex) {
        int biggerIndex = p;
        int leftChildIndex = p * 2 + 1;
        int rightChildIndex = leftChildIndex + 1;

        if(leftChildIndex <= lastIndex && array[leftChildIndex].compareTo(array[biggerIndex]) > 0){
            biggerIndex = leftChildIndex;
        }

        if(rightChildIndex <= lastIndex && array[rightChildIndex].compareTo(array[biggerIndex]) > 0){
            biggerIndex = rightChildIndex;
        }

        if(biggerIndex != p){
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
