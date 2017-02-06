package com.wjstudydemo.view.material_animation;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.wjstudydemo.R;
import com.wjstudydemo.bean.Sample;
import com.wjstudydemo.databinding.ActivityAnimations2Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjian
 * @title AnimationsActivity2
 * @description
 * @modifier
 * @date
 * @since 2017/1/19 15:53
 **/
public class AnimationsActivity2 extends BaseDetailActivity {

    private static final int DELAY = 100;
    private Scene scene0;
    private Scene scene1;
    private Scene scene2;
    private Scene scene3;
    private Scene scene4;
    private final List<View> viewsToAnimate = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations();
            setupLayout();
        }
    }

    private void bindData() {
        ActivityAnimations2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_animations2);
        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setAnimationsSample(sample);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom));
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                getWindow().getEnterTransition().removeListener(this);
                TransitionManager.go(scene0);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupLayout() {
        ViewGroup activityRoot = (ViewGroup) findViewById(R.id.buttons_group);
        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);

//        Android Scene保存了view层级的状态，并保存了层级中所有view的属性值。
// 动画框架能使用动画进行场景的切换，一般只需要制定exit(退出)动画，不需要制定enter（进入）动画，
// 系统会自动帮我们创建进入动画。

//        用Layout创建一个场景
//        用layout创建Scene时，一般这个layout是不变的，只有在layout创建的时候才会加载场景。
// 如果你要改变这个layout的话，需要重新创建Scene。不能用layout的一部分来创建Scene。
// 用layout创建Scene时需要吧Layout当做ViewGroup初始化，然后调用Scene.getSceneForLayout() 。
        scene0 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene0, this);
//        场景动作在下边这些情况下用处比较大:
//        1、需要加动画的view，他们不在同一个view层级中，这样就可以操作每个的view进行入场和出场动画。
//        2、为那些没有自带动画效果的view加效果， 例如ListView中的子view，详情参考Limitations。
//        为了保证动画的性能问题，要把他们放到线程里，然后调用 Scene.setExitAction() 或者 Scene.setEnterAction()。
//        Framework 会在场景动画开始前调用setExitAction()，场景动画结束后调用 setEnterAction()。
        scene0.setEnterAction(this::setViewsToAnimate);
        scene0.setExitAction(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(activityRoot);
                View title = scene0.getSceneRoot().findViewById(R.id.scene0_title);
                title.setScaleX(0);
                title.setScaleY(0);
            }
        });

        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene1, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene2, this);
        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene3, this);
        scene4 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene4, this);

        View button1 = findViewById(R.id.sample3_button1);
        View button2 = findViewById(R.id.sample3_button2);
        View button3 = findViewById(R.id.sample3_button3);
        View button4 = findViewById(R.id.sample3_button4);

        /*
        Transition类型
        AutoTransition：xml中配置<autoTransition/>，默认变化. 淡入淡出,移动 、重计算大小， views, in that order.
        Fade：xml中配置<fade/>android:fadingMode="[fade_in |fade_out |fade_in_out]"/>,淡入淡出
        ChangeBounds：<changeBounds/>移动并改变大小
        一、xml创建一个Transition：
        1、工程中创建 res/transition/ 目录.
        2、创建xml资源文件
        3、加入Transition节点
        res/transition/fade_transition.xml
        <fade xmlns:android="http://schemas.android.com/apk/res/android" />
        然后java代码中载入xml资源
        Transition mFadeTransition =
        TransitionInflater.from(this).
        inflateTransition(R.transition.fade_transition);
        二、代码中创建Transition
        Transition mFadeTransition = new Fade();
        场景和Transition都已经创建好了，那么该怎么触发他们呢。
        TransitionManager.go(mEndingScene, mFadeTransition);
        如果要融合多种变化的话可以用set来存放他们例如AutoTransiton
        <transitionSet xmlns:android="http://schemas.android.com/apk/res/android"
            android:transitionOrdering="sequential">
            <fade android:fadingMode="fade_out" />
            <changeBounds />
            <fade android:fadingMode="fade_in" />
        </transitionSet>
         */
        button1.setOnClickListener(v -> {
            TransitionManager.go(scene1, new ChangeBounds());
        });
        button2.setOnClickListener(v -> {
            TransitionManager.go(scene2, TransitionInflater.from(AnimationsActivity2.this).inflateTransition(R.transition.slide_and_changebounds));
        });
        button3.setOnClickListener(v -> {
            TransitionManager.go(scene3, TransitionInflater.from(AnimationsActivity2.this).inflateTransition(R.transition.slide_and_changebounds_sequential));
        });
        button4.setOnClickListener(v -> {
            TransitionManager.go(scene4, TransitionInflater.from(AnimationsActivity2.this).inflateTransition(R.transition.slide_and_changebounds_sequential_with_interpolators));
        });

        viewsToAnimate.add(button1);
        viewsToAnimate.add(button2);
        viewsToAnimate.add(button3);
        viewsToAnimate.add(button4);
    }

    public void setViewsToAnimate() {
        for (int i = 0; i < viewsToAnimate.size(); i++) {
            View child = viewsToAnimate.get(i);
            child.animate()
                    .setStartDelay(i * DELAY)
                    .scaleX(1)
                    .scaleY(1);

        }
    }
}
