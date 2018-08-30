package java8.lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: TestPredicate
 * @Package java8.lambda
 * @Description: 使用lambda表达式和函数式接口Predicate
 * @date 2018/1/11
 */
public class TestPredicate {

    @Test
    public void test(){
        List<String> list = Lists.newArrayList("Lambdas", "Default Method", "Stream API", "Date and Time API");

        System.out.println("Languages which startsWith L :");
        filter(list, (str) -> str.startsWith("L"));

        System.out.println("Languages which endsWith s :");
        filter(list, (str) -> str.endsWith("s"));

        System.out.println("Print all languages :");
        filter(list, (str) -> true);

        System.out.println("Print no languages :");
        filter(list, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(list, (str) -> str.length() > 4);

        Predicate<String> startWith = (n) -> n.startsWith("L");
        Predicate<String> endWith = (n) -> n.endsWith("s");
        System.out.println("compose with Predicate ");
        list.stream().filter(startWith.and(endWith)).forEach(l -> System.out.println(l));// 与 and 或 or 非 negate

    }

    public static <T> void filter(List<T> names, Predicate<T> condition){
        names.forEach(name -> {
            if(condition.test(name)){
                System.out.println(name);
            }
        });

    }


}
