package com.network.network.transform;

import com.google.gson.Gson;
import com.network.network.model.net.CommonResult;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;

/**
 * Created by Jin on 2016/3/9.
 */
public class RxCommonResultTransform implements Observable.Transformer<JSONObject, CommonResult> {

    @Override
    public Observable<CommonResult> call(Observable<JSONObject> oldObservable) {
        return oldObservable
                .map(result -> {
                    if (result != null) {
                        CommonResult commonResult = new Gson().fromJson(result.toString(), CommonResult.class);
                        try {
                            commonResult.setData(result.getJSONObject("data"));
                        } catch (JSONException e) {
                            Observable.error(e);
                        }
                        return commonResult;
                    }
                    return null;
                });

    }
}
