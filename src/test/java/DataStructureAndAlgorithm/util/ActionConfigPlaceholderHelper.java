package DataStructureAndAlgorithm.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * 动作配置中, 占位符相关方法
 *
 * @author lijianye Created on 17/2/20.
 */
public class ActionConfigPlaceholderHelper {

    public static final String placeholderPrefix = "${";

    public static final String placeholderSuffix = "}";

    /**
     * 提取第一个参数
     *
     * @param str
     * @return
     */
    public static String extractFirstVariable(String str) {

        int startIndex = str.indexOf(placeholderPrefix);
        if (startIndex != -1) {
            int endIndex = str.indexOf(placeholderSuffix, startIndex + placeholderPrefix.length());

            if (endIndex != -1) {
                String placeholder = str.substring(startIndex + placeholderPrefix.length(), endIndex);
                return placeholder;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 提取所有参数
     *
     * @param str
     * @return
     */
    public static List<String> extractAllVariables(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return Collections.emptyList();
        }
        List<String> result = Lists.newArrayList();

        int startIndex = str.indexOf(placeholderPrefix);
        if (startIndex != -1) {
            int endIndex = str.indexOf(placeholderSuffix, startIndex + placeholderPrefix.length());

            if (endIndex != -1) {
                String placeholder = str.substring(startIndex + placeholderPrefix.length(), endIndex);
                String leftString = str.substring(endIndex);

                result.add(placeholder);
                result.addAll(extractAllVariables(leftString));

            } else {
                return Collections.emptyList();
            }
        }

        return result;
    }

}
