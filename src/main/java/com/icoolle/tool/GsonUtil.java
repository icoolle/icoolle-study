package com.icoolle.tool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 操作java对象和json数据之间的相互转换
 */
public class GsonUtil {

    public static Gson GSON;

    public static Gson GSONBUILDER;


    static {
        GSONBUILDER = getGsonBuilder();
        GSON = getGson();
    }

    /**
     * 获取gson对象
     *
     * @return
     */
    public synchronized static Gson getGson() {
        if (null == GSON) {
            GSON = new Gson();
        }
        return GSON;
    }


    /**
     * Gson 构建
     *
     * @return
     */
    public synchronized static Gson getGsonBuilder() {
        if (null == GSONBUILDER) {
            GSONBUILDER = new GsonBuilder()
                    .setLenient()// json宽松
                    .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                    .serializeNulls() //智能null
                    .setPrettyPrinting()// 调教格式
                    .disableHtmlEscaping() //默认是GSON把HTML 转义的
                    .create();
        }
        return GSONBUILDER;
    }

}
