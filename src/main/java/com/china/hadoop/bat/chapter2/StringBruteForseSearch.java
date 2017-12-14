package com.china.hadoop.bat.chapter2;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: StringBruteForseSearch
 * @Package com.china.hadoop.bat.chapter2
 * @Description: 字符串匹配-暴力搜索
 * @date 2017/10/10
 */
public class StringBruteForseSearch {

    public static int search(String source, String pattern){
        int i = 0;
        int j = 0;
        int size = pattern.length();
        int nLast = source.length() - size;

        while(i <= nLast && j < size){
            if(source.charAt(i + j) == pattern.charAt(j)){
                j++;
            }else{
                i++;
                j = 0;
            }
        }

        if(j >= size){
            return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        String source = "abdfkljaldjfaoigqdfdsaghi";
        String pattern = "oig";
        System.out.println(search(source, pattern));
    }

}
