package com.wjstudydemo.view.material_animation;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
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
            // This view will not be affected by enter transition animation
//            enterTransition.excludeTarget(R.id.square_red, true);
        }
        return enterTransition;
    }
}
