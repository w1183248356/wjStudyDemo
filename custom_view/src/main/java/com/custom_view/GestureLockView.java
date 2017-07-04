package com.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author wangjian
 * @title GestureLockView
 * @description
 * @modifier
 * @date
 * @since 2017/7/4 21:40
 **/
public class GestureLockView extends ViewGroup {
    public GestureLockView(Context context) {
        super(context);
    }

    public GestureLockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureLockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
