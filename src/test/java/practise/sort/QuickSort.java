package practise.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: QuickSort
 * @Package practise.sort
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/8/1
 */
public class QuickSort {

    public static <T extends Comparable<T>> void quickSort(T[] array, int left, int right) {
        if(left < right){
            int partition = partition(array, left, right);

            quickSort(array, left, partition - 1);

            quickSort(array, partition + 1, right);
        }
    }

    public static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int pivotIndex = left + getPivotRelativePosition();
        T pivot = array[pivotIndex];

        while(left < right){
            while(array[left].compareTo(pivot) < 0){
                left++;
            }

            while(array[right].compareTo(pivot) > 0){
                right--;
            }

            swap(array, left, right);
        }

        return left;
    }

    private static int getPivotRelativePosition() {
        return 0;
    }

    public static <T extends Comparable<T>> void swap(T[] array, int source, int target) {
        T t = array[source];
        array[source] = array[target];
        array[target] = t;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{-3, 5, 7, 11, 9, 3, 16, 1};
        System.out.println(Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

//        swap(array, 0, array.length - 1);
//        System.out.println(Arrays.toString(array));
    }

}
