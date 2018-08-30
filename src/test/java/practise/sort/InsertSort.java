package practise.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: InsertSort
 * @Package practise.sort
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/7/25
 */
public class InsertSort<T extends Comparable<T>> {


    public static <T extends Comparable<T>> T[] sort(T[] unsorted){
        int length = unsorted.length;

        for (int i = 1; i < length; i++) {
            sort1(i, unsorted);
        }

        return unsorted;
    }

    private static <T extends Comparable<T>> void sort(int i, T[] unsorted) {
        for (int j = i; j > 0 ; j--) {
            T cur = unsorted[j];
            T pre = unsorted[j - 1];
            if(cur.compareTo(pre) < 0){
                unsorted[j - 1] = cur;
                unsorted[j] = pre;
            }else{
                break;
            }
        }
    }

    private static <T extends Comparable<T>> void sort1(int i, T[] unsorted) {
        int j;
        T cur = unsorted[i];
        for (j = i; j > 0 ; j--) {
            T pre = unsorted[j - 1];
            if(cur.compareTo(pre) < 0){
                unsorted[j] = pre;
            }else{
                break;
            }
        }
        unsorted[j] = cur;
    }


    public static void main(String[] args) {
        Integer[] array = new Integer[]{3,7,1,8,5,6};

        System.out.println(Arrays.toString(InsertSort.sort(array)));
    }

}
