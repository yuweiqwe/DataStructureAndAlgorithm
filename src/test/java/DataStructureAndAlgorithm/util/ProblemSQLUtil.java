package DataStructureAndAlgorithm.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ProblemSQLUtil
 * @Package DataStructureAndAlgorithm.util
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/4/10
 */
public class ProblemSQLUtil {

    public static final String problemSQL = "INSERT INTO config_problem_classification (id, name, add_time, update_time, creator_id, updater_id, status) " +
            " VALUES(#id#, '#name#', '2018-03-22 10:50:49', '2018-03-22 02:48:39', 1, 1, 1);";

    public static final String relationSQL = "INSERT INTO relation_problem_classification_bg (problem_classification_id, bg_id, add_time, update_time, creator_id, updater_id, is_delete) " +
            " VALUES(#problemId#, #bgId#, '2018-03-22 11:19:41', '2018-03-22 03:19:27', 1, 1, 0);";

    public static void main(String[] args) throws Exception {
        Map<Integer, List<String>> relationMap = Maps.newHashMap();

        Set<String> problemSet = Sets.newHashSet();
        Map<String, Integer> problemMap = Maps.newHashMap();

        Map<String, Integer> bgMap = Maps.newHashMap();

        for (BgEnum bgEnum : BgEnum.values()) {
            bgMap.put(bgEnum.getName(), bgEnum.getId());
        }

        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/yuwei/IdeaProjects/DataStructureAndAlgorithm/src/test/java/DataStructureAndAlgorithm/util/problem.txt")));

        String content = null;
        Integer bgId = null;
        while((content = br.readLine()) != null){
            if("".equals(content.trim())){
                continue;
            }

            content = content.trim();
            if(bgMap.get(content.trim()) != null){
                bgId = bgMap.get(content.trim());
                relationMap.put(bgId, Lists.newArrayList());
            }else{
                relationMap.get(bgId).add(content);

                problemSet.add(content);
            }
        }

        int id = 1;
        for (String problem : problemSet) {
            problemMap.put(problem, id++);
        }

        //生成problemSQL
        problemMap.forEach((k,v) -> System.out.println(problemSQL.replace("#id#", v.toString()).replace("#name#", k)));

        System.out.println();
        System.out.println();

        //生成relationSQL
        relationMap.forEach((k,v) -> execute(k, v, problemMap));

    }

    public static void execute(Integer bgId, List<String> problems, Map<String, Integer> problemMap){
        problems.forEach(p -> System.out.println(relationSQL.replace("#problemId#", problemMap.get(p).toString()).replace("#bgId#", bgId.toString())));
    }

}
