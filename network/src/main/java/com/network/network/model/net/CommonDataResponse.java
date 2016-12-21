package com.network.network.model.net;

import com.google.gson.annotations.SerializedName;

/**
 * @author wangjian
 * @title CommonDataResponse
 * @description
 * @modifier
 * @date
 * @since 2016/12/7 9:53
 **/

public class CommonDataResponse<T> {
    @SerializedName("data")
    T data;
    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
