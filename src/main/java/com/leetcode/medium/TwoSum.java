package com.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: TwoSum
 * @Package com.leetcode
 * @Description: 找数组中2个数和等于target
 * @date 2017/11/14
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }

        throw new RuntimeException("no solution");
    }

    public static void main(String[] args) {
        int[] nums = {3,3};
        int target = 6;

        System.out.println(Arrays.toString(new TwoSum().twoSum(nums, target)));
    }

}
