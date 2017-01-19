package com.wjstudydemo.view.material_animation;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;

import com.wjstudydemo.R;
import com.wjstudydemo.bean.Sample;
import com.wjstudydemo.databinding.ActivityTransition2Binding;

/**
 * @author wangjian
 * @title TransitionActivity2
 * @description
 * @modifier
 * @date
 * @since 2017/1/18 10:54
 **/
public class TransitionActivity2 extends BaseDetailActivity {
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void bindData() {
        ActivityTransition2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_transition2);
        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        binding.setTransition2Sample(sample);
    }

    private void setupWindowAnimations() {
        Transition transition;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (type == TYPE_PROGRAMMATICALLY) {
                transition = buildEnterTransition();
            } else {
                transition = TransitionInflater.from(this).inflateTransition(R.transition.transitionset_activity2);
            }
            getWindow().setEnterTransition(transition);
        }
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

    private Transition buildEnterTransition() {
            Explode enterTransition = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            enterTransition = new Explode();
            enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        }
        return enterTransition;
    }
}
