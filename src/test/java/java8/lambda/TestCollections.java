package java8.lambda;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: TestCollections
 * @Package java8.lambda
 * @Description: java通用集合foreach迭代和lambda方式遍历
 * @date 2018/1/11
 */
public class TestCollections {

    @Test
    public void test(){
        List<String> list = Lists.newArrayList("Lambdas", "Default Method", "Stream API", "Date and Time API");

        for (String s : list) {
            System.out.println(s);
        }

        list.forEach(s -> {
            System.out.println(s);
        });


        Set<Long> set = Sets.newHashSet(1l,2l,3l);

        System.out.println(set.stream().collect(Collectors.toList()));

    }

}
