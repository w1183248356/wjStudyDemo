package com.wjstudydemo;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.network.network.model.LegworkInfo;
import com.network.network.util.L;
import com.squareup.leakcanary.LeakCanary;
import com.wjstudydemo.exception.CrashHandler;
import com.wjstudydemo.util.GlideImageLoader;
import com.wjstudydemo.view.MainActivity;
import com.wjstudydemo.view.looklook.BaseLookActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;


/**
 * Application
 */
public class App extends MultiDexApplication {
    public static App app;
    private static int mainTid;
    private LegworkInfo mLegworkInfo;
    /**
     * LookLook所有界面集合，来管理LookLook所有的Activity
     */
    private static List<BaseLookActivity> lookActivities;

    /**
     * 获取 applicationContext对象
     *
     * @return
     */
    public static App getApp() {
        return app;
    }

    static {
        /*
        设置夜间模式
        详细介绍  http://blog.csdn.net/rongbinjava/article/details/51841141
        MODE_NIGHT_NO 日间模式
        MODE_NIGHT_YES 夜间模式
        MODE_NIGHT_AUTO 根据时间自动切换日夜间模式
        MODE_NIGHT_FOLLOW_SYSTEM 默认模式，就是跟随系统的设置，
        据说有可能以后会在android系统设置中添加日夜间模式的设置，
        此时如果你的app是默认模式，会根据系统设置变化日夜间模式
         */
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM);
    }

    public LegworkInfo getLegworkInfo() {
        return mLegworkInfo;
    }

    public void setLegworkInfo(LegworkInfo legworkInfo) {
        mLegworkInfo = legworkInfo;
    }

    /**
     * 获取application
     *
     * @return
     */
    public static Context getApplication() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lookActivities = new LinkedList<>();
        mainTid = android.os.Process.myTid();
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

    /**
     * GalleryFinal默认主题为DEFAULT（深蓝色）,还自带主题：DARK（黑色主题）、CYAN（蓝绿主题）、
     * ORANGE（橙色主题）、GREEN（绿色主题）和TEAL（青绿色主题），
     * 当然也支持自定义主题（Custom Theme）,
     * 在自定义主题中用户可以配置字体颜色、图标颜色、更换图标、和背景色
     * http://www.open-open.com/lib/view/open1452169389417.html
     */
    private void initGalleryfinal() {
        //设置主题
        /*
        setTitleBarTextColor//标题栏文本字体颜色
        setTitleBarBgColor//标题栏背景颜色
        setTitleBarIconColor//标题栏icon颜色，如果设置了标题栏icon，设置setTitleBarIconColor将无效
        setCheckNornalColor//选择框未选颜色
        setCheckSelectedColor//选择框选中颜色
        setCropControlColor//设置裁剪控制点和裁剪框颜色
        setFabNornalColor//设置Floating按钮Nornal状态颜色
        setFabPressedColor//设置Floating按钮Pressed状态颜色

        setIconBack//设置返回按钮icon
        setIconCamera//设置相机icon
        setIconCrop//设置裁剪icon
        setIconRotate//设置选择icon
        setIconClear//设置清楚选择按钮icon（标题栏清除选择按钮）
        setIconFolderArrow//设置标题栏文件夹下拉arrow图标
        setIconDelete//设置多选编辑页删除按钮icon
        setIconCheck//设置checkbox和文件夹已选icon
        setIconFab//设置Floating按钮icon
        setEditPhotoBgTexture//设置图片编辑页面图片margin外背景
        setIconPreview设置预览按钮icon
        setPreviewBg设置预览页背景
         */
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.colorPrimary))
                .setFabNornalColor(getResources().getColor(R.color.colorPrimary))
                .setFabPressedColor(getResources().getColor(R.color.colorPrimary))
                .setCheckSelectedColor(getResources().getColor(R.color.colorPrimary))
                .setCropControlColor(getResources().getColor(R.color.colorPrimary))
                .build();


        //配置功能
        /*
        setMutiSelect(boolean)//配置是否多选
        setMutiSelectMaxSize(int maxSize)//配置多选数量
        setEnableEdit(boolean)//开启编辑功能
        setEnableCrop(boolean)//开启裁剪功能
        setEnableRotate(boolean)//开启选择功能
        setEnableCamera(boolean)//开启相机功能
        setCropWidth(int width)//裁剪宽度
        setCropHeight(int height)//裁剪高度
        setCropSquare(boolean)//裁剪正方形
        setSelected(List)//添加已选列表,只是在列表中默认呗选中不会过滤图片
        setFilter(List list)//添加图片过滤，也就是不在GalleryFinal中显示
        takePhotoFolter(File file)//配置拍照保存目录，不做配置的话默认是/sdcard/DCIM/GalleryFinal/
        setRotateReplaceSource(boolean)//配置选择图片时是否替换原始图片，默认不替换
        setCropReplaceSource(boolean)//配置裁剪图片时是否替换原始图片，默认不替换
        setForceCrop(boolean)//启动强制裁剪功能,一进入编辑页面就开启图片裁剪，不需要用户手动点击裁剪，此功能只针对单选操作
        setForceCropEdit(boolean)//在开启强制裁剪功能时是否可以对图片进行编辑（也就是是否显示旋转图标和拍照图标）
        setEnablePreview(boolean)//是否开启预览功能
         */
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        /*
        开发者可以根据app现使用的ImageLoader来实现GalleryFinal ImageLoader，个人相对比较看好Glide，
        当然Fresco非常棒,不过它非常大。Picasso和UIL是老牌的ImageLoader也可以。至于Xutil3现在还不稳定，
        有些问题待修复。如果你有其他的ImageLoader也可以参考demo自行实现，
        如果有不明白之处加Q群(218801658)提问。
         */
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
