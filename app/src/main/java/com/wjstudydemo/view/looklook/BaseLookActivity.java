package com.wjstudydemo.view.looklook;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.wjstudydemo.App;

/**
 * @author wangjian
 * @title BaseLookActivity
 * @description
 * @modifier
 * @date
 * @since 2017/2/8 10:22
 **/
public class BaseLookActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    public static BaseLookActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        // TODO: 2017/2/8 这里可以添加三方的控件  类似umeng
        activity = this;
        App.getApp().addLookActivity(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    private void init() {
        initData();
        initEvents();
    }

    /***
     * 初始化事件（监听事件等事件绑定）
     */
    protected void initEvents() {
    }

    /**
     * 绑定数据
     */
    protected void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ((App)App.getApplication()).removeLookActivity(this);
    }
}
