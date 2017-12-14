package DataStructureAndAlgorithm.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public static void email(List<String> list){
        String name = list.get(0);
        String template = list.get(1);
        String namespace = list.get(2);
        String templateName = list.get(3);
        String target = list.get(4);
//        String cc = list.get(5);
        String cc = "";
        String date = Date;

        String sql = email_template.replace("#[Template]", template)
                .replace("#[Namespace]", namespace)
                .replace("#[TemplateName]", templateName)
                .replace("#[Name]", name)
                .replace("#[Target]", target)
                .replace("#[Cc]", cc)
                .replace("#[Date]", date);

        System.out.println(sql);
    }

    public static void sms(List<String> list){
        String template = list.get(0);
        String namespace = list.get(1);
        String templateName = list.get(2);
        String name = list.get(2);
        String target = list.get(3);
        String date = Date;

        String sql = sms_template.replace("#[Template]", template)
                .replace("#[Namespace]", namespace)
                .replace("#[TemplateName]", templateName)
                .replace("#[Name]", name)
                .replace("#[Target]", target)
                .replace("#[Date]", date);

        System.out.println(sql);
    }

    public static void main1(String[] args) throws Exception {
        FileInputStream is = new FileInputStream("//Users//yuwei//Downloads//201712051.xlsx");

        ExcelUtils utils = new ExcelUtils(is, "2007");

        int sheetIndex = 1;
        List<List<String>> sheets = utils.read(sheetIndex, 2, utils.getRowCount(sheetIndex));


        for (List<String> list : sheets) {
            email(list);
        }

        utils.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream is = new FileInputStream("//Users//yuwei//Downloads//201712051.xlsx");

        ExcelUtils utils = new ExcelUtils(is, "2007");

        int sheetIndex = 0;
        List<List<String>> sheets = utils.read(sheetIndex, 2, utils.getRowCount(sheetIndex));


        for (List<String> list : sheets) {
            sms(list);
        }

        utils.close();
    }

}
