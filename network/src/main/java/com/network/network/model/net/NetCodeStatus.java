package com.network.network.model.net;

/**
 * @author wangjian
 * @title NetCodeStatus
 * @description
 * @modifier
 * @date
 * @since 2016/12/7 9:53
 **/
public class NetCodeStatus {
    /**
     * 成功返回的code
     */
    public static final int OK = 200;

    /**
     * 强制升级的code
     */
    public static final int FORCE_UPDATE = 0;


    /**
     * 上传的图片类 icon
     */
    public static final String IMGTYPE_ICON = "icon";


    /**
     * 数据集为空，仅对查询有效
     */
    public static final int CODE_NULL = 4;


    //以下是自定义错误
    /**
     * json解析出错
     */
    public static final int CLIENT_JSON_ERROR = 10004;
    /**
     * 网络连接失败
     */
    public static final int CLIENT_NO_CONNECT = 40004;
    /**
     * 没有返回data内容
     */
    public static final int CLIENT_NO_DATA = 40003;
}
