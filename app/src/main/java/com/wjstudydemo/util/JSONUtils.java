package com.wjstudydemo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * @author wangjian
 * @title JsonUtil
 * @description
 * @modifier
 * @date
 * @since 2016/12/22 16:23
 **/
public class JSONUtils {
    public JSONUtils() {
    }

    public static String parser2json(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static String parser2jsonWithoutExpose(Object obj) {
        Gson gson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(obj);
    }

    public static String parserDateBean2json(Object obj) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Date.class, new JSONUtils.DateSerializerUtils()).setDateFormat(1).create();
        return gson.toJson(obj);
    }

    public static <T> List<T> fromJsonArray(String strjson, TypeToken typeToken) {
        try {
            Gson e = new Gson();
            List target2 = (List)e.fromJson(strjson, typeToken.getType());
            return target2;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String strjson, Class<T> cls) {
        try {
            Gson e = new Gson();
            T target2 = e.fromJson(strjson, cls);
            return target2;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson2DateBean(String json, Class<T> cls) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Date.class, new JSONUtils.DateDeserializerUtils()).setDateFormat(1).create();
        return gson.fromJson(json, cls);
    }

    private static class DateSerializerUtils implements JsonSerializer<Date> {
        private DateSerializerUtils() {
        }

        public JsonElement serialize(Date date, Type type, JsonSerializationContext content) {
            return new JsonPrimitive(Long.valueOf(date.getTime()));
        }
    }

    private static class DateDeserializerUtils implements JsonDeserializer<Date> {
        private DateDeserializerUtils() {
        }

        public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }
}
