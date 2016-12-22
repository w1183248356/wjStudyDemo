package com.wjstudydemo.interfaces;

/**
 * Created by wlw-97 on 2016/10/25.
 */

public interface OnStandardChangeListener {
    void onStandardChange(String standard, String stkc, int count, String price);

    void onStandardWindowClose(String stkc);
}
