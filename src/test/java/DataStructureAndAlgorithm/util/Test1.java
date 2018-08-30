package DataStructureAndAlgorithm.util;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Test1
 * @Package DataStructureAndAlgorithm.util
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/4/23
 */
public class Test1 {

    public static final String a = "(";
    public static final String b = ")";

    public static void execute(int start, int left, int total, List<String> list){
        if(left == 0){
            System.out.println(list);
            return;
        }

        if(left == total){
            int index = start + 0;
            list.set(index, a);
            list.set(total * 2 - 1 - index, b);
            left--;
        }

        for (int i = 1; i < total * 2 - 1; i++) {
            int index = start + i;
            list.set(index, a);
            list.set(total * 2 - 1 - index, b);
            execute(start + 1, left - 1, total, list);
            list.set(start + i, " ");
            list.set(total * 2 - 1 - index, " ");
        }
    }



    public static void main(String[] args) {
        int total = 3;
        List<String> list = Lists.newArrayListWithCapacity(total * 2);
        for (int i = 0; i < total * 2; i++) {
            list.add(" ");
        }
        execute(0, total, total, list);
    }


}
