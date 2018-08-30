package practise.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BubbleSort
 * @Package practise.sort
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/8/1
 */
public class BubbleSort {

    public static <T extends Comparable<T>> void bubbleSort(T[] unsorted){
        int length = unsorted.length;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if(unsorted[j].compareTo(unsorted[j + 1]) > 0){
                    swap(j, j + 1, unsorted);
                }
            }
        }

    }

    private static <T extends Comparable<T>> void swap(int j, int i, T[] unsorted) {
        T tmp = unsorted[j];
        unsorted[j] = unsorted[i];
        unsorted[i] = tmp;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{3,5,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

}
