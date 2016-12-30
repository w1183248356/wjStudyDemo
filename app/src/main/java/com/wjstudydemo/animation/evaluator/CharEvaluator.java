package com.wjstudydemo.animation.evaluator;

import android.animation.TypeEvaluator;

/**
 * @author wangjian
 * @title CharEvaluator
 * @description 字符转换器
 * @modifier
 * @date
 * @since 2016/12/28 16:20
 **/
public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = startValue;
        int endInt = endValue;
        char result = (char) (startInt + fraction*(endInt - startInt));
        return result;
    }
}
