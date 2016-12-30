package com.wjstudydemo.view.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;
import com.wjstudydemo.animation.evaluator.CharEvaluator;
import com.wjstudydemo.util.view.MyPointView1;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wangjian
 * @title ValueAnimatorDemo1Activity
 * @description ValueAnimator基本使用界面
 * @modifier
 * @date
 * @since 2016/12/28 14:45
 **/
public class ValueAnimatorDemo2Activity extends BaseNucleusActivity {
    @Bind(R.id.tv)
    TextView vTv;
    @Bind(R.id.pointview)
    MyPointView1 vMyPointView1;

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
    }

    @OnClick(R.id.btn)
    public void onBtnClick(View v) {
        mValueAnimator = repeatAnim();
        vMyPointView1.doPointAnim();
    }

    @OnClick(R.id.btn_cancel)
    public void onBtnCancleClick(View v) {
        mValueAnimator.cancel();
    }

    public ValueAnimator repeatAnim() {
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('z'));
        animator.addUpdateListener(animation -> {
            char c = (char) animation.getAnimatedValue();
            vTv.setText(String.valueOf(c));
        });
        animator.setDuration(10000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
        return animator;
    }
}
