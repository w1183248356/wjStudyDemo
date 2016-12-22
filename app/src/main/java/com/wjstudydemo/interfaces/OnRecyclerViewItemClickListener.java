package com.wjstudydemo.interfaces;

import android.view.View;

/**
 * Created by Jin on 2016/4/12 0012.
 */
public interface OnRecyclerViewItemClickListener<T> {
    void onItemClick(View view, T data);
}
