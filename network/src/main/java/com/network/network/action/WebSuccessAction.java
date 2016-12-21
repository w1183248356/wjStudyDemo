package com.network.network.action;


import com.network.network.exception.ResponseCodeError;
import com.network.network.model.net.CommonDataResponse;
import com.network.network.model.net.NetCodeStatus;

import rx.functions.Action1;

/**
 * @author wangjian
 * @title WebSuccessAction
 * @description
 * @modifier
 * @date
 * @since 2016/12/7 9:59
 **/
public abstract class WebSuccessAction<T extends CommonDataResponse> implements Action1<T> {
    @Override
    public void call(T response) {
        int rc = response.getCode();
        if (rc != NetCodeStatus.OK)
            throw new ResponseCodeError(response.getMsg());
        onSuccess(response);
    }

    abstract void onSuccess(T extendedResponse);
}
