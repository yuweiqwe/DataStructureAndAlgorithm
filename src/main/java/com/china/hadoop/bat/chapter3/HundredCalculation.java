package com.china.hadoop.bat.chapter3;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: HundredCalculation
 * @Package com.china.hadoop.bat.chapter3
 * @Description: 百数问题
 * 在1,2,3,4,5,6,7,8,9(顺序不能变)数字之间插入 运算符+或者运算符-或者什么都不插入，使 得计算结果是100。
 * 如:1+2+34-5+67-8+9=100
 * 请输出所有的可行运算符方式。
 * Depth First Search  深度优先搜索
 * Breadth First Search 广度优先搜索
 * @date 2017/10/15
 */
public class HundredCalculation {

    private static final char ADD = '+';
    private static final char MINUS = '-';
    private static final char EMPTY = ' ';

    public static void getHundredResult(int index, int[] numbers, char[] symbols){
        if(index == numbers.length - 1){
            int result = cal(numbers, symbols);
            if(result == 100){
                System.out.println(Arrays.toString(numbers));
                System.out.println(Arrays.toString(symbols));
            }
            return;
        }

        symbols[index] = ADD;
        getHundredResult(index + 1, numbers, symbols);
        symbols[index] = MINUS;
        getHundredResult(index + 1, numbers, symbols);
        symbols[index] = EMPTY;
        getHundredResult(index + 1, numbers, symbols);

    }

    public static int cal(int[] numbers, char[] symbols){
        int number = numbers[0];
        char symbol = EMPTY;

        for (int i = 0; i < symbols.length; i++) {
            symbol = symbols[i];

            switch (symbol){
                case ADD :
                    number += numbers[i + 1];
                    break;
                case MINUS :
                    number -= numbers[i + 1];
                    break;
                case EMPTY :
                    number = number * 10 + numbers[i + 1];
                    break;
                default:
                    ;
            }
        }

        return number;
    }

    public static void main1(String[] args) {
        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9};
//        char[] symbols = new char[]{EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY};
//        char[] symbols = new char[]{ADD,ADD,ADD,ADD,ADD,ADD,ADD,ADD};
//        char[] symbols = new char[]{MINUS,MINUS,MINUS,MINUS,MINUS,MINUS,MINUS,MINUS};
        char[] symbols = new char[]{EMPTY,EMPTY,MINUS,MINUS,MINUS,MINUS,ADD,MINUS};
        System.out.println(cal(numbers, symbols));
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9};
        char[] symbols = new char[numbers.length - 1];
        getHundredResult(0, numbers, symbols);
    }

}
