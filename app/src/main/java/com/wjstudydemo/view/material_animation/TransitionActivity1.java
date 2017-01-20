package com.wjstudydemo.view.material_animation;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;

import com.wjstudydemo.R;
import com.wjstudydemo.bean.Sample;
import com.wjstudydemo.databinding.ActivityTransition1Binding;

/**
 * @author wangjian
 * @title TransitionActivity1
 * @description
 * @modifier
 * @date
 * @since 2017/1/17 16:26
 **/
public class TransitionActivity1 extends BaseDetailActivity {
    private Sample sample;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupWindowAnimations();
        setupToolbar();
        setupLayout();
    }

    public void setupLayout() {
        findViewById(R.id.sample1_button1).setOnClickListener(v -> {
            Intent intent = new Intent(TransitionActivity1.this, TransitionActivity2.class);
            intent.putExtra(EXTRA_SAMPLE, sample);
            intent.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
            transitionTo(intent);
        });
        findViewById(R.id.sample1_button2).setOnClickListener(v -> {
            Intent intent = new Intent(TransitionActivity1.this, TransitionActivity2.class);
            intent.putExtra(EXTRA_SAMPLE, sample);
            intent.putExtra(EXTRA_TYPE, TYPE_XML);
            transitionTo(intent);
        });
        findViewById(R.id.sample1_button3).setOnClickListener(v -> {
            Intent intent = new Intent(TransitionActivity1.this, TransitionActivity3.class);
            intent.putExtra(EXTRA_SAMPLE, sample);
            intent.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
            transitionTo(intent);
        });
        findViewById(R.id.sample1_button4).setOnClickListener(v -> {
            Intent i = new Intent(TransitionActivity1.this, TransitionActivity3.class);
            i.putExtra(EXTRA_SAMPLE, sample);
            i.putExtra(EXTRA_TYPE, TYPE_XML);
            transitionTo(i);
        });
        findViewById(R.id.sample1_button5).setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Visibility returnTransition = buildReturnTransition();
                getWindow().setReturnTransition(returnTransition);
                finishAfterTransition();
            }
        });
        findViewById(R.id.sample1_button6).setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                /**
                 * If no return transition is defined Android will use reversed enter transition
                 * 如果没有定义返回过渡Android将使用逆转进入过渡
                 * In this case, return transition will be a reversed Slide (defined in buildEnterTransition)
                 * 返回的过渡将是一个逆转幻灯片
                 */
                finishAfterTransition();
            }
        });
    }

    private void bindData() {
        ActivityTransition1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_transition1);
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setTransition1Sample(sample);
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(buildEnterTransition());
        }
    }

    private Visibility buildEnterTransition() {
        Fade enterTransition = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            enterTransition = new Fade();
            enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
//             This view will not be affected by enter transition animation
            enterTransition.excludeTarget(R.id.square_red, true);
        }
        return enterTransition;
    }

    private Visibility buildReturnTransition() {
        Visibility enterTransition = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            enterTransition = new Slide();
            enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        }
        return enterTransition;
    }
}
