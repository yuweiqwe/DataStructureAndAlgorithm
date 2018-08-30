package java8.lambda;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: TestActionListener
 * @Package java8.lambda
 * @Description: java swing编程中ActionListener通用实现 和 lambda表达式实现方式
 * @date 2018/1/11
 */
public class TestActionListener {

    @Test
    public void testActionListener(){
        JButton button = new JButton("show");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });

        JButton show = new JButton("show");
        show.addActionListener((e) -> {
            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
        });

    }

}
