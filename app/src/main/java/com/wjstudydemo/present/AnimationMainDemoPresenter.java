package com.wjstudydemo.present;

import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.wjstudydemo.bean.StudyNameInfo;
import com.wjstudydemo.util.JSONUtils;
import com.wjstudydemo.view.AnimationMainDemoActivity;

import java.util.List;

/**
 * @author wangjian
 * @title AnimationDemoPresenter
 * @description
 * @modifier
 * @date
 * @since 2016/12/27 11:24
 **/
public class AnimationMainDemoPresenter extends BasePresenter<AnimationMainDemoActivity> {
    public static final int SET_ADAPTERE_DATA = 1;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onTakeView(AnimationMainDemoActivity activity) {
        super.onTakeView(activity);
        String json = "[{\"studyName\":\"alpha、scale、translate、rotate、set的xml属性及用法\",\"studyClassName\":\"com.wjstudydemo.view.animation.ASTRSAnimationXMLActivity\"}," +
                "{\"studyName\":\"DesignSupportLibrary使用\",\"studyClassName\":\"com.wjstudydemo.view.DesignSupportLibraryDemoActivity\"}," +
                "{\"studyName\":\"动画学习\",\"studyClassName\":\"com.wjstudydemo.view.DesignSupportLibraryDemoActivity\"}]";
        List<StudyNameInfo> list = JSONUtils.fromJsonArray(json, new TypeToken<List<StudyNameInfo>>() {
        });
        activity.setAdapterData(list);
    }
}
