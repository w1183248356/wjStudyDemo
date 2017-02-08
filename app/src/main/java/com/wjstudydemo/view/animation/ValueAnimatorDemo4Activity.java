package com.wjstudydemo.view.animation;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;
import com.wjstudydemo.util.view.MyPointView1;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wangjian
 * @title ValueAnimatorDemo1Activity
 * @description PropertyValuesHolder与Keyframe 基本使用界面
 * @modifier
 * @date
 * @since 2016/12/28 14:45
 **/
public class ValueAnimatorDemo4Activity extends BaseNucleusActivity {
    @BindView(R.id.tv)
    TextView vTv;
    @BindView(R.id.pointview)
    MyPointView1 vMyPointView1;
    @BindView(R.id.iv)
    ImageView vIv;

    /*
    View Animation   包括Tween Animation（补间动画）和Frame Animation(逐帧动画)；
    Property Animator包括ValueAnimator和ObjectAnimation；
    直观上的区别
    1、引入时间不同：View Animation是API Level 1就引入的。Property Animation是API Level 11引入的，即Android 3.0才开始有Property Animation相关的API。
    2、所在包名不同：View Animation在包android.view.animation中。而Property Animation API在包 android.animation中。
    3、动画类的命名不同：View Animation中动画类取名都叫XXXXAnimation,而在Property Animator中动画类的取名则叫XXXXAnimator

    视图动画改变的是view不能改变属性而属性动画改变的是属性
    View Animation仅能对指定的控件做动画，而Property Animator是通过改变控件某一属性值来做动画的
    补间动画虽能对控件做动画，但并没有改变控件内部的属性值。而Property Animator则是恰恰相反，Property Animator是通过改变控件内部的属性值来达到动画效果的
    简单的理解就是视图动画改变的是view的显示地方对view没有任何的改变
    属性动画改变的是view的属性，通过改变属性来完成动画。
     */
    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_value_animator1);
        ButterKnife.bind(this);
        SpannableStringBuilder ssb = new SpannableStringBuilder();

    }

    @OnClick(R.id.btn)
    public void onBtnClick(View v) {
        mValueAnimator = repeatAnim();
        imgViewAnimation();
//        vMyPointView1.doPointAnim();
    }

    @OnClick(R.id.btn_cancel)
    public void onBtnCancleClick(View v) {
        mValueAnimator.cancel();
    }

    public void imgViewAnimation() {
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.2f, 60);
        Keyframe frame2 = Keyframe.ofFloat(0.4f, -60);
        Keyframe frame3 = Keyframe.ofFloat(0.6f, 60);
        Keyframe frame4 = Keyframe.ofFloat(0.8f, -60);
        Keyframe frame5 = Keyframe.ofFloat(1f, 0);
        frame5.setInterpolator(new BounceInterpolator());

        Keyframe scaleFrame0 = Keyframe.ofFloat(0, 1);
        Keyframe scaleFrame1 = Keyframe.ofFloat(0.1f, 1.5f);
        Keyframe scaleFrame2 = Keyframe.ofFloat(0.9f, 1.5f);
        Keyframe scaleFrame3 = Keyframe.ofFloat(1, 1);

        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2, frame3, frame4, frame5);
        PropertyValuesHolder scaleXFrameHolder = PropertyValuesHolder.ofKeyframe("ScaleX", scaleFrame0, scaleFrame1, scaleFrame2, scaleFrame3);
        PropertyValuesHolder scaleYFrameHolder = PropertyValuesHolder.ofKeyframe("ScaleY", scaleFrame0, scaleFrame1, scaleFrame2, scaleFrame3);
        Animator animator = ObjectAnimator.ofPropertyValuesHolder(vIv, frameHolder, scaleXFrameHolder, scaleYFrameHolder);
        animator.setDuration(10000);
        animator.start();
    }

    public ValueAnimator repeatAnim() {
        /*
        target：指需要执行动画的控件
        values：是一个可变长参数，可以传进去多个PropertyValuesHolder实例，
                由于每个PropertyValuesHolder实例都会针对一个属性做动画，
                所以如果传进去多个PropertyValuesHolder实例，将会对控件的多个属性同时做动画操作。
         */

        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("Rotation", 60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f);
        PropertyValuesHolder backgroundColorHolder = PropertyValuesHolder.ofInt("BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(vTv, rotationHolder, backgroundColorHolder);
        animator.setDuration(3000);
        animator.start();
        return animator;
    }
}
