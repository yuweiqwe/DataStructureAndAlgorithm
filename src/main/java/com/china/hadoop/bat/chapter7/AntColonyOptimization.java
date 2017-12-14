package com.china.hadoop.bat.chapter7;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: AntColonyOptimization
 * @Package com.china.hadoop.bat.chapter7
 * @Description: 蚁群算法
 * 按百度百科的话来说，蚁群算法(ant colony optimization, ACO)，又称蚂蚁算法，是一种用来在图中寻找优化路径的机率型算法。
 * 它由Marco Dorigo于1992年在他的博士论文中提出，其灵感来源于蚂蚁在寻找食物过程中发现路径的行为。
 * 蚁群算法是一种模拟进化算法，初步的研究表明该算法具有许多优良的性质，并且现在已用于我们生活的方方面面。
 * <p>
 * 基本原理
 * 蚂蚁在运动过程中，会留下一种称为信息素的东西，并且会随着移动的距离，播散的信息素越来越少，所以往往在家或者食物的周围，信息素的浓度是最强的，
 * 而蚂蚁自身会根据信息素去选择方向，当然信息素越浓，被选择的概率也就越大，并且信息素本身具有一定的挥发作用。 蚂蚁的运动过程可以简单归纳如下：
 * 1、当周围没有信息素指引时，蚂蚁的运动具有一定的惯性，并有一定的概率选择其他方向
 * 2、当周围有信息素的指引时，按照信息素的浓度强度概率性的选择运动方向
 * 3、找食物时，蚂蚁留下家相关的A信息素，找家时，蚂蚁留下食物相关的B信息素，并随着移动距离的增加，洒播的信息素越来越少
 * 4、随着时间推移，信息素会自行挥发
 * 一个简单的例子，如果现在有两条通往食物的路径，一条较长路径A,一条较短路径B,虽然刚开始A,B路径上都有蚂蚁，又因为B比A短，蚂蚁通过B花费的时间较短，
 * 随着时间的推移和信息素的挥发，逐渐的B上的信息素浓度会强于A，这时候因为B的浓度比A强，越来越多多蚂蚁会选择B，而这时候B上的浓度只会越来越强。
 * 如果蚂蚁一开始只在A上呢，注意蚂蚁的移动具有一定小概率的随机性，所以当一部分蚂蚁找到B时，随着时间的推移，蚂蚁会收敛到B上，从而可以跳出局部最优。
 * @date 2017/10/31
 */
public class AntColonyOptimization {

    private Ant[] ants;//蚂蚁
    private int antNum;//蚂蚁数量
    private int cityNum;//城市数量
    private int MAX_TURNS;//迭代次数
    private double[][] pheromone;//信息素
    private int[][] distance;//城市间距离
    private int bestLength;//最佳距离--等于或接近最短距离
    private int[] bestPath;//最佳路劲--等于或接近最短路劲

    //三个参数
    private float alpha;//信息素的权值
    private float beta;//城市之间距离的权值
    private float rho;//信息素挥发率

    public AntColonyOptimization() {

    }

    public AntColonyOptimization(int antNum, int cityNum, int MAX_TURNS, float alpha, float beta, float rho) {
        this.antNum = antNum;
        this.ants = new Ant[antNum];
        this.cityNum = cityNum;
        this.MAX_TURNS = MAX_TURNS;
        this.alpha = alpha;
        this.beta = beta;
        this.rho = rho;
    }

