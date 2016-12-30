package com.wjstudydemo.bean;

/**
 * @author wangjian
 * @title Point
 * @description
 * @modifier
 * @date
 * @since 2016/12/28 16:47
 **/
public class EvaluatorObj1 {
    private int radius;
    private int argb;

    public EvaluatorObj1(int radius, int argb){
        this.radius = radius;
        this.argb = argb;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getArgb() {
        return argb;
    }

    public void setArgb(int argb) {
        this.argb = argb;
    }
}
