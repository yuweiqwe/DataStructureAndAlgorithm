package com.china.hadoop.bat.chapter9;

import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: TaskSort
 * @Package com.china.hadoop.bat.chapter9
 * @Description: 任务安排
 * 给定一台有m个储存空间的单进程机器;现有n个 请求:第i个请求计算时需要占用R[i]个空间，计算 完成后，
 * 储存计算结果需要占用O[i]个空间(其中 O[i]<R[i])。问如何安排这n个请求的顺序，使得所 有请求都能完成。
 *
 * 如:m=14，n=2，R[1,2]=[10,8]，O[1,2]=[5,6]。可以先运 行第一个任务，计算时占用10个空间，计算完成后占用5 个空间，
 * 剩余9个空间执行第二个任务;但如果先运行第 二个任务，则计算完成后仅剩余8个空间，第一个任务的 计算空间就不够了。
 *
 *
 * 第k个任务的计算占用空间加上前面k-1个任 务的空间占用量之和，越小越好。从而:
 * o(1)+...+o(j)+...o(k-1)+r(k)
 * o(1)+...+o(k)+...o(j-1)+r(j)
 * 使得上一个公式小于下一个公式即可：o(1)+...+o(j)+...o(k-1)+r(k)<=o(1)+...+o(k)+...o(j-1)+r(j)
 * o(j)+r(k)<=o(k)+r(j)  交换位置--> r(k)-o(k)<=r(j)-o(j)
 * 得:将任务按照R[i]-O[i]降序排列即可。
 *
 * @date 2017/11/4
 */
public class TaskSort {

    public static Task[] distribute(int[] r, int[] o, int m, int n){
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = new Task(i, r[i] - o[i]);
        }

        Arrays.sort(tasks, (o1, o2) -> o1.cost > o2.cost ? -1 : 1);//根据cost降序排列

        boolean isSuccess = true;
        int occupation = 0;
        for (int i = 0; i < n; i++) {//判断任务是否能执行完
            int taskId = tasks[i].getTaskId();
            if(occupation + r[taskId] > m){
                isSuccess = false;
                break;
            }
            occupation += o[taskId];
        }


        return isSuccess ? tasks : null;
    }


    public static void main(String[] args) {
        int[] r = {5,8,3,9};
        int[] o = {1,3,2,6};

        int m = 15;
        int n = 4;

        Task[] tasks = distribute(r, o, m, n);

        if(tasks != null){
            Arrays.stream(tasks).forEach(t -> System.out.println(t.toString()));
        }
    }

    protected static class Task{

        protected int taskId;
        protected int cost;

        public Task(int taskId, int cost) {
            this.taskId = taskId;
            this.cost = cost;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "taskId=" + taskId +
                    ", cost=" + cost +
                    '}';
        }
    }

}
