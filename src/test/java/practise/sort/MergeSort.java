package practise.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: MergeSort
 * @Package practise.sort
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/8/2
 */
public class MergeSort {

    public static <T extends Comparable<T>> void mergeSort(T[] unsorted, int low, int high){
        if(low >= high){
            return;
        }

        int middle = (low + high) / 2;
        mergeSort(unsorted, low, middle);
        mergeSort(unsorted, middle + 1, high);
        merge1(unsorted, low, middle, high);
    }

    public static <T extends Comparable<T>> void merge(T[] unsorted, int low, int mid, int high){
        Comparable<T>[] extendSpace = new Comparable[high - low + 1];

        int size = 0;
        int left = low;
        int right = mid + 1;

        while(left <= mid && right <= high){
            if(unsorted[left].compareTo(unsorted[right]) < 0){
                extendSpace[size++] = unsorted[left++];
            }else{
                extendSpace[size++] = unsorted[right++];
            }
        }

        while(left <= mid){
            extendSpace[size++] = unsorted[left++];
        }

        while(right <= high){
            extendSpace[size++] = unsorted[right++];
        }

        for (int i = 0; i < extendSpace.length; i++) {
            unsorted[low + i] = (T) extendSpace[i];
        }

    }

    public static <T extends Comparable<T>> void merge1(T[] unsorted, int low, int mid, int high){
        Comparable<T>[] extendSpace = new Comparable[high - low + 1];

        int size = 0;
        int left = low;
        int right = mid + 1;

        while(left <= mid || right <= high){
            if(left <= mid && right > high){
                extendSpace[size++] = unsorted[left++];
            }else if(left > mid && right <= high){
                extendSpace[size++] = unsorted[right++];
            }else if(unsorted[left].compareTo(unsorted[right]) < 0){
                extendSpace[size++] = unsorted[left++];
            }else{
                extendSpace[size++] = unsorted[right++];
            }
        }

        for (int i = 0; i < extendSpace.length; i++) {
            unsorted[low + i] = (T) extendSpace[i];
        }

    }


    public static void main(String[] args) {
        Integer[] array = new Integer[]{5,-3,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

}
