package com.network.network.model.net;

import com.google.gson.annotations.Expose;

import org.json.JSONObject;

/**
 * @author wangjian
 * @title NetCodeStatus
 * @description
 * @modifier
 * @date
 * @since 2016/12/7 9:53
 **/
public class CommonResult {
    private JSONObject data;
    @Expose
    private int code;
    @Expose
    private String msg;
    @Expose
    private String oper;
    @Expose
    private PaginationInfo pagination;

    public PaginationInfo getPagination() {
        return pagination;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "data=" + data.toString() +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", oper='" + oper + '\'' +
                '}';
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


    public String getOper() {
        return oper;
    }


}

