package DataStructureAndAlgorithm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: SqlUtils
 * @Package DataStructureAndAlgorithm.util
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2017/11/16
 */
public class SqlUtils {

    public static String sms_template = "INSERT INTO CSC_MessageTemplate (AddTime, UpdateTime, ModuleType, MessageType, Name, Template," +
            "                                 LoginId, IsDelete, Namespace, TemplateName, Target, Cc, Remail, TemplateType)" +
            "VALUES ('#[Date]', '#[Date]', 1, 1, '#[Name]'," +
            "                               '#[Template]', 12756, 0," +
            "                               '#[Namespace]', '#[TemplateName]', '#[Target]', null, null, 'SMS');";

    public static String email_template = "INSERT INTO CSC_MessageTemplate (AddTime, UpdateTime, ModuleType, MessageType, Name, Template," +
            "                                 LoginId, IsDelete, Namespace, TemplateName, Target, Cc, Remail, TemplateType)" +
            "VALUES ('#[Date]', '#[Date]', 1, 2, '#[Name]'," +
            "                               '#[Template]', 12756, 0," +
            "                               '#[Namespace]', '#[TemplateName]', '#[Target]', '#[Cc]', null, 'EMAIL');";

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String Date = format.format(new Date());

    public static Set<String> symbols = Sets.newHashSet();

    public static String version = "V1_";

    public static void email(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        if(list.get(0) == null || "".equals(list.get(0).trim())){
            return;
        }
        String name = list.get(0);
        String template = list.get(1);
        String namespace = list.get(2);
        String templateName = list.get(3);
        String target = list.get(4);
        String cc = list.get(5);
        String date = Date;

        String sql = email_template.replace("#[Template]", template)
                .replace("#[Namespace]", namespace)
                .replace("#[TemplateName]", version + namespace + "-" + templateName)
                .replace("#[Name]", name)
                .replace("#[Target]", target)
                .replace("#[Cc]", cc)
                .replace("#[Date]", date);

        System.out.println(sql);
    }

    public static void sms(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        if(list.get(0) == null || "".equals(list.get(0).trim())){
            return;
        }
        String template = list.get(0);
        String namespace = list.get(1);
        String templateName = list.get(2);
        String name = list.get(2);
        String target = list.get(3);
        String date = Date;

        String sql = sms_template.replace("#[Template]", template)
                .replace("#[Namespace]", namespace)
                .replace("#[TemplateName]", version + namespace + "-" + templateName)
                .replace("#[Name]", name)
                .replace("#[Target]", target)
                .replace("#[Date]", date);

        System.out.println(sql);
    }

    public static void validate(List<String> list){
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        if(list.get(0) == null || "".equals(list.get(0).trim())){
            return;
        }

        for (String l : list) {
            List<String> subs = ActionConfigPlaceholderHelper.extractAllVariables(l);

            if(!subs.isEmpty()){
                symbols.addAll(subs);
            }
        }


    }

    public static void main1(String[] args) throws Exception {
        FileInputStream is = new FileInputStream("//Users//yuwei//Downloads//email.xlsx");

        ExcelUtils utils = new ExcelUtils(is, "2007");

        int sheetCount = utils.getSheetCount();
        for (int i = 0; i < sheetCount; i++) {
            int sheetIndex = i;
            List<List<String>> sheets = utils.read(sheetIndex, 1, utils.getRowCount(sheetIndex));

            for (List<String> list : sheets) {
                email(list);
            }
        }

        utils.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream is = new FileInputStream("//Users//yuwei//Downloads//sms.xlsx");

        ExcelUtils utils = new ExcelUtils(is, "2007");

        int sheetCount = utils.getSheetCount();
        for (int i = 0; i < sheetCount; i++) {
            int sheetIndex = i;
            List<List<String>> sheets = utils.read(sheetIndex, 1, utils.getRowCount(sheetIndex));

            for (List<String> list : sheets) {
                sms(list);
            }
        }

        utils.close();
    }

    public static void main3(String[] args) throws Exception {
//        FileInputStream is = new FileInputStream("//Users//yuwei//Downloads//旅游客服中心邮件20171129.xlsx");
        FileInputStream is = new FileInputStream("//Users//yuwei//Downloads//旅游客服中心邮件2018.2.2-0205.xlsx");


        ExcelUtils utils = new ExcelUtils(is, "2007");

        int sheetCount = utils.getSheetCount();
        for (int i = 0; i < sheetCount; i++) {
            int sheetIndex = i;
            List<List<String>> sheets = utils.read(sheetIndex, 1, utils.getRowCount(sheetIndex));

            for (List<String> list : sheets) {
                validate(list);
            }
        }

        utils.close();

        System.out.println(symbols);
    }

    public static void main4(String[] args) throws IOException {
        String str = "";

        String[] arr = str.split(" \n");

        Set<Integer> set = Sets.newHashSet();
        for (String s : arr) {
            Map<String, String> map = JacksonUtil.toMap(s, String.class, String.class);

            set.addAll(JacksonUtil.toSet(map.get("rightValue"), Integer.class));

        }

        List<Integer> list = Lists.newArrayList(set.iterator());
        Collections.sort(list);

        System.out.println(list);
        System.out.println(list.size());
    }

}
