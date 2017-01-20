package com.wjstudydemo.view.material_animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wjstudydemo.R;
import com.wjstudydemo.bean.Sample;
import com.wjstudydemo.databinding.ActivityRevealBinding;

/**
 * @author wangjian
 * @title RevealActivity
 * @description
 * @modifier
 * @date
 * @since 2017/1/19 16:57
 **/
public class RevealActivity extends BaseDetailActivity implements View.OnTouchListener {
    private static final int DELAY = 100;
    private RelativeLayout bgViewGroup;
    private Toolbar toolbar;
    private Interpolator interpolator;
    private TextView body;
    private View btnRed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations();
        }
        setupLayout();
    }

    private void bindData() {
        ActivityRevealBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_reveal);
        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setReveal1Sample(sample);
    }

    private void setupWindowAnimations() {
        interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupEnterAnimations();
            setupExitAnimations();
        }
    }

    private void setupLayout() {
        bgViewGroup = (RelativeLayout) findViewById(R.id.reveal_root);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        body = ((TextView) findViewById(R.id.sample_body));
        View btnGreen = findViewById(R.id.square_green);
        btnGreen.setOnClickListener(v -> {
            revealGreen();
        });
        btnRed = findViewById(R.id.square_red);
        btnRed.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                revealRed();
            }
        });

        findViewById(R.id.square_blue).setOnClickListener(v -> {
            revealBlue();
        });
        findViewById(R.id.square_yellow).setOnTouchListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimations() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                // Removing listener here is very important because shared element transition is executed again backwards on exit.
                // If we don't remove the listener this code will be triggered again.
                transition.removeListener(this);
                hideTarget();
                animateRevealShow(toolbar);
                animateButtonsIn();
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
    private void setupExitAnimations() {
        Fade returnTransition = new Fade();
        getWindow().setReturnTransition(returnTransition);
        returnTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        returnTransition.setStartDelay(getResources().getInteger(R.integer.anim_duration_medium));
        returnTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                transition.removeListener(this);
                animateButtonsOut();
                animateRevealHide(bgViewGroup);
            }

            @Override
            public void onTransitionEnd(Transition transition) {

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

    private void hideTarget() {
        findViewById(R.id.shared_target).setVisibility(View.GONE);
    }

    private void animateButtonsIn() {
        for (int i = 0; i < bgViewGroup.getChildCount(); i++) {
            View child = bgViewGroup.getChildAt(i);
            child.animate()
                    .setStartDelay(100 + i * DELAY)
                    .setInterpolator(interpolator)
                    .alpha(1)
                    .scaleX(1)
                    .scaleY(1);
        }
    }

    private void animateButtonsOut() {
        for (int i = 0; i < bgViewGroup.getChildCount(); i++) {
            View child = bgViewGroup.getChildAt(i);
            child.animate()
                    .setStartDelay(i)
                    .setInterpolator(interpolator)
                    .alpha(0)
                    .scaleX(0f)
                    .scaleY(0f);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow(View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, 0, finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        anim.setInterpolator(new AccelerateInterpolator());
        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealHide(final View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int initialRadius = viewRoot.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewRoot.setVisibility(View.INVISIBLE);
            }
        });
        anim.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        anim.start();
    }

    private void revealGreen() {
        animateRevealColor(bgViewGroup, R.color.sample_green);
        body.setText(R.string.reveal_body2);
        body.setTextColor(ContextCompat.getColor(this, R.color.theme_green_background));
    }

    private void animateRevealColor(ViewGroup viewRoot, @ColorRes int color) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        animateRevealColorFromCoordinates(viewRoot, color, cx, cy);
    }

    private Animator animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        }
        viewRoot.setBackgroundColor(ContextCompat.getColor(this, color));
        anim.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealRed() {
        ViewGroup.LayoutParams originalParams = btnRed.getLayoutParams();
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                animateRevealColor(bgViewGroup, R.color.sample_red);
                body.setText(R.string.reveal_body3);
                body.setTextColor(ContextCompat.getColor(RevealActivity.this, R.color.theme_red_background));
                btnRed.setLayoutParams(originalParams);
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
        TransitionManager.beginDelayedTransition(bgViewGroup, transition);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        btnRed.setLayoutParams(layoutParams);
    }

    private void revealYellow(float x, float y) {
        animateRevealColorFromCoordinates(bgViewGroup, R.color.sample_yellow, (int) x, (int) y);
        body.setText(R.string.reveal_body1);
        body.setTextColor(ContextCompat.getColor(this, R.color.theme_yellow_background));
    }

    private void revealBlue() {
        animateButtonsOut();
        Animator anim = animateRevealColorFromCoordinates(bgViewGroup, R.color.sample_blue, bgViewGroup.getWidth() / 2, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animateButtonsIn();
            }
        });
        body.setText(R.string.reveal_body4);
        body.setTextColor(ContextCompat.getColor(this, R.color.theme_blue_background));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v.getId() == R.id.square_yellow) {
                revealYellow(event.getRawX(), event.getRawY());
            }
        }
        return false;
    }
}
