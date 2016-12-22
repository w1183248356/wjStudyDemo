package com.wjstudydemo;

import android.app.Activity;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;

import com.network.network.model.LegworkInfo;
import com.network.network.util.L;
import com.squareup.leakcanary.LeakCanary;
import com.wjstudydemo.exception.CrashHandler;
import com.wjstudydemo.util.GlideImageLoader;
import com.wjstudydemo.view.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;


/**
 * Application
 */
public class App extends MultiDexApplication {
    public static App app;
    private List<WeakReference<Activity>> mOpenActivitesList = new ArrayList<>();
    private LegworkInfo mLegworkInfo;

    /**
     * 获取 applicationContext对象
     *
     * @return
     */
    public static App getApp() {
        return app;
    }

    public LegworkInfo getLegworkInfo() {
        return mLegworkInfo;
    }

    public void setLegworkInfo(LegworkInfo legworkInfo) {
        mLegworkInfo = legworkInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (app == null) {
            app = this;
        }
        //安装内存检测
        if (false) {
            if (LeakCanary.isInAnalyzerProcess(this))
                return;
            LeakCanary.install(this);
        }

        //初始化日志
        L.init();
        EventBus.getDefault().register(this);

        /**全局catch捕获 防止应用退出**/
        if (!BuildConfig.DEBUG) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(getApplicationContext());
        }

        //初始化Stetho
        //Stetho.initializeWithDefaults(this);

        //初始化Galleryfinal
        initGalleryfinal();

    }

    private void initGalleryfinal() {
        //设置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.colorPrimary))
                .setFabNornalColor(getResources().getColor(R.color.colorPrimary))
                .setFabPressedColor(getResources().getColor(R.color.colorPrimary))
                .setCheckSelectedColor(getResources().getColor(R.color.colorPrimary))
                .setCropControlColor(getResources().getColor(R.color.colorPrimary))
                .build();


        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        ImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
    }

    public void addActivity(WeakReference<Activity> activity) {
        if (!mOpenActivitesList.contains(activity))
            mOpenActivitesList.add(activity);
    }


    /**
     * 回退到主界面并且显示第一个fargment
     */
    public void backToIndexActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    // 关闭每一个list内的activity
    public void exit() {
        try {
            for (WeakReference<Activity> activity : mOpenActivitesList) {
                if (activity.get() != null)
                    activity.get().finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Subscribe
    public void getHXInfoById(String userId) {
//        Map<String, String> params = new HashMap<>();
//        params.put("customerUserNo", UserBusiness.getUserId());
//        params.put("vendorUserNo", userId);
//
//        RESTApiImpl.getHXInfoById(params, null).subscribe(jsonObject -> {
//            EventBus.getDefault().post(jsonObject);
//        });

    }


}
