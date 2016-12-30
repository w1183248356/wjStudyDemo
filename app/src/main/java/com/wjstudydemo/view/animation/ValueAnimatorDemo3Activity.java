package com.wjstudydemo.view.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;
import com.wjstudydemo.util.view.MyPointView1;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wangjian
 * @title ValueAnimatorDemo1Activity
 * @description ObjectAnimator 基本使用界面
 * @modifier
 * @date
 * @since 2016/12/28 14:45
 **/
public class ValueAnimatorDemo3Activity extends BaseNucleusActivity {
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
        SpannableStringBuilder ssb = new SpannableStringBuilder();

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
        /*
        第一个参数用于指定这个动画要操作的是哪个控件
        第二个参数用于指定这个动画要操作这个控件的哪个属性
        第三个参数是可变长参数，这个就跟ValueAnimator中的可变长参数的意义一样了，就是指这个属性值是从哪变到哪
         */
        ObjectAnimator animator = ObjectAnimator.ofFloat(vTv, "alpha", 1, 0, 1);
        animator.setDuration(2000);
        animator.start();
        return animator;
    }
}
