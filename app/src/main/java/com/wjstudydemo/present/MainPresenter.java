package com.wjstudydemo.present;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.wjstudydemo.bean.StudyNameInfo;
import com.wjstudydemo.util.JSONUtils;
import com.wjstudydemo.view.MainActivity;

import java.util.List;

/**
 * @author wangjian
 * @title MainPresenter
 * @description
 * @modifier
 * @date
 * @since 2016/12/22 10:48
 **/
public class MainPresenter extends BasePresenter<MainActivity> {
    public static final int SET_ADAPTERE_DATA = 1;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onTakeView(MainActivity activity) {
        super.onTakeView(activity);
        String json = "[{\"studyName\":\"动画\",\"studyClassName\":\"\"}]";
        List<StudyNameInfo> list = JSONUtils.fromJsonArray(json, new TypeToken<List<StudyNameInfo>>() {
        });
        activity.setAdapterData(list);
    }

//    public void setAdapterData() {
//
//        restartableFirst(SET_ADAPTERE_DATA, new Func0<Observable<Object>>() {
//            @Override
//            public Observable<Object> call() {
//                return null;
//            }
//        });
//        restartableFirst(SET_ADAPTERE_DATA, () -> {
//            String json = "[{\"studyName\":\"动画\",\"studyClassName\":\"\"}]";
//            List<StudyNameInfo> list = JSONUtils.fromJsonArray(json, new TypeToken<StudyNameInfo>() {
//            });
//            return list;
//        }, (view, list) -> {
//
//        }, (view, error) -> L.ee(error.toString()));
//    }


    public void setData(int methodId) {
        start(methodId);
    }
}
