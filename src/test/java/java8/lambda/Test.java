package java8.lambda;

import com.algorithm.sort.BubbleSort;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Test
 * @Package java8.lambda
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/3/1
 */
public class Test {

    @org.junit.Test
    public void test(){

        List<String> list = Arrays.stream(new String[]{"1", "2", "", null}).filter(t -> t != null && !"".equals(t)).collect(Collectors.toList());

        System.out.println(list);

        List<String> bgIdStringList = Lists.newArrayList(Splitter.on(",").split(""));


    }

}
