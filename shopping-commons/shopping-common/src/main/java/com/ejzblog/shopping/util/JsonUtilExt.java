package com.ejzblog.shopping.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 11:44
 * @see com.ejzblog.shopping.util
 */
@SuppressWarnings("ALL")
@Slf4j
public class JsonUtilExt {

    public static ObjectNode createNode() {
        return objectMapper.createObjectNode();
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return objectMapper.readValue(jsonData, beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析数组列表
     *
     * @param content   参数
     * @param valueType 类型
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> parseArrayList(String content, Class<T> valueType) {
        if (StrUtil.isBlank(content)) {
            return new ArrayList<>();
        }

        try {

            if (Integer.class == valueType) {
                return (ArrayList<T>) objectMapper.readValue(content, new TypeReference<ArrayList<Integer>>() {
                });
            }

            if (Long.class == valueType) {
                return (ArrayList<T>) objectMapper.readValue(content, new TypeReference<ArrayList<Long>>() {
                });
            }

            if (String.class == valueType) {
                return (ArrayList<T>) objectMapper.readValue(content, new TypeReference<ArrayList<String>>() {
                });
            }

            throw new RuntimeException("Don't support class=" + valueType.getCanonicalName());
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("error_parseArrayList", e);
        }

        return new ArrayList<>();
    }

    /**
     * 解析哈希集
     *
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> HashSet<T> parseHashSet(String content, Class<T> valueType) {
        if (StrUtil.isBlank(content)) {
            return new HashSet<>();
        }

        try {

            if (Integer.class == valueType) {
                return (HashSet<T>) objectMapper.readValue(content, new TypeReference<HashSet<Integer>>() {
                });
            }

            if (Long.class == valueType) {
                return (HashSet<T>) objectMapper.readValue(content, new TypeReference<HashSet<Long>>() {
                });
            }

            if (String.class == valueType) {
                return (HashSet<T>) objectMapper.readValue(content, new TypeReference<HashSet<String>>() {
                });
            }

            throw new RuntimeException("Don't support class=" + valueType.getCanonicalName());
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("error_parseHashSet", e);
        }

        return new HashSet<>();
    }


    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return objectMapper.readValue(jsonData, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 将对象转换成特定pojo。
     * <p>Title: objToPojo</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static <T> T objToPojo(Object data, Class<T> beanType) {
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            return objectMapper.readValue(jsonData, beanType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象转换成特定pojo。
     * <p>Title: objToPojo</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static <T> List<T> objToList(Object data, Class<T> beanType) {
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            return jsonToList(jsonData, beanType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
