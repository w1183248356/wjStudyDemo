package com.network.network.exception;

/**
 * @author wangjian
 * @title ResponseCodeError
 * @description
 * @modifier
 * @date
 * @since 2016/12/7 9:59
 **/
public class ResponseCodeError extends RuntimeException {
    public ResponseCodeError(String detailMessage) {
        super(detailMessage);
    }
}
