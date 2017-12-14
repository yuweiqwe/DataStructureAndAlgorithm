package com.china.hadoop.bat.chapter4;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Mode
 * @Package com.china.hadoop.bat.chapter4
 * @Description: 众数
 * 定义:给定N个数，称出现次数最多的数为 众数;若某众数出现的次数大于N/2，称该 众数为绝对众数。
 * 如:A={1,2,1,3,2}中，1和2都是众数，但都不是 绝对众数。
 * 如:A={1,2,1,3,1}中，1是绝对众数。
 * 已知给定的N个整数存在绝对众数，以最低的时空复杂度计算该绝对众数。
 * @date 2017/10/15
 */
public class Mode {

    /**
     * @Title: getMode
     * @Description: 空间复杂度：O(1)   时间复杂度：O(N)
     * 删除数组A中两个不同的数，绝对众数不变。
     * 若两个数中有1个是绝对众数，则剩余的N-2个数中，绝对众数仍然大于(N-2)/2;
     * 若两个数中没有绝对众数，显然不影响绝对众数。
     * 算法描述:
     * 记m为候选绝对众数，出现次数为c，初始化为0。
     * 遍历数组A:
     * 若c==0，则m=A[i]
     * 若c≠0且m≠A[i]，则同时删掉m和A[i]
     * 若c≠0且m==A[i]，则c++
     * @param array
     * @param size
     * @return int
     * @throws
     * */
    public static int getMode(int[] array, int size){
        int count = 0;
        int m = array[0];

        for (int i = 0; i < size; i++) {
            if(count == 0){
                m = array[i];
                count = 1;
            }else if(m != array[i]){
                count--;
            }else{
                count++;
            }

        }

        return m;
    }

    public static void main(String[] args) {
        int[] array = new int[]{8,1,5,8,0,1,8,8,9,9,8};

        System.out.println(getMode(array, array.length));
    }

}
