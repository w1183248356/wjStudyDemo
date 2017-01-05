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
                "{\"studyName\":\"alpha、scale、translate、rotate、set的代码生成方法\",\"studyClassName\":\"com.wjstudydemo.view.animation.ASTRSAnimationCodeActivity\"}," +
                "{\"studyName\":\"ValueAnimator基本使用界面\",\"studyClassName\":\"com.wjstudydemo.view.animation.ValueAnimatorDemo1Activity\"}," +
                "{\"studyName\":\"ValueAnimator自定义\",\"studyClassName\":\"com.wjstudydemo.view.animation.ValueAnimatorDemo2Activity\"}," +
                "{\"studyName\":\"ValueAnimator-ObjectAnimator使用\",\"studyClassName\":\"com.wjstudydemo.view.animation.ValueAnimatorDemo3Activity\"}," +
                "{\"studyName\":\"PropertyValuesHolder与Keyframe 基本使用界面\",\"studyClassName\":\"com.wjstudydemo.view.animation.ValueAnimatorDemo4Activity\"}," +
                "{\"studyName\":\"联合动画简单实现\",\"studyClassName\":\"com.wjstudydemo.view.animation.ValueAnimatorDemo5Activity\"}," +
                "{\"studyName\":\"利用xml来实现ValueAnimator、ObjectAnimator和AnimatorSet\",\"studyClassName\":\"com.wjstudydemo.view.animation.XML2VOS\"}," +
                "{\"studyName\":\"简单的时间点击按钮弹出多个按钮\",\"studyClassName\":\"com.wjstudydemo.view.animation.AnimatorDemoActivity\"}]";
        List<StudyNameInfo> list = JSONUtils.fromJsonArray(json, new TypeToken<List<StudyNameInfo>>() {
        });
        activity.setAdapterData(list);
    }
}
