package com.wjstudydemo.present;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.wjstudydemo.bean.StudyNameInfo;
import com.wjstudydemo.util.JSONUtils;
import com.wjstudydemo.view.customview.CustomViewMainActivity;

import java.util.List;

/**
 * @author wangjian
 * @title CustomViewMainPresenter
 * @description
 * @modifier
 * @date
 * @since 2017/7/7 17:11
 **/
public class CustomViewMainPresenter extends BasePresenter<CustomViewMainActivity> {
    public static final int SET_ADAPTERE_DATA = 1;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onTakeView(CustomViewMainActivity activity) {
        super.onTakeView(activity);
        String json = "[{\"studyName\":\"签名\",\"studyClassName\":\"com.wjstudydemo.view.customview.SignActivity\"}," +
                "{\"studyName\":\"DesignSupportLibrary使用\",\"studyClassName\":\"com.wjstudydemo.view.DesignSupportLibraryDemoActivity\"}," +
                "{\"studyName\":\"动画学习\",\"studyClassName\":\"com.wjstudydemo.view.AnimationMainDemoActivity\"}," +
                "{\"studyName\":\"自定义控件学习\",\"studyClassName\":\"com.wjstudydemo.view.UserDefinedWidgetActivity\"}" +
                (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ?
                        ",{\"studyName\":\"MaterialAnimationsActivity源码\",\"studyClassName\":\"com.wjstudydemo.view.MaterialAnimationsActivity\"}]" : "]");
        List<StudyNameInfo> list = JSONUtils.fromJsonArray(json, new TypeToken<List<StudyNameInfo>>() {
        });
        activity.setAdapterData(list);
    }
}
