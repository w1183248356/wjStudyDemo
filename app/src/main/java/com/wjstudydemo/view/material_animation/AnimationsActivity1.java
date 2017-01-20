package com.wjstudydemo.view.material_animation;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wjstudydemo.R;
import com.wjstudydemo.bean.Sample;
import com.wjstudydemo.databinding.ActivityAnimations1Binding;

/**
 * @author wangjian
 * @title AnimationsActivity1
 * @description
 * @modifier
 * @date
 * @since 2017/1/19 15:20
 **/
public class AnimationsActivity1 extends BaseDetailActivity {
    private ImageView square;
    private ViewGroup viewRoot;

    private Sample sample;
    private boolean sizeChanged;
    private int savedWidth;

    private boolean positionChanged;
    private int savePosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupWindowAnimations();
        setupLayout();
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setReenterTransition(new Fade());
        }
    }

    private void bindData(){
        ActivityAnimations1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_animations1);
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setAnimationsSample(sample);
    }

    private void setupLayout(){
        square = (ImageView) findViewById(R.id.square_green);
        viewRoot = (ViewGroup) findViewById(R.id.sample3_root);
        findViewById(R.id.sample3_button1).setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                changeLayout();
            }
        });
        findViewById(R.id.sample3_button2).setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                changePosition();
            }
        });
        findViewById(R.id.sample3_button3).setOnClickListener(v -> {
            Intent i = new Intent(AnimationsActivity1.this, AnimationsActivity2.class);
            i.putExtra(EXTRA_SAMPLE, sample);
            transitionTo(i);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void changeLayout(){
        TransitionManager.beginDelayedTransition(viewRoot);

        ViewGroup.LayoutParams params = square.getLayoutParams();
        if(sizeChanged){
            params.width = savedWidth;
        } else {
            savedWidth = params.width;
            params.width = 200;
        }
        sizeChanged = !sizeChanged;
        square.setLayoutParams(params);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void changePosition(){
        TransitionManager.beginDelayedTransition(viewRoot);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) square.getLayoutParams();
        if (positionChanged){
            params.gravity = Gravity.CENTER;
        } else {
            params.gravity = Gravity.LEFT;
        }
        positionChanged = !positionChanged;
        square.setLayoutParams(params);
    }
}
