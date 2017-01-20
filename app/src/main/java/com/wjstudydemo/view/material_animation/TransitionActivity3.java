package com.wjstudydemo.view.material_animation;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;

import com.wjstudydemo.R;
import com.wjstudydemo.bean.Sample;
import com.wjstudydemo.databinding.ActivityTransition3Binding;

/**
 * @author wangjian
 * @title TransitionActivity3
 * @description
 * @modifier
 * @date
 * @since 2017/1/19 14:04
 **/
public class TransitionActivity3 extends BaseDetailActivity {
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupWindowAnimations();
        setupLayout();
    }

    private void bindData() {
        ActivityTransition3Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_transition3);
        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        binding.setTransition3Sample(sample);
    }

    private void setupLayout() {
        findViewById(R.id.exit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
            }
        });
    }

    public void setupWindowAnimations() {
        Transition transition = null;
        if (type == TYPE_PROGRAMMATICALLY) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                transition = buildEnterTransition();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(transition);
        }
    }

    private Visibility buildEnterTransition() {
        Slide enterTransition = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            enterTransition = new Slide();
            enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
            enterTransition.setSlideEdge(Gravity.RIGHT);
        }
        return enterTransition;
    }
}
