package com.china.hadoop.bat.chapter3;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: HammingWeight
 * @Package com.china.hadoop.bat.chapter3
 * @Description: HammingWeight
 * (n & 0x55555555) 5=0101 得到所有低位  0xaaaaaaaa  a=1010 得到所有高位，然后高位右移1位，数的所有高位错位到低位，然后低位和高位相加，结果占用2位（最大10  2进制）
 *
 * @date 2017/10/12
 */
public class HammingWeight {

    /**
     * @Title: algorithm
     * @Description:
     * 0x55555555  binary: 5 = 0101
     * 0xaaaaaaaa  binary: a = 1010
     * 0x33333333  binary: 3 = 0011
     * 0xcccccccc  binary: c = 1100
     * 0x0f0f0f0f  binary: c = 00001111
     * 0xf0f0f0f0  binary: c = 11110000
     * 0x00ff00ff  binary: c = 0000000011111111
     * 0xff00ff00  binary: c = 1111111100000000
     * 0x0000ffff  binary: c = 00000000000000001111111111111111
     * 0xffff0000  binary: c = 11111111111111110000000000000000
     * @param n
     * @return long
     * @throws
     * */
    public static long algorithm(long n){
        n = (n & 0x55555555) + ((n & 0xaaaaaaaa) >> 1);
        n = (n & 0x33333333) + ((n & 0xcccccccc) >> 2);
        n = (n & 0x0f0f0f0f) + ((n & 0xf0f0f0f0) >> 4);
        n = (n & 0x00ff00ff) + ((n & 0xff00ff00) >> 8);
        n = (n & 0x0000ffff) + ((n & 0xffff0000) >> 16);

        return n;
    }

    public static void main(String[] args) {
        System.out.println(algorithm(1));
        System.out.println(algorithm(2));
        System.out.println(algorithm(3));
        System.out.println(algorithm(7));
        System.out.println(algorithm(15));
        System.out.println(algorithm(31));
        System.out.println(algorithm(11111));
    }

}
