package com.wjstudydemo.animation.evaluator;

import android.animation.TypeEvaluator;

import com.wjstudydemo.bean.EvaluatorObj1;

/**
 * @author wangjian
 * @title PointEvaluator
 * @description
 * @modifier
 * @date
 * @since 2016/12/28 16:49
 **/
public class PointEvaluator implements TypeEvaluator<EvaluatorObj1> {
    @Override
    public EvaluatorObj1 evaluate(float fraction, EvaluatorObj1 startValue, EvaluatorObj1 endValue) {
        int startRadius = startValue.getRadius();
        int endRadius = endValue.getRadius();
        int startArgb = startValue.getArgb();
        int endArgb = endValue.getArgb();

        int result = (int) (startRadius + fraction*(endRadius - startRadius));
        int startInt = (Integer) startArgb;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endArgb;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;
        int resultArgb = (int)((startA + (int)(fraction * (endA - startA))) << 24) |
                (int)((startR + (int)(fraction * (endR - startR))) << 16) |
                (int)((startG + (int)(fraction * (endG - startG))) << 8) |
                (int)((startB + (int)(fraction * (endB - startB))));
        return new EvaluatorObj1(result, resultArgb);
    }
}
