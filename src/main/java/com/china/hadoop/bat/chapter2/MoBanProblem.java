package com.china.hadoop.bat.chapter2;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: MoBanProblem
 * @Package com.china.hadoop.bat.chapter2
 * @Description: 魔板问题
 * @date 2017/10/9
 */
public class MoBanProblem {
    /** 0!=1 1!=1 2!=2 3!=6 4!=24 5!=120 6!=720 7!=5040 */
    static int[] hash = {1, 1, 2, 6, 24, 120, 720, 5040};

    public static void main(String args[]) {
        String number = "12345678";
        String end = "34512678";
        String rs = null;
        number = preProcess(number);
        end = preProcess(end);
        rs = bfs(number, end);
        if (rs != null) {
            System.out.println(rs);
        } else {
            System.out.println("无法变换");
        }


    }

    //由于输入的字符串与魔板上字符的顺序不一致，所以需要对数据进行预处理，将后四位翻转
    private static String preProcess(String number) {
        return number.substring(0, 4) + number.substring(7, 8) + number.substring(6, 7) + number.substring(5, 6) + number.substring(4, 5);
    }

    //上下两行互换
    public static String fun_A(String number) {
        String a = number.substring(4, 8) + number.substring(0, 4);
        return a;
    }

    //每行同时循环右移一格
    public static String fun_B(String number) {
        String a = number.substring(3, 4) + number.substring(0, 3) + number.substring(7, 8) + number.substring(4, 7);
        return a;
    }

    //中间四个方块顺时针旋转一格
    public static String fun_C(String number) {
        String a = number.substring(0, 1) + number.substring(5, 6) + number.substring(1, 2) + number.substring(3, 4) +
                number.substring(4, 5) + number.substring(6, 7) + number.substring(2, 3) + number.substring(7, 8);
        return a;
    }

    private static int getA(String status, int i) {
        int rs = 0;
        for (int k = i + 1; k < status.length(); k++) {
            if (status.charAt(k) < status.charAt(i)) {
                rs++;
            }
        }
        return rs;
    }


    //计算当前状态的cantor序号
    public static int cantor(String status) {
        int[] a = new int[8];
        int rs = 0;
        for (int i = 0, j = 7; i < status.length(); i++, j--) {
            a[j] = getA(status, i);
        }
        for (int i = 7; i >= 0; i--) {
            rs += a[i] * hash[i];//康托展开 X=an*(n-1)!+an-1*(n-2)!+...+ai*(i-1)!+...+a2*1!+a1*0!
        }
        return rs;

    }


    public static boolean matches(String a, String b) {
        if (a.length() != b.length()) return false;
        if (a.equals(b)) return true;
        else return false;
    }

    /**
    * @Title: bfs
    * @Description: 广度优先搜索--树状结构：从根节点开始遍历，一层层遍历--queue队列-先进先出
    * @param start
    * @param end
    * @return String
    * @throws
    */
    public static String bfs(String start, String end) {
        //某个序列状态是否已被搜索过
        boolean[] visited = new boolean[40320];
        String[] ans = new String[40320];
        Queue<String> status = new LinkedBlockingDeque<>();
        status.offer(start);
        while (!status.isEmpty()) {
            String tmp = status.poll();
            if (matches(tmp, end)) {
                return ans[cantor(tmp)];
            }
            if (!visited[cantor(tmp)]) {
                if (ans[cantor(tmp)] == null) ans[cantor(tmp)] = "";
                if (ans[cantor(tmp)] == "" || ans[cantor(tmp)].substring(ans[cantor(tmp)].length() - 1, ans[cantor(tmp)].length()) != "A") {
                    String fun_A_tmp = fun_A(tmp);
                    status.offer(fun_A_tmp);
                    ans[cantor(fun_A_tmp)] = ans[cantor(tmp)] + "A";
                }
                if (ans[cantor(tmp)].length() < 3 || ans[cantor(tmp)].substring(ans[cantor(tmp)].length() - 3, ans[cantor(tmp)].length()) != "BBB") {
                    String fun_B_tmp = fun_B(tmp);
                    status.offer(fun_B_tmp);
                    ans[cantor(fun_B_tmp)] = ans[cantor(tmp)] + "B";
                }
                if (ans[cantor(tmp)].length() < 3 || ans[cantor(tmp)].substring(ans[cantor(tmp)].length() - 3, ans[cantor(tmp)].length()) != "CCC") {
                    String fun_C_tmp = fun_C(tmp);
                    status.offer(fun_C_tmp);
                    ans[cantor(fun_C_tmp)] = ans[cantor(tmp)] + "C";
                }
            }
            visited[cantor(tmp)] = true;


        }
        return null;

    }

}
