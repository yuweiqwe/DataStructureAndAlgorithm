package common;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Test
 * @Package common
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/8/7
 */
public class Test {

    @org.junit.Test
    public void test1(){
        int num = 123456789;

        System.out.println(Integer.toBinaryString(num));
        //6 -> 110
        System.out.println(num & 6);
        System.out.println(num % 6);
    }

    public static void main(String[] args) {
        System.out.println(new Date(1535002024));

    }

}
