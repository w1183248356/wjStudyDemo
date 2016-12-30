package com.wjstudydemo.util.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.wjstudydemo.animation.evaluator.PointEvaluator;
import com.wjstudydemo.bean.EvaluatorObj1;

/**
 * @author wangjian
 * @title MyPointView1
 * @description
 * @modifier
 * @date
 * @since 2016/12/28 16:45
 **/
public class MyPointView1 extends View {
    private EvaluatorObj1 mCurPoint;
    public MyPointView1(Context context) {
        super(context);
    }

    public MyPointView1(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mCurPoint == null) {
            mCurPoint = new EvaluatorObj1(20, 0xffffff00);
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mCurPoint.getArgb());
//        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(300, 300, mCurPoint.getRadius(), paint);
    }

    public void doPointAnim(){
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), new EvaluatorObj1(2, Color.RED), new EvaluatorObj1(200, Color.BLUE));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurPoint = (EvaluatorObj1) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(10000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }
}
