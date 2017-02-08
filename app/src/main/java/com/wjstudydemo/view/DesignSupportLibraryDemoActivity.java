package com.wjstudydemo.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;
import com.wjstudydemo.present.DesignSupportLibraryDemoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;

/**
 * @author wangjian
 * @title DesignSuppotLibraryDemoActivity
 * @description
 * @modifier
 * @date
 * @since 2016/12/26 11:21
 **/
@RequiresPresenter(DesignSupportLibraryDemoPresenter.class)
public class DesignSupportLibraryDemoActivity extends BaseNucleusActivity<DesignSupportLibraryDemoPresenter>{
    @BindView(R.id.fab)
    FloatingActionButton vFab;
    @BindView(R.id.toolbar)
    Toolbar vToolbar;
    @BindView(R.id.tabs)
    TabLayout vTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_design_demo);
        ButterKnife.bind(this);
        initView();
    }

    public void initView(){
        /*
        style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar"
        这里需要设置页面的theme 设置
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
         */
        setSupportActionBar(vToolbar);//通知页面 使用 Toolbar 作为一个 Action Bar
        vTabLayout.addTab(vTabLayout.newTab().setText("Tab 1"));
        vTabLayout.addTab(vTabLayout.newTab().setText("Tab 2"));
        vTabLayout.addTab(vTabLayout.newTab().setText("Tab 3"));
        vTabLayout.addTab(vTabLayout.newTab().setText("Tab 4"));
        vTabLayout.addTab(vTabLayout.newTab().setText("Tab 5"));
        vTabLayout.addTab(vTabLayout.newTab().setText("Tab 6"));
        vFab.setOnClickListener(view->{
            Snackbar.make(view, "FAB", Snackbar.LENGTH_SHORT).setAction("确定", v->{
                Toast.makeText(DesignSupportLibraryDemoActivity.this, "点击确定的效果", Toast.LENGTH_SHORT).show();
            }).show();
        });
    }

    public void initTop(){

    }
}
