package com.wjstudydemo.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by wangjian on 2017/9/5.
 *
 * @detail
 */

public class ServiceUtil {
    /**
     * 判断进程是否运行
     * @return
     */
    public static boolean isProessRunning(Context context, String proessName) {

        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : lists) {
            if (info.processName.equals(proessName)) {
                isRunning = true;
            }
        }

        return isRunning;
    }
}
