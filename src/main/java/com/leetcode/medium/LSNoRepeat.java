package com.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: LSNoRepeat
 * @Package com.leetcode.medium
 * @Description: 最长无重复子串
 * @date 2017/11/14
 */
public class LSNoRepeat {

    public int lengthOfLongestSubstring(String s) {
        int size = s.length();
        int max = 0;
        int start = 0;
        int end = 0;

        Set<Character> set = new HashSet<>();
        while(start < size && end < size){
            if(!set.contains(s.charAt(end))){
                set.add(s.charAt(end++));
                max = Math.max(max, end - start);
            }else{
                set.remove(s.charAt(start++));
            }
        }

        return max;
    }

    public int lengthOfLongestSubstring1(String s) {
        int size = s.length();
        int max = 0;
        int start = 0;
        int end = 0;

        Map<Character, Integer> map = new HashMap<>();
        while(end < size){
            if(map.containsKey(s.charAt(end))){
                start = Math.max(map.get(s.charAt(end)), start);
            }
            max = Math.max(max, end - start + 1);
            map.put(s.charAt(end), end + 1);
            end++;
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(new LSNoRepeat().lengthOfLongestSubstring1("abcabcbb"));
        System.out.println(System.currentTimeMillis());
        System.out.println(new LSNoRepeat().lengthOfLongestSubstring1("aab"));
        System.out.println(System.currentTimeMillis());
        System.out.println(new LSNoRepeat().lengthOfLongestSubstring1("pwwkew"));
        System.out.println(System.currentTimeMillis());
        System.out.println(new LSNoRepeat().lengthOfLongestSubstring1("dvdf"));
        System.out.println(System.currentTimeMillis());
    }

}
