package com.wjstudydemo.view.userDefinedWidget;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;
import com.wjstudydemo.util.view.DrawDemo1View;

/**
 * @author wangjian
 * @title DrawDemo1Activity
 * @description
 * @modifier
 * @date
 * @since 2017/1/5 15:35
 **/
public class DrawDemo1Activity extends BaseNucleusActivity {
    FrameLayout fl;

    /*
    Paint与Canvas
    像我们平时画图一样，需要两个工具，纸和笔。Paint就是相当于笔，而Canvas就是纸，这里叫画布。

    所以，凡有跟要要画的东西的设置相关的，比如大小，粗细，画笔颜色，透明度，字体的样式等等，都是在Paint里设置；
    同样，凡是要画出成品的东西，比如圆形，矩形，文字等相关的都是在Canvas里生成。

    paint.setAntiAlias(true);//抗锯齿功能
    paint.setColor(Color.RED);  //设置画笔颜色
    paint.setStyle(Style.FILL);//设置填充样式
    paint.setStrokeWidth(30);//设置画笔宽度
    paint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_draw_demo1);
        fl = (FrameLayout) findViewById(R.id.root);
        fl.addView(new DrawDemo1View(this));
    }
}
