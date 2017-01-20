package com.wjstudydemo.view.material_animation;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;

import com.wjstudydemo.R;
import com.wjstudydemo.bean.Sample;
import com.wjstudydemo.databinding.ActivitySharedelementBinding;
import com.wjstudydemo.fragment.material_animation.SharedElementFragment1;

/**
 * @author wangjian
 * @title SharedElementActivity
 * @description 如果想做成这样的效果必须 两个不同的页面含有相同的transitionName
 *                  例如  页面A
 *                  <ImageView
 *                  android:id="@+id/small_blue_icon"
 *                  style="@style/MaterialAnimations.Icon.Small"
 *                  android:src="@drawable/circle"
 *                  android:transitionName="@string/blue_name" />
 *                  页面   B
 *                  <ImageView
 *                  android:id="@+id/big_blue_icon"
 *                  style="@style/MaterialAnimations.Icon.Big"
 *                  android:src="@drawable/circle"
 *                  android:transitionName="@string/blue_name" />
 *                  然后在使用
 *                  ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, sharedView, transitionName);
 *                  跳转到页面B
 * @modifier
 * @date
 * @since 2017/1/19 10:36
 **/
public class SharedElementActivity extends BaseDetailActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        bindData(sample);
        setupWindowAnimations();
        setupLayout(sample);
        setupToolbar();
    }

    private void bindData(Sample sample) {
        ActivitySharedelementBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sharedelement);
        binding.setSharedSample(sample);
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
        }
    }

    private void setupLayout(Sample sample) {
        // Transition for fragment1
        Slide slideTransition = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            slideTransition = new Slide(Gravity.LEFT);
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        }
        // Create fragment and define some of it transitions
        SharedElementFragment1 sharedElementFragment1 = SharedElementFragment1.newInstance(sample);
        sharedElementFragment1.setReenterTransition(slideTransition);
        sharedElementFragment1.setExitTransition(slideTransition);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            sharedElementFragment1.setSharedElementEnterTransition(new ChangeBounds());
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment1)
                .commit();
    }
}
