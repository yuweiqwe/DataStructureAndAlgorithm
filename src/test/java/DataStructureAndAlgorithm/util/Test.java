package DataStructureAndAlgorithm.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: Test
 * @Package DataStructureAndAlgorithm.util
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/1/22
 */
public class Test {

    public static Integer lock = 0;



    public static void main(String[] args) throws IOException, InterruptedException {
        String json = "[]";

        List<Map> list = JacksonUtil.toList(json, Map.class);

        final Map<String, String> map = Maps.newHashMap();

        for (Map m : list) {
            map.put(m.get("id").toString(), "4592");
        }

        System.out.println(JacksonUtil.toJsonStr(map));


        new Thread(() -> {
            System.out.println("thread start");
            try {
                Thread.sleep(200l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock){
                try {
                    System.out.println("thread wait");
                    lock.wait(0);
                    System.out.println("awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("thread end");
        }).start();


        Thread.sleep(3000l);
        System.out.println("notify");
        synchronized (lock){
            lock.notify();
            System.out.println("release lock");
        }
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");

    }

}
