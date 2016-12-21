package com.network.network.transform;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.network.BuildConfig;
import com.network.network.dialog.UpdateModel;
import com.network.network.model.net.NetCodeStatus;
import com.network.network.util.L;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jin on 2016/3/9.
 */
public class RxResponTransform implements Observable.Transformer<String, JSONObject> {
    private ProgressDialog mProgressDialog;

    public RxResponTransform(ProgressDialog myProgressDialog) {
        this.mProgressDialog = myProgressDialog;
    }

    @Override
    public Observable<JSONObject> call(Observable<String> oldObservable) {
        return oldObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(result -> {
                    try {
                        return Observable.just(new JSONObject(result));
                    } catch (JSONException e) {
                        return Observable.error(new JSONException(e.getMessage() + "====>" + result));
                    }
                })
                .map(responsObject -> {
                    try {
                        return responsObject.getJSONObject("result");
                    } catch (JSONException e) {
                        L.ee(e.getMessage());
                        return null;
                    }
                })
                .filter(responsObject -> responsObject != null)
                .doOnNext(responsObject -> {
                    //日志输出
                    if (BuildConfig.DEBUG) {
                        L.json(responsObject.toString());
                        L.ii("responsObject====>" + responsObject.toString());
                    }

                    //隐藏进度条
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                })
                .doOnNext(responsObject -> {//判断是否需要强制更新
                    try {
                        if (responsObject.getInt("code") == NetCodeStatus.FORCE_UPDATE) {
                            UpdateModel updateModel = new UpdateModel(true, null);
                            EventBus.getDefault().post(updateModel);
                        }
                    } catch (JSONException e) {
                        Observable.error(new JSONException(e.getMessage()));
                    }
                })
                .doOnSubscribe(() -> {
                    if (mProgressDialog != null)
                        mProgressDialog.show();
                })
                .doOnError(e -> {
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (mProgressDialog != null && e instanceof UnknownHostException)
                        Toast.makeText(mProgressDialog.getContext(), "请检查网络是否连接", Toast.LENGTH_SHORT).show();


                    if (BuildConfig.DEBUG)
                        L.e(e.getMessage());
                })
                .doOnCompleted(() -> {
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                });
    }
}
