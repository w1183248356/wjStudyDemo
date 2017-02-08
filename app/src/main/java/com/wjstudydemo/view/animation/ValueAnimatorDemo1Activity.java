package com.wjstudydemo.view.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wjstudydemo.R.id.tv;

/**
 * @author wangjian
 * @title ValueAnimatorDemo1Activity
 * @description ValueAnimator基本使用界面
 * @modifier
 * @date
 * @since 2016/12/28 14:45
 **/
public class ValueAnimatorDemo1Activity extends BaseNucleusActivity {
    @BindView(tv)
    TextView vTv;

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
    }

    @OnClick(R.id.btn_cancel)
    public void onBtnCancleClick(View v) {
        mValueAnimator.cancel();
    }

    public ValueAnimator repeatAnim() {
        /*
        ofInt与ofFloat两个方法  区别就是传入的值不一样
         */
//        ValueAnimator animator = ValueAnimator.ofInt(0, 400, 30, 300);
        ValueAnimator animator = ValueAnimator.ofInt(0, 400);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                vTv.layout(vTv.getLeft(), curValue, vTv.getRight(), curValue + vTv.getHeight());
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("qijian","animation start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setRepeatCount(3);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(3000);
        animator.start();
        //可以自已定义Interpolator 只要实现TimerInterpolator改变里面的算法
        animator.setInterpolator(new BounceInterpolator());
        //或者自定义Evaluator
//        animator.setEvaluator(new IntEvaluator());

        ValueAnimator argbAnima = ValueAnimator.ofInt(0xffffff00,0xff0000ff);
        argbAnima.addUpdateListener(animation -> {
            int curValue = (int)animation.getAnimatedValue();
            vTv.setBackgroundColor(curValue);
        });
        argbAnima.setDuration(9000);
        argbAnima.start();
        return animator;
    }
}
