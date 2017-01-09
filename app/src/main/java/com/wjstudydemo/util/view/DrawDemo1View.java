package com.wjstudydemo.util.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wangjian
 * @title DrawViewDemo1
 * @description
 * @modifier
 * @date
 * @since 2017/1/5 15:38
 **/
public class DrawDemo1View extends View {
    public DrawDemo1View(Context context) {
        super(context);
    }

    public DrawDemo1View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawDemo1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿功能
        paint.setColor(Color.RED);  //设置画笔颜色
        paint.setStyle(Paint.Style.FILL);//设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.setStrokeWidth(5);//设置画笔宽度
        paint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影

        //设置画布背景颜色
        canvas.drawRGB(255, 255,255);
        //画圆
        canvas.drawCircle(190, 200, 150, paint);
    }
}
