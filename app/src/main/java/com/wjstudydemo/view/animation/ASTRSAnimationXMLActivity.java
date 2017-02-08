package com.wjstudydemo.view.animation;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;
import com.wjstudydemo.present.animation.ASTRSAnimationXMLPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;

/**
 * @author wangjian
 * @title ASTRSAnimationXMLActivity
 * @description alpha、scale、translate、rotate、set 的xml属性及用法
 *                  XML配置文件中
 *                  alpha   渐变透明度动画效果
 *                  scale   渐变尺寸伸缩动画效果
 *                  translate   画面转换位置移动动画效果
 *                  rotate  画面转移旋转动画效果
 * @modifier
 * @date
 * @since 2016/12/27 11:32
 **/
@RequiresPresenter(ASTRSAnimationXMLPresenter.class)
public class ASTRSAnimationXMLActivity extends BaseNucleusActivity<ASTRSAnimationXMLPresenter> {
    @BindView(R.id.toolbar)
    Toolbar vToolbar;
    @BindView(R.id.tv)
    TextView vTv;
    /*
    通用属性
    android:duration        动画持续时间，以毫秒为单位
    android:fillAfter          如果设置为true，控件动画结束时，将保持动画最后时的状态
    android:fillBefore       如果设置为true,控件动画结束时，还原到开始动画前的状态
    android:fillEnabled    与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
    android:repeatCount     重复次数
    android:repeatMode	重复类型，有reverse和restart两个值，reverse表示倒序回放，restart表示重新放一遍，
                            必须与repeatCount一起使用才能看到效果。因为这里的意义是重复的类型，即回放时的动作。
    android:interpolator  设定插值器，其实就是指定的动作效果，比如弹跳效果等，不在这小节中讲解，后面会单独列出一单讲解。
    */

    /*
    Interpolator插值器
    使用方法 android:interpolator="@android:anim/accelerate_decelerate_interpolator"
    AccelerateDecelerateInterpolator 在动画开始与介绍的地方速率改变比较慢，在中间的时候加速
    AccelerateInterpolator 在动画开始的地方速率改变比较慢，然后开始加速
    AnticipateInterpolator 开始的时候向后然后向前甩
    AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值
    BounceInterpolator 动画结束的时候弹起
    CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线
    DecelerateInterpolator 在动画开始的地方快然后慢
    LinearInterpolator 以常量速率改变
    OvershootInterpolator 向前甩一定值后再回到原来位置
    */
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_animation_astrs_xml);
        ButterKnife.bind(this);
        setSupportActionBar(vToolbar);
    }

    /*
    scale   渐变尺寸伸缩动画效果
    android:fromXScale    起始的X方向上相对自身的缩放比例，浮点值，比如1.0代表自身无变化，0.5代表起始时缩小一倍，2.0代表放大一倍；
    android:toXScale        结尾的X方向上相对自身的缩放比例，浮点值；
    android:fromYScale    起始的Y方向上相对自身的缩放比例，浮点值，
    android:toYScale        结尾的Y方向上相对自身的缩放比例，浮点值；
    android:pivotX            缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，当为数值时，表示在当前View的左上角，
                                即原点处加上50px，做为起始缩放点；如果是50%，表示在当前控件的左上角加上自己宽度的50%做为起始点；
                                如果是50%p，那么就是表示在当前的左上角加上父控件宽度的50%做为起始点x轴坐标。（具体意义，后面会举例演示）
    android:pivotY           缩放起点Y轴坐标，取值及意义跟android:pivotX一样。

    */
    @OnClick(R.id.scale_one)
    public void onScaleOneClick(View v){
        animation = AnimationUtils.loadAnimation(this, R.anim.scale_one);
        vTv.startAnimation(animation);
    }

    /*
    android:fromAlpha   动画开始的透明度，从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
    android:toAlpha       动画结束时的透明度，也是从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
    */
    @OnClick(R.id.alpha)
    public void onalphaClick(View v){
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha_one);
        vTv.startAnimation(animation);
    }

    /*
    android:fromDegrees     开始旋转的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
    android:toDegrees         结束时旋转到的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
    android:pivotX               缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，具体意义已在scale标签中讲述，这里就不再重讲
    android:pivotY               缩放起点Y轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p
     */
    @OnClick(R.id.rotate)
    public void onRotateClick(View v){
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate_one);
        vTv.startAnimation(animation);
    }

    /*
    android:fromXDelta     起始点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，具体意义已在scale标签中讲述，这里就不再重讲
    android:fromYDelta    起始点Y轴从标，可以是数值、百分数、百分数p 三种样式；
    android:toXDelta         结束点X轴坐标
    android:toYDelta        结束点Y轴坐标
     */
    @OnClick(R.id.translate)
    public void onTranslateClick(View v){
        animation = AnimationUtils.loadAnimation(this, R.anim.translate_one);
        vTv.startAnimation(animation);
    }

    @OnClick(R.id.set)
    public void onSetClick(View v){
        animation = AnimationUtils.loadAnimation(this, R.anim.set_one);
        vTv.startAnimation(animation);
    }

}
