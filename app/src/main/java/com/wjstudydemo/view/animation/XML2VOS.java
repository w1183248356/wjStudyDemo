package com.wjstudydemo.view.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
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
 * @title XML2VOS
 * @description 联合动画的XML实现与使用示例
 *                  利用xml来实现ValueAnimator、ObjectAnimator和AnimatorSet;
 * @modifier
 * @date
 * @since 2017/1/5 13:41
 **/
public class XML2VOS extends BaseNucleusActivity {
    @Bind(R.id.tv)
    TextView vTv;
    @Bind(R.id.pointview)
    MyPointView1 vMyPointView1;
    @Bind(R.id.iv)
    ImageView vIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_value_animator1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onBtnClick(View v) {
//        animatorDemo();
//        objectAnimatorDemo();
        setDemo();
    }

    @OnClick(R.id.btn_cancel)
    public void onBtnCancleClick(View v) {
//        mValueAnimator.cancel();
    }

    /*
    <animator
    android:duration="int"
    android:valueFrom="float | int | color"
    android:valueTo="float | int | color"
    android:startOffset="int"
    android:repeatCount="int"
    android:repeatMode=["repeat" | "reverse"]
    android:valueType=["intType" | "floatType"]
    android:interpolator=["@android:interpolator/XXX"]/>

        animator
        android:duration:每次动画播放的时长
        android:valueFrom:初始动化值；取值范围为float,int和color，如果取值为float对应的值样式应该为89.0，
                            取值为Int时，对应的值样式为：89;当取值为clolor时，对应的值样式为 #333333;
        android:valueTo：动画结束值；取值范围同样是float,int和color这三种类型的值；
        android:startOffset：动画激活延时；对应代码中的startDelay(long delay)函数；
        android:repeatCount：动画重复次数
        android:repeatMode：动画重复模式，取值为repeat和reverse；repeat表示正序重播，reverse表示倒序重播
        android:valueType：表示参数值类型，取值为intType和floatType；与android:valueFrom、android:valueTo相对应。
                            如果这里的取值为intType，那么android:valueFrom、android:valueTo的值也就要对应的是int类型的数值。
                            如果这里的数值是floatType，那么android:valueFrom、android:valueTo的值也要对应的设置为float类型的值。
                            非常注意的是，如果android:valueFrom、android:valueTo的值设置为color类型的值，那么不需要设置这个参数；
        android:interpolator:设置加速器；有关系统加速器所对应的xml值对照表如下：
     */
    public void animatorDemo(){
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.animator1);
        animator.addUpdateListener(animation -> {
            int offset = (int) animation.getAnimatedValue();
            vTv.layout(offset, offset, vTv.getWidth() + offset, vTv.getHeight() + offset);
        });
        animator.start();
    }

    /*
    <objectAnimator
    android:propertyName="string"
    android:duration="int"
    android:valueFrom="float | int | color"
    android:valueTo="float | int | color"
    android:startOffset="int"
    android:repeatCount="int"
    android:repeatMode=["repeat" | "reverse"]
    android:valueType=["intType" | "floatType"]
    android:interpolator=["@android:interpolator/XXX"]/>

    - android:propertyName：对应属性名，即ObjectAnimator所需要操作的属性名。
    其它字段的意义与animator的意义与取值是一样的，下面再重新列举一下。
    - android:duration:每次动画播放的时长
    - android:valueFrom:初始动化值；取值范围为float,int和color；
    - android:valueTo：动画结束值；取值范围同样是float,int和color这三种类型的值；
    - android:startOffset：动画激活延时；对应代码中的startDelay(long delay)函数；
    - android:repeatCount：动画重复次数
    - android:repeatMode：动画重复模式，取值为repeat和reverse；repeat表示正序重播，reverse表示倒序重播
    - android:valueType：表示参数值类型，取值为intType和floatType；与android:valueFrom、android:valueTo相对应。如果这里的取值为intType，那么android:valueFrom、android:valueTo的值也就要对应的是int类型的数值。如果这里的数值是floatType，那么android:valueFrom、android:valueTo的值也要对应的设置为float类型的值。非常注意的是，如果android:valueFrom、android:valueTo的值设置为color类型的值，那么不需要设置这个参数；
    - android:interpolator:设置加速器；
     */
    public void objectAnimatorDemo(){
        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.object_animator1);
        ObjectAnimator colorAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.object_animator2);
        animator.setTarget(vTv);
        colorAnimator.setTarget(vTv);
        animator.start();
        colorAnimator.start();
    }

    /*
    set
    <set
    android:ordering=["together" | "sequentially"]>
    android:ordering：表示动画开始顺序。together表示同时开始动画，sequentially表示逐个开始动画；
    加载方式为：
     */
    public void setDemo(){
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator1);
        animatorSet.setTarget(vTv);
        animatorSet.start();
    }

}
