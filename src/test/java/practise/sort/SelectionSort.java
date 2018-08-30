package practise.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: SelectionSort
 * @Package practise.sort
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/8/2
 */
public class SelectionSort {

    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        int length = array.length;

        int min;
        for (int i = 0; i < length; i++) {
            min = i;
            for (int j = i + 1; j < length; j++) {
                if(array[min].compareTo(array[j]) > 0){
                    min = j;
                }

            }

            swap(array, min, i);
        }

    }


    public static <T extends Comparable<T>> void swap(T[] array, int source, int target) {
        T t = array[source];
        array[source] = array[target];
        array[target] = t;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{3,5,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }

}
