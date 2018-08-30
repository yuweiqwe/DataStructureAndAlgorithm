package java8.lambda;

import org.junit.Test;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: TestRunnable
 * @Package java8.lambda
 * @Description: java通用Runnable和lambda表达式实现Runnable
 * @date 2018/1/11
 */
public class TestRunnable {

    @Test
    public void testRunnable(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("common Runnable");
            }
        }).start();


        new Thread(() -> {
            System.out.println("lambda Runnable");
        }).start();

    }


}
