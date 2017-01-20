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

        scene0 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_animations_scene0, this);
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
