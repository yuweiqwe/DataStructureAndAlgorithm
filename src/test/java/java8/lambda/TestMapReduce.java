package java8.lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: TestMapReduce
 * @Package java8.lambda
 * @Description: lambda表达式的Map和Reduce示例
 * @date 2018/1/11
 */
public class TestMapReduce {

    @Test
    public void test(){
        List<Integer> costBeforeTax = Lists.newArrayList(100, 200, 300, 400, 500, 200);

        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }

        costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
        costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(r -> System.out.println(r));

        List<String> result = Lists.newArrayList();

        double bill = 0;
        bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println(bill);
        bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> {return sum + cost;}).get();
        System.out.println(bill);

        List<Double> filterResults = costBeforeTax.stream().map((cost) -> cost + .12*cost).filter((cost) -> cost < 400).collect(Collectors.toList());
        System.out.println(filterResults);
        String strResult = costBeforeTax.stream().map((cost) -> cost + .12*cost).filter((cost) -> cost < 400).map((cost) -> cost.toString()).collect(Collectors.joining(","));
        System.out.println(strResult);

        filterResults = costBeforeTax.stream().map((cost) -> cost + .12*cost).filter((cost) -> cost < 400).distinct().collect(Collectors.toList());


        Map<Double, List<Double>> map = costBeforeTax.stream().map((cost) -> cost + .12*cost).filter((cost) -> cost < 400).collect(Collectors.groupingBy(t -> t));
        System.out.println(map);

    }

}
