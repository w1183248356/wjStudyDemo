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
        String json = "[{\"studyName\":\"RecyclerView使用\",\"studyClassName\":\"com.wjstudydemo.view.RecyclerViewDemoActivity\"}," +
                "{\"studyName\":\"DesignSupportLibrary使用\",\"studyClassName\":\"com.wjstudydemo.view.DesignSupportLibraryDemoActivity\"}," +
                "{\"studyName\":\"动画学习\",\"studyClassName\":\"com.wjstudydemo.view.AnimationMainDemoActivity\"}," +
                "{\"studyName\":\"自定义控件学习\",\"studyClassName\":\"com.wjstudydemo.view.UserDefinedWidgetActivity\"}" +
                (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ?
                        ",{\"studyName\":\"MaterialAnimationsActivity源码\",\"studyClassName\":\"com.wjstudydemo.view.MaterialAnimationsActivity\"}"+
                               ",{\"studyName\":\"LookLook源码\",\"studyClassName\":\"com.wjstudydemo.view.looklook.LookLookMainActivity\"}]" : "]");
        List<StudyNameInfo> list = JSONUtils.fromJsonArray(json, new TypeToken<List<StudyNameInfo>>() {
        });
        activity.setAdapterData(list);
    }

}
