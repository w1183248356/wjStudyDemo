package com.network.network.util;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Jin on 2016/3/31.
 * 网络操作工具类
 */
public class NetworkUtil {
    public static String map2Json(Map<String, String> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }


}
