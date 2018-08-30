package java8.concurrent;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BlockingQueue
 * @Package java8.concurrent
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/8/9
 */
public class BlockingQueue {

    @Test
    public void test1(){
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            blockingQueue.add(i);
        }

        blockingQueue.add(100);
    }

    @Test
    public void test2(){
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            blockingQueue.add(i);
        }

        try {
            blockingQueue.put(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            blockingQueue.add(i);
        }

        System.out.println(blockingQueue.offer(100));
        try {
            System.out.println(blockingQueue.offer(100, 1000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            blockingQueue.add(i);
        }

        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.poll(1000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            blockingQueue.add(i);
        }

        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.peek());
            System.out.println(blockingQueue.poll(1000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
