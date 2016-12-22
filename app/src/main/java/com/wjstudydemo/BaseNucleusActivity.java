package com.wjstudydemo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wjstudydemo.util.AppForegroundStateManager;

import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;

/**
 * Created by Jin on 2016/3/12.
 */
public class BaseNucleusActivity<PresenterType extends Presenter> extends NucleusAppCompatActivity<PresenterType> {
    /**
     * 导航的右边的按钮
     */
    public Button mRight_bt;
    /**
     * 导航左边的按钮
     */
    public ImageButton mLeft_bt;
    /**
     * 导航的中间的文本
     */
    public TextView mCenter_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        ((App) getApplicationContext()).addActivity(new WeakReference<>(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppForegroundStateManager.getInstance().onActivityVisible(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppForegroundStateManager.getInstance().onActivityNotVisible(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    /**
     * 设置titiel的方法
     *
     * @param lefeVisbile  左边按钮是否可用
     * @param rightVisbile 右边的按钮是否可用
     * @param centerText   中间的标题的文字
     */
    public void setTitle(Boolean lefeVisbile, String centerText, Boolean rightVisbile) {
//        mLeft_bt = (ImageButton) findViewById(R.id.title_left_bt);
//        mRight_bt = (Button) findViewById(R.id.title_right_bt);
//        mCenter_tv = (TextView) findViewById(R.id.title_text_tv);
//        mCenter_tv.setText(centerText);
//        if (lefeVisbile) {
//            mLeft_bt.setVisibility(View.VISIBLE);
//        } else {
//            mLeft_bt.setVisibility(View.INVISIBLE);
//        }
//        if (rightVisbile) {
//            mRight_bt.setVisibility(View.VISIBLE);
//        } else {
//            mRight_bt.setVisibility(View.INVISIBLE);
//        }
    }
}
