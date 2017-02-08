package com.wjstudydemo.view.looklook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wjstudydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wangjian
 * @title LookLookMainActivity
 * @description
 * @modifier
 * @date
 * @since 2017/2/8 10:22
 **/
public class LookLookMainActivity extends BaseLookActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    SimpleArrayMap<Integer, String> mTitleArryMap = new SimpleArrayMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_looklook);
        ButterKnife.bind(this);
        initView();
        addfragmentsAndTitle();
    }

    public void initView(){
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_open:
                    drawer.openDrawer(GravityCompat.END);
                    break;
                case R.id.menu_about:
//                    goAboutActivity();
                    break;
            }
            return true;
        });

    }

    private void addfragmentsAndTitle() {
        mTitleArryMap.put(R.id.zhihuitem, getResources().getString(R.string.zhihu));
        mTitleArryMap.put(R.id.topnewsitem, getResources().getString(R.string.topnews));
        mTitleArryMap.put(R.id.meiziitem, getResources().getString(R.string.meizi));

    }

    private void animateToolBar(){
        View t = toolbar.getChildAt(0);
        if(t != null && t instanceof TextView){
            TextView title = (TextView) t;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_look, menu);
        return true;
    }


}
