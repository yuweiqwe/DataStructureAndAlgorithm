package com.china.hadoop.bat.chapter2;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: KMP
 * @Package com.china.hadoop.bat.chapter2
 * @Description: 字符串匹配算法-KMP:The Knuth-Morris-Pratt Algorithm
 * "前缀"指除了最后一个字符以外，一个字符串的全部头部组合；
 * "后缀"指除了第一个字符以外，一个字符串的全部尾部组合。
 * http://www.cnblogs.com/c-cloud/p/3224788.html
 * @date 2017/10/10
 */
public class KMP {

    /**
     * @Title: getNext
     * @Description: KMP经典算法：对于模式串的位臵j，考察Patternj-1 =p0p1...pj- 2pj-1，查找字符串Patternj-1的最大相等k前缀 和k后缀。
     * * 注:计算next[j]时，考察的字符串是模式串的前 j-1个字符，与pattern[j]无关。
     * 即:查找满足条件的最大的k，使得 p0p1...pk-2pk-1 = pj-kpj-k+1...pj-2pj-1
     * @param pattern
     * @return int[]
     * @throws
     * */
    public static int[] getNext(String pattern){
        int nLen = pattern.length();

        int[] next = new int[nLen];
        next[0] = -1;

        int k = -1;//最大相等K前缀和K后缀的长度
        int j = 0;//游标

        while(j < nLen -1){
            //此刻，k即nex[j-1]，且p[k]标识前缀，p[j]表示后缀
            //注释：k==-1表示未找到k前缀与k后缀香港等，首次分析可先忽略
            if(k == -1 || pattern.charAt(j) == pattern.charAt(k)){
                ++j;
                ++k;
                next[j] = k;
            }else{//p[j]与p[k]匹配失败，则继续递归计算前缀p[next[k]]
                k = next[k];
            }
        }

        return next;
    }

    /**
     * @Title: getBetterNext
     * @Description: KMP经典算法优化：文本串匹配到i，模式串匹配到j，此刻，若text[i]≠pattern[j]，即失配的情况:若next[j]=k，说明模式串应该从j滑动到k位臵;
     * 若此时满足pattern[j]==pattern[k]，因为text[i]≠pattern[j]，所以， text[i] ≠pattern[k] 即i和k没有匹配，应该继续滑动到next[k]。
     * 换句话说:在原始的next数组中，若next[j]=k并且 pattern[j]==pattern[k]，next[j]可以直接等于next[k]
     * @param pattern
     * @return int[]
     * @throws
     * */
    public static int[] getBetterNext(String pattern){
        int nLen = pattern.length();

        int[] next = new int[nLen + 1];
        next[0] = -1;

        int k = -1;
        int j = 0;

        while(j < nLen - 1){
            if(k == -1 || pattern.charAt(j) == pattern.charAt(k)){
                ++j;
                ++k;
                if(pattern.charAt(j) == pattern.charAt(k)){
                    next[j] = next[k];
                }else{
                    next[j] = k;
                }
            }else{
                k = next[k];
            }
        }

        return next;
    }

    public static int kmp(String source, String pattern){
        int ans = -1;
        int i = 0;
        int j = 0;
        int pattern_len = pattern.length();

        int[] next = getNext(pattern);

        while(i < source.length()){
            if(j == -1 || source.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }

            if(j == pattern_len){
                ans = i - pattern_len;
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String pattern = "abaabcaba";
        System.out.println(Arrays.toString(getNext(pattern)));
        System.out.println(Arrays.toString(getBetterNext(pattern)));
        String source = "adfadfadfabaabcabadafdadfad";

        System.out.println(kmp(source, pattern));
    }

}