    public void init(String filename) throws IOException {
        //读取数据
        int[] x;
        int[] y;
        String strbuff;
        BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        distance = new int[cityNum][cityNum];
        x = new int[cityNum];
        y = new int[cityNum];
        for (int i = 0; i < cityNum; i++) {
            strbuff = data.readLine();
            if(strbuff == null || "".equals(strbuff)){
                continue;
            }
            String[] strcol = strbuff.split(" ");
            x[i] = Integer.valueOf(strcol[1]);
            y[i] = Integer.valueOf(strcol[2]);
        }
        //计算距离矩阵 ，针对具体问题，距离计算方法也不一样，此处用的是att48作为案例，它有48个城市，距离计算方法为伪欧氏距离，最优值为10628
        for (int i = 0; i < cityNum - 1; i++) {
            distance[i][i] = 0;  //对角线为0
            for (int j = i + 1; j < cityNum; j++) {
                double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])) / 10.0);
                int tij = (int) Math.round(rij);
                if (tij < rij) {
                    distance[i][j] = tij + 1;
                    distance[j][i] = distance[i][j];
                } else {
                    distance[i][j] = tij;
                    distance[j][i] = distance[i][j];
                }
            }
        }
        distance[cityNum - 1][cityNum - 1] = 0;

        //初始化信息素矩阵
        pheromone = new double[cityNum][cityNum];
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                pheromone[i][j] = 0.1f;  //初始化为0.1 或者 antNum/dist 蚂蚁数量/路径估计值
            }
        }

        bestLength = Integer.MAX_VALUE;
        bestPath = new int[cityNum];

        //随机放置蚂蚁
        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant();
            ants[i].init(distance, cityNum, alpha, beta);
        }
    }

    public void search() {
        for (int t = 0; t < MAX_TURNS; t++) {
            for (int i = 0; i < antNum; i++) {
                for (int j = 0; j < cityNum - 1; j++) {
                    ants[i].selectNextCity(pheromone);//蚂蚁选择所有路径
                }

                if (ants[i].getTourLength() < bestLength) {//当前蚂蚁路径长度 小于当前最佳路径长度时
                    bestLength = ants[i].getTourLength();//更新最佳路径长度

                    for (int k = 0; k < ants[i].getTabu().size(); k++) {
                        bestPath[k] = ants[i].getTabu().get(k);//更新最近路径
                    }
                }

                //计算每只蚂蚁在各自走过路径上释放的信息素
                for (int s = 0; s < cityNum - 1; s++) {
                    ants[i].getDelta()[ants[i].getTabu().get(s)][ants[i].getTabu().get(s + 1)]
                            = 1.0d / ants[i].getTourLength();
                    ants[i].getDelta()[ants[i].getTabu().get(s + 1)][ants[i].getTabu().get(s)]
                            = 1.0d / ants[i].getTourLength();
                }

            }

            //更新信息素
            updatePheromone();
            //重新初始化蚂蚁
            for (int i = 0; i < antNum; i++) {
                ants[i].init(distance, cityNum, alpha, beta);
            }

            System.out.println("turns : " + t);
            printOptimal();
        }

        //输出结果
        printOptimal();
    }

    private void updatePheromone() {
        //信息素挥发
        for (int i = 0; i < cityNum; i++)
            for (int j = 0; j < cityNum; j++)
                pheromone[i][j] = pheromone[i][j] * (1 - rho);

        //信息素更新
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                for (int k = 0; k < antNum; k++) {
                    pheromone[i][j] += ants[k].getDelta()[i][j];
                }
            }
        }
    }

    private void printOptimal() {
        System.out.println("The optimal length is: " + bestLength);
        System.out.println("The optimal tour is: " + Arrays.toString(bestPath));
    }

    protected static class Ant {

        private List<Integer> tabu;//禁忌表-已访问城市
        private List<Integer> allowedCities;//允许访问的城市
        private double[][] delta; //信息数变化矩阵-蚂蚁在每个城市留下的信息素
        private int[][] distance;//城市间距离

        private int tourLength; //走过路径长度
        private int cityNum;//城市数量

        private int firstCity;//起始城市
        private int currentCity;//当前城市

        private float alpha;//信息素的权值
        private float beta;//城市之间距离的权值

        public Ant() {

        }

        /**
         * @param distance
         * @param cityNum
         * @param alpha
         * @param beta
         * @return void
         * @throws
         * @Title: init
         * @Description: 初始化蚂蚁相关信息
         */
        public void init(int[][] distance, int cityNum, float alpha, float beta) {
            this.cityNum = cityNum;

            this.alpha = alpha;
            this.beta = beta;

            this.distance = distance;

            tabu = Lists.newArrayListWithCapacity(cityNum);
            allowedCities = Lists.newArrayListWithCapacity(cityNum);

            delta = new double[cityNum][cityNum];

            for (int i = 0; i < cityNum; i++) {
                allowedCities.add(i);
                for (int j = 0; j < cityNum; j++) {
                    delta[i][j] = 0f;
                }
            }

            firstCity = Double.valueOf(Math.random()).intValue() % cityNum;
            currentCity = firstCity;
            for (Integer i : allowedCities) {
                if (firstCity == i) {
                    allowedCities.remove(i);
                    break;
                }
            }

            tabu.add(firstCity);
        }

        /**
         * @param pheromone
         * @return void
         * @throws
         * @Title: selectNextCity
         * @Description: 根据当前信息素蚂蚁选择下一个城市
         */
        public void selectNextCity(double[][] pheromone) {
            double[] p = new double[cityNum];
            //总概率的分母
            double sum = 0f;
            for (Integer allowedCity : allowedCities) {
                sum += Math.pow(pheromone[currentCity][allowedCity], alpha)
                        / Math.pow(distance[currentCity][allowedCity], beta);
            }

            //求每一个路径的概率P
            for (Integer allowedCity : allowedCities) {
                p[allowedCity] = Math.pow(pheromone[currentCity][allowedCity], alpha)
                        / Math.pow(distance[currentCity][allowedCity], beta)
                        / sum;
            }

            //轮盘选择下一个城市
            int selectCity = 0;
            double selectP = Math.random();

            double pos = 0f;
            for (int i = 0; i < cityNum; i++) {
                pos += p[i];
                if (pos >= selectP) {
                    selectCity = i;
                    break;
                }
            }

            //禁忌表中增加选择城市
            tabu.add(selectCity);

            //允许访问城市中删除选择城市
            allowedCities.remove(Integer.valueOf(selectCity));//remove方法有index 和 object 重载版本

            //更新当前城市
            currentCity = selectCity;

            //计算当前走过路径长度
            tourLength = calculateTourLength();
        }

        public int calculateTourLength() {
            int len = 0;
            for (int i = 0; i < tabu.size() - 1; i++) {
                len += distance[tabu.get(i)][tabu.get(i + 1)];
            }

            return len;
        }

        public List<Integer> getTabu() {
            return tabu;
        }

        public void setTabu(List<Integer> tabu) {
            this.tabu = tabu;
        }

        public List<Integer> getAllowedCities() {
            return allowedCities;
        }

        public void setAllowedCities(List<Integer> allowedCities) {
            this.allowedCities = allowedCities;
        }

        public double[][] getDelta() {
            return delta;
        }

        public void setDelta(double[][] delta) {
            this.delta = delta;
        }

        public int[][] getDistance() {
            return distance;
        }

        public void setDistance(int[][] distance) {
            this.distance = distance;
        }

        public int getTourLength() {
            return tourLength;
        }

        public void setTourLength(int tourLength) {
            this.tourLength = tourLength;
        }

        public int getCityNum() {
            return cityNum;
        }

        public void setCityNum(int cityNum) {
            this.cityNum = cityNum;
        }

        public int getFirstCity() {
            return firstCity;
        }

        public void setFirstCity(int firstCity) {
            this.firstCity = firstCity;
        }

        public int getCurrentCity() {
            return currentCity;
        }

        public void setCurrentCity(int currentCity) {
            this.currentCity = currentCity;
        }

        public float getAlpha() {
            return alpha;
        }

        public void setAlpha(float alpha) {
            this.alpha = alpha;
        }

        public float getBeta() {
            return beta;
        }

        public void setBeta(float beta) {
            this.beta = beta;
        }
    }

    public static void main(String[] args) throws IOException {
        AntColonyOptimization aco = new AntColonyOptimization(48, 48, 1000, 1.f, 10.f, 0.8f);
        aco.init("/Users/yuwei/IdeaProjects/DataStructureAndAlgorithm/src/main/java/com/china/hadoop/bat/chapter7/data.txt");
        aco.search();
    }

}
