package com.wjstudydemo.present;

import android.os.Bundle;

import com.wjstudydemo.view.RecyclerViewDemoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjian
 * @title RecyclerViewPresenter
 * @description
 * @modifier
 * @date
 * @since 2016/12/23 9:12
 **/
public class RecyclerViewPresenter extends BasePresenter<RecyclerViewDemoActivity> {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onTakeView(RecyclerViewDemoActivity activity) {
        super.onTakeView(activity);
        List<String> list = new ArrayList<>();
        list = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            list.add("" + (char) i);
        }
        activity.setList(list);
    }
}
