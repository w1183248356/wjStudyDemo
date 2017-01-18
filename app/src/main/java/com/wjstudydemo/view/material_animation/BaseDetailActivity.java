package com.wjstudydemo.view.material_animation;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wjstudydemo.R;
import com.wjstudydemo.util.TransitionHelper;

/**
 * Created by lgvalle on 12/09/15.
 */
public class BaseDetailActivity extends AppCompatActivity {
    public static final String EXTRA_SAMPLE = "sample";
    public static final String EXTRA_TYPE = "type";
    public static final int TYPE_PROGRAMMATICALLY = 0;
    public static final int TYPE_XML = 1;

    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
         * setHomeButtonEnabled这个小于4.0版本的默认值为true的。但是在4.0及其以上是false，该方法的作用：决定左上角的图标是否可以点击。
         * 没有向左的小图标。 true 图标可以点击  false 不可以点击。
         * actionBar.setDisplayHomeAsUpEnabled(true)    // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
         * actionBar.setDisplayShowHomeEnabled(true)   //使左上角图标是否显示，如果设成false，则没有程序图标，
         * 仅仅就个标题，否则，显示应用程序图标，对应id为Android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
         * actionBar.setDisplayShowCustomEnabled(true)  // 使自定义的普通View能在title栏显示，即actionBar.setCustomView能起作用，对应ActionBar.DISPLAY_SHOW_CUSTOM
         * actionBar.setDisplayShowTitleEnabled(true)   //对应ActionBar.DISPLAY_SHOW_TITLE。
         * 其中 setHomeButtonEnabled 和 setDisplayShowHomeEnabled 共同起作用，如果setHomeButtonEnabled设成false，即使setDisplayShowHomeEnabled设成true，图标也不能点击
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressWarnings("unchecked")
    void transitionTo(Intent i) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
            startActivity(i, transitionActivityOptions.toBundle());
        }
    }
}
