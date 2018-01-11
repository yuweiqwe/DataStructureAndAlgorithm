package DataStructureAndAlgorithm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Json工具类
 */
public final class JacksonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtil.class);
    private static final ObjectMapper OBJECT_MAPPER;

    private JacksonUtil() {
    }

    static {
        OBJECT_MAPPER = new ObjectMapper();
        //如果json中的key在目标对象中没有对应的setter方法, 是否抛出异常, 反序列化失败
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    /**
     * 将Json字符串转成JsonNode对象
     *
     * @param jsonStr 待转换的json字符串
     *
     * @return JsonNode对象 或 Null(转换失败时 或 字符串为空)
     */
    public static JsonNode toJsonNode(String jsonStr) throws IOException {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }

        return OBJECT_MAPPER.readTree(jsonStr);
    }

    /**
     * 将对象转成Json格式字符串
     *
     * @param obj 待转换对象
     *
     * @return JSON字符串, 如果对象为null或转换异常则返回""
     */
    public static String toJsonStrWithEmptyDefault(Object obj) {
        String jsonStr = "";

        try {
            jsonStr = toJsonStr(obj);
        } catch (Exception e) {
            LOGGER.warn("将对象转成Json字符串抛出异常, obj: {}", jsonStr, obj, e);
        }

        return jsonStr;
    }

    /**
     * 将对象转成Json格式字符串
     *
     * @param obj 待转换对象
     *
     * @return JSON字符串, 如果对象为null则返回null
     */
    public static String toJsonStr(Object obj) throws JsonProcessingException {
        if (obj == null) {
            return null;
        }

        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    /**
     * 将Json字符串转成指定类型的对象
     *
     * @param jsonStr     待转换的Json字符串
     * @param targetClass 目标类型
     *
     * @return 对象, 如果转换失败 或 字符串为空串 则返回null
     */
    public static <T> T toBeanWithNullDefault(String jsonStr, Class<T> targetClass) {
        T bean = null;
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }

        try {
            bean = toBean(jsonStr, targetClass);
        } catch (IOException e) {
            LOGGER.warn("将Json字符串转成对象抛出异常, JsonString: {} , targetClass: {}", jsonStr, targetClass, e);
        }

        return bean;
    }

    public static <T> T toBean(JsonNode node, Class<T> targetClass) throws JsonProcessingException {
        return OBJECT_MAPPER.treeToValue(node, targetClass);
    }

    /**
     * 将Json字符串转成指定类型的对象
     *
     * @param jsonStr     Json字符串
     * @param targetClass 目标类型
     *
     * @return Java对象
     */
    public static <T> T toBean(String jsonStr, Class<T> targetClass) throws IOException {
        return OBJECT_MAPPER.readValue(jsonStr, targetClass);
    }

    /**
     * 将Json字符串转成包含指定类型元素的Set集合
     *
     * @param jsonStr   待转换的Json字符串
     * @param itemClass 要转换成的Set集合中元素的类型
     *
     * @return Json字符串为空时返回空的集合(size等于0, 不是null)
     */
    public static <T> Set<T> toSetWithEmptyDefault(String jsonStr, Class<T> itemClass) throws IOException {
        if (StringUtils.isBlank(jsonStr)) {
            return new HashSet<>(0);
        }

        return toSet(jsonStr, itemClass);
    }

    /**
     * 将Json字符串转成包含指定类型元素的Set集合
     *
     * @param jsonStr   待转换的Json字符串
     * @param itemClass 要转换成的Set集合中元素的类型
     *
     * @return 包含指定类型元素的Set集合
     */
    public static <T> Set<T> toSet(String jsonStr, Class<T> itemClass) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory()
                                         .constructCollectionType(Set.class, itemClass);
        return OBJECT_MAPPER.readValue(jsonStr, javaType);
    }

    /**
     * 将Json字符串转成包含指定类型元素的List集合
     *
     * @param jsonStr   待转换的Json字符串
     * @param itemClass 要转换成的List集合中元素的类型
     *
     * @return Json字符串为空时返回null
     */
    public static <T> List<T> toListWithEmptyDefault(String jsonStr, Class<T> itemClass) {
        if (StringUtils.isBlank(jsonStr)) {
            return Lists.newArrayList();
        }

        try {
            return toList(jsonStr, itemClass);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    /**
     * 将Json字符串转成包含指定类型元素的List集合
     *
     * @param jsonStr   待转换的Json字符串
     * @param itemClass 要转换成的List集合中元素的类型
     *
     * @return 包含指定类型元素的List集合
     */
    public static <T> List<T> toList(String jsonStr, Class<T> itemClass) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory()
                                         .constructCollectionType(List.class, itemClass);
        return OBJECT_MAPPER.readValue(jsonStr, javaType);
    }

    /**
     * 将Json字符串转成包含指定类型元素的Map
     *
     * @param jsonStr    待转换的Json字符串
     * @param keyClass   要转换成的Map中的Key的类型
     * @param valueClass 要转换成的Map中的Value的类型
     *
     * @return Json字符串为空时返回空的Map(不是null)
     */
    public static <K, V> Map<K, V> toMapWithEmptyDefault(String jsonStr, Class<K> keyClass, Class<V> valueClass) throws IOException {
        if (StringUtils.isBlank(jsonStr)) {
            return new HashMap<>(0);
        }

        return toMap(jsonStr, keyClass, valueClass);
    }

    /**
     * 将Json字符串转成包含指定类型元素的Map
     *
     * @param jsonStr    待转换的Json字符串
     * @param keyClass   要转换成的Map中的Key的类型
     * @param valueClass 要转换成的Map中的Value的类型
     *
     * @return 包含指定类型元素的Map
     */
    public static <K, V> Map<K, V> toMap(String jsonStr, Class<K> keyClass, Class<V> valueClass) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory()
                                         .constructMapType(Map.class, keyClass, valueClass);
        return OBJECT_MAPPER.readValue(jsonStr, javaType);
    }

    /**
     * 将Json字符串转成包含指定类型元素的ConcurrentHashMap
     *
     * @param jsonStr    待转换的Json字符串
     * @param keyClass   要转换成的Map中的Key的类型
     * @param valueClass 要转换成的Map中的Value的类型
     *
     * @return 包含指定类型元素的Map
     */
    public static <K, V> ConcurrentHashMap<K, V> toConcurrentMap(String jsonStr, Class<K> keyClass, Class<V> valueClass) throws IOException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory()
                                         .constructMapType(ConcurrentHashMap.class, keyClass, valueClass);
        return OBJECT_MAPPER.readValue(jsonStr, javaType);
    }

    /**
     * 将Json字符串转成包含指定类型元素的ConcurrentHashMap, 且元素的value为List类型
     *
     * @param jsonStr    待转换的Json字符串
     * @param keyClass   要转换成的Map中的Key的类型
     * @param valueClass 要转换成的Map中的作为Value的list中元素的类型
     *
     * @return 包含指定类型元素的Map
     */
    public static <K, V> ConcurrentHashMap<K, List<V>> toConcurrentMapWithListValue(String jsonStr, Class<K> keyClass, Class<V> valueClass)
            throws IOException {
        JavaType keyJavaType = OBJECT_MAPPER.getTypeFactory()
                                            .constructType(keyClass);
        JavaType valueJavaType = OBJECT_MAPPER.getTypeFactory()
                                              .constructCollectionType(List.class, valueClass);
        JavaType javaType = OBJECT_MAPPER.getTypeFactory()
                                         .constructMapType(ConcurrentHashMap.class, keyJavaType, valueJavaType);
        return OBJECT_MAPPER.readValue(jsonStr, javaType);
    }
}
