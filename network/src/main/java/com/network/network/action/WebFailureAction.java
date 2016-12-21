package com.network.network.action;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;

/**
 * @author wangjian
 * @title WebFailureAction
 * @description
 * @modifier
 * @date
 * @since 2016/12/7 9:53
 **/
public class WebFailureAction implements Action1<Throwable> {

    Context mContext;

    @Override
    public void call(Throwable throwable) {
        String errorMsg;
        if (throwable instanceof IOException) {
            errorMsg = "请检查网络状态";
        } else if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            errorMsg = httpException.response().message();
        } else {
            errorMsg = throwable.getMessage();
        }
        Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
