package java8.thread;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ThreadTest
 * @Package java8.thread
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/8/9
 */
public class ThreadTest {

    @Test
    public void test1(){
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(() -> {

            while(true){
                synchronized (lock1) {
                    lock1.notify();

                    System.out.println(1);

                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        });

        Thread thread2 = new Thread(() -> {

            while(true){
                synchronized (lock1) {
                    lock1.notify();

                    System.out.println(2);

                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        });

        thread1.start();
        thread2.start();


    }

    @Test
    public void test2(){
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Condition condition1 = lock1.newCondition();
        Condition condition2 = lock1.newCondition();

        System.out.println("go");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(true){
                    System.out.println(Thread.currentThread().getName() + i++);
                    lock1.lock();

                    System.out.println("A");
                    try {
                        condition2.signal();
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }

                    lock1.unlock();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(true){
                    System.out.println(Thread.currentThread().getName() + i++);
                    lock1.lock();

                    System.out.println("B");
                    try {
                        condition1.signal();
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    lock1.unlock();
                }
            }
        });

        thread1.start();
        thread2.start();

    }

}
