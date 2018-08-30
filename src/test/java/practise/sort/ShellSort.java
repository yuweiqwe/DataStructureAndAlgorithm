package practise.sort;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ShellSort
 * @Package practise.sort
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/8/1
 */
public class ShellSort {

    private static final int FACTOR = 3;


    public static <T extends Comparable<T>> void shellSort(T[] unsorted) {
        int length = unsorted.length;
        int gap = getDynamicGap(length);

        while(gap >= 1){
            for (int i = gap; i < length; i++) {
                sort(i, unsorted, gap);
            }

            gap = gap / FACTOR;
        }

    }

    private static <T extends Comparable<T>> void sort(int i, T[] unsorted, int gap) {
        for (int j = i - gap; j >= 0; j = j - gap) {
            T cur = unsorted[j + gap];
            T pre = unsorted[j];
            if(cur.compareTo(pre) < 0){
                unsorted[j] = cur;
                unsorted[j + gap] = pre;
            }else{
                break;
            }

        }
    }


    public static int getDynamicGap(int length){
        int gap = 1;

        while(gap < length / FACTOR){
            gap = gap * FACTOR + 1;
        }

        return gap;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{5,3,7,11,9,3,16,1};
        System.out.println(Arrays.toString(array));
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }

}
