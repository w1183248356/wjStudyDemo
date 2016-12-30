package com.wjstudydemo.view.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
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
 * @description 联合动画的代码实现
 * @modifier
 * @date
 * @since 2016/12/28 14:45
 **/
public class ValueAnimatorDemo5Activity extends BaseNucleusActivity {
    @Bind(R.id.tv)
    TextView vTv;
    @Bind(R.id.pointview)
    MyPointView1 vMyPointView1;
    @Bind(R.id.iv)
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
        doPlaySequentiallyAnimator();
    }

    @OnClick(R.id.btn_cancel)
    public void onBtnCancleClick(View v) {
//        mValueAnimator.cancel();
    }

    /*
    一、AnimatorSet——playSequentially,playTogether>
    首先，AnimatorSet针对ValueAnimator和ObjectAnimator都是适用的，但一般而言，
    我们不会用到ValueAnimator的组合动画，所以我们这篇仅讲解ObjectAnimator下的组合动画实现。
    在AnimatorSet中直接给为我们提供了两个方法playSequentially和playTogether，
    playSequentially表示所有动画依次播放，playTogether表示所有动画一起开始。
     */
    public void doPlaySequentiallyAnimator(){
        ObjectAnimator tvAnimator = ObjectAnimator.ofInt(vTv, "BackgroundColor",  0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator ivAnimator = ObjectAnimator.ofFloat(vIv, "ScaleX", 1.2f, 1.5f, 1.2f, 2f);
        ObjectAnimator ivAnimator2 = ObjectAnimator.ofFloat(vIv, "ScaleY", 1.2f, 1.5f, 1.2f, 2f);
        ObjectAnimator tvAnimator1 = ObjectAnimator.ofInt(vTv, "translationX", 0, 300, 0);
        ObjectAnimator tvAnimator2 = ObjectAnimator.ofInt(vTv, "translationX", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(tvAnimator, ivAnimator, ivAnimator2, tvAnimator1, tvAnimator2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
}
