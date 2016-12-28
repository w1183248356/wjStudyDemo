package com.wjstudydemo.view.animation;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wangjian
 * @title ASTRSAnimationCodeActivity
 * @description 代码生成alpha、scale、translate、rotate、set及插值器动画
 * @modifier
 * @date
 * @since 2016/12/28 9:09
 **/
public class ASTRSAnimationCodeActivity extends BaseNucleusActivity {
    @Bind(R.id.toolbar)
    Toolbar vToolbar;
    @Bind(R.id.tv)
    TextView vTv;

    Animation animation;

    /*
    各个标签对应的类
    scale —— ScaleAnimation
    alpha —— AlphaAnimation
    rotate —— RotateAnimation
    translate —— TranslateAnimation
    set —— AnimationSet
    标签对应的函数
    android:duration                  setDuration(long)	 动画持续时间，以毫秒为单位
    android:fillAfter                    setFillAfter(boolean)	如果设置为true，控件动画结束时，将保持动画最后时的状态
    android:fillBefore                 setFillBefore(boolean)	如果设置为true,控件动画结束时，还原到开始动画前的状态
    android:fillEnabled              setFillEnabled(boolean)	与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
    android:repeatCount           setRepeatCount(int)	重复次数
    android:repeatMode            setRepeatMode(int)	重复类型，有reverse和restart两个值，取值为RESTART或 REVERSE，
                                    必须与repeatCount一起使用才能看到效果。因为这里的意义是重复的类型，即回放时的动作。


    android:interpolator            setInterpolator(Interpolator) 设定插值器，其实就是指定的动作效果，比如弹跳效果等
    Interpolator class	                Resource ID
    AccelerateDecelerateInterpolator	 @android:anim/accelerate_decelerate_interpolator
    AccelerateInterpolator	            @android:anim/accelerate_interpolator
    AnticipateInterpolator	            @android:anim/anticipate_interpolator
    AnticipateOvershootInterpolator	     @android:anim/anticipate_overshoot_interpolator
    BounceInterpolator	                @android:anim/bounce_interpolator
    CycleInterpolator	                    @android:anim/cycle_interpolator
    DecelerateInterpolator	            @android:anim/decelerate_interpolator
    LinearInterpolator	                @android:anim/linear_interpolator
    OvershootInterpolator	            @android:anim/overshoot_interpolator
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_animation_astrs_xml);
        ButterKnife.bind(this);
        setSupportActionBar(vToolbar);
    }

    /*
    android:fromXScale    起始的X方向上相对自身的缩放比例，浮点值，比如1.0代表自身无变化，0.5代表起始时缩小一倍，2.0代表放大一倍；
    android:toXScale        结尾的X方向上相对自身的缩放比例，浮点值；
    android:fromYScale    起始的Y方向上相对自身的缩放比例，浮点值，
    android:toYScale        结尾的Y方向上相对自身的缩放比例，浮点值；
    android:pivotX            缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，当为数值时，表示在当前View的左上角，
                            即原点处加上50px，做为起始缩放点；如果是50%，表示在当前控件的左上角加上自己宽度的50%做为起始点；如果是50%p，
                              那么就是表示在当前的左上角加上父控件宽度的50%做为起始点x轴坐标。（具体意义，后面会举例演示）
    android:pivotY           缩放起点Y轴坐标，取值及意义跟android:pivotX一样。

    代码对应函数
    ScaleAnimation(Context context, AttributeSet attrs)  从XML文件加载动画，基本用不到
    ScaleAnimation(float fromX, float toX, float fromY, float toY)
    ScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY)
    ScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
    在标签属性android:pivotX中有三种取值，数，百分数，百分数p；体现在构造函数中，就是最后一个构造函数的pivotXType,
    它的取值有三个，Animation.ABSOLUTE、Animation.RELATIVE_TO_SELF和Animation.RELATIVE_TO_PARENT
     */
    @OnClick(R.id.scale_one)
    public void onScaleClick(View v) {
        ScaleAnimation animation = new ScaleAnimation(0.2f, 1.5f, 0.2f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setRepeatCount(3);
        animation.setRepeatMode(Animation.REVERSE);
        vTv.startAnimation(animation);
    }

    /*
    android:fromAlpha   动画开始的透明度，从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
    android:toAlpha       动画结束时的透明度，也是从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明

    对应的函数
    AlphaAnimation(Context context, AttributeSet attrs)  同样，从本地XML加载动画，基本不用
    AlphaAnimation(float fromAlpha, float toAlpha)
     */
    @OnClick(R.id.alpha)
    public void onAlphaClick(View v){
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.1f);
        animation.setDuration(1000);
        animation.setRepeatCount(3);
        animation.setRepeatMode(Animation.RESTART);
        animation.setFillBefore(true);
        vTv.startAnimation(animation);
    }

    /*
    android:fromDegrees     开始旋转的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
    android:toDegrees         结束时旋转到的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
    android:pivotX               缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，
                                具体意义已在scale标签中讲述，这里就不再重讲
    android:pivotY               缩放起点Y轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p
    对应的函数
    RotateAnimation(Context context, AttributeSet attrs)　　从本地XML文档加载动画，同样，基本不用
    RotateAnimation(float fromDegrees, float toDegrees)
    RotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY)
    RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
    是pivotXType和pivotYType的选择，同样有三个取值：Animation.ABSOLUTE、Animation.RELATIVE_TO_SELF和Animation.RELATIVE_TO_PARENT
    查看源码发现，如果不选择pivotXType和pivotYType 这两个值默认是Animation.ABSOLUTE
     */
    @OnClick(R.id.rotate)
    public void onRotateClick(View v){
        RotateAnimation animation = new RotateAnimation(0, -650, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(2000);
        vTv.startAnimation(animation);
    }

    /*
    android:fromXDelta     起始点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，具体意义已在scale标签中讲述，这里就不再重讲
    android:fromYDelta    起始点Y轴从标，可以是数值、百分数、百分数p 三种样式；
    android:toXDelta         结束点X轴坐标
    android:toYDelta        结束点Y轴坐标
    对应的函数
    TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
    TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)
     */
    @OnClick(R.id.translate)
    public void onTranslateClick(View v){
        TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -80,
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -80);
        animation.setDuration(1000);
        animation.setFillBefore(true);
        animation.setInterpolator(new AnticipateInterpolator());
        vTv.startAnimation(animation);
    }


    /*
    AnimationSet(Context context, AttributeSet attrs)  同样，基本不用
    AnimationSet(boolean shareInterpolator)  shareInterpolator取值true或false，取true时，
                                                指在AnimationSet中定义一个插值器（interpolater），
                                                它下面的所有动画共同。如果设为false，
                                                则表示它下面的动画自己定义各自的插值器。
     */
    @OnClick(R.id.set)
    public void onSetClick(View v){
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f,0.1f);
        ScaleAnimation scaleAnim = new ScaleAnimation(0.0f,1.4f,0.0f,1.4f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        RotateAnimation rotateAnim = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        AnimationSet setAnim=new AnimationSet(true);
        setAnim.addAnimation(alphaAnim);
        setAnim.addAnimation(scaleAnim);
        setAnim.addAnimation(rotateAnim);
        setAnim.setInterpolator(new BounceInterpolator());
        setAnim.setDuration(3000);
        setAnim.setFillAfter(true);
        vTv.startAnimation(setAnim);
    }




}
