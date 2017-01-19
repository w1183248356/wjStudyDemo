package com.wjstudydemo.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.wjstudydemo.R;
import com.wjstudydemo.adapter.SamplesRecyclerAdapter;
import com.wjstudydemo.bean.Sample;
import com.wjstudydemo.util.TransitionHelper;
import com.wjstudydemo.view.material_animation.TransitionActivity1;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangjian
 * @title MaterialAnimationsActivity
 * @description Material-animations 源码
 * @modifier
 * @date
 * @since 2017/1/9 13:48
 * 共享元素跳转：
 * 为了使有一个共享元素的两个activities间使用过渡动画：
 * 1.在你的主题中启用窗口内容过渡
 * 2.在你的主题样式中指定共享元素的过渡
 * 3.定义你的过渡动画为XML资源
 * 4.使用Android:transitionName属性给两个布局中的共享元素指定一个相同的名字（名字一定不要写错）
 * 5.使用ActivityOptions.makeSceneTransitionAnimation() 方法
 * 首先在主题里面添加下面属性Style
 * <item name="android:windowContentTransitions">true</item>
 * <item name="android:windowAllowEnterTransitionOverlap">false</item>
 * <item name="android:windowAllowReturnTransitionOverlap">false</item>
 *
 **/
public class MaterialAnimationsActivity extends AppCompatActivity {
    private List<Sample> samples;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_material_animations);
        setupWindowAnimations();
        setupSamples();
        setupToolbar();
        setupLayout();
    }

    public void setupWindowAnimations() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.slide_main);
            Slide slide = new Slide();
            slide.setDuration(getResources().getInteger(R.integer.anim_duration_long));
            slide.setSlideEdge(Gravity.LEFT);
            getWindow().setReenterTransition(slide);
            getWindow().setExitTransition(slide);
            // TODO: 2017/1/17 这里的动画只能在api= 21（android5.0）的时候使用
            // TODO: 2017/1/17 如果需要有动画效果。 startActivity（intent，ActivityOptions.makeSceneTransitionAnimation(this).toBundle())）
        }
    }

    @SuppressWarnings("ResourceType")
    private void setupSamples() {
        samples = Arrays.asList(
                new Sample(ContextCompat.getColor(this, R.color.sample_red), "Transitions"),
                new Sample(ContextCompat.getColor(this, R.color.sample_blue), "Shared Elements"),
                new Sample(ContextCompat.getColor(this, R.color.sample_green), "View animations"),
                new Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circular Reveal Animation")
        );
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SamplesRecyclerAdapter samplesRecyclerAdapter = new SamplesRecyclerAdapter(this, samples);
        recyclerView.setAdapter(samplesRecyclerAdapter);
        samplesRecyclerAdapter.setOnItemClickListener((v, viewHolder) -> {
            switch (viewHolder.getAdapterPosition()) {
                case 0:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        transitionToActivity(TransitionActivity1.class, samples.get(0));
                    }
                    break;
            }
        });
    }

    public void transitionToActivity(Class target, Sample sample) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        startActivity(target, pairs, sample);
    }

    public void startActivity(Class target, Pair<View, String>[] pairs, Sample sample) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Intent intent = new Intent(this, target);
            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
            intent.putExtra("sample", sample);
            this.startActivity(intent, transitionActivityOptions.toBundle());
        }
    }
}
