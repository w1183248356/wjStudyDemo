package com.wjstudydemo.nokillservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.wjstudydemo.R;
import com.wjstudydemo.util.ServiceUtil;
import com.wjstudydemo.view.customview.CircleProgressBarActivity;

/**
 * Created by wangjian on 2017/9/5.
 *
 * @detail
 */

public class DemoServer extends Service {
    private String TAG = getClass().getName();
    private String Process_Name = "com.wjstudydemo:demoservice2";

    private StrongService ss = new StrongService.Stub(){

        @Override
        public void startService() throws RemoteException {
            Intent i = new Intent(getBaseContext(), DemoServer2.class);
            getBaseContext().startService(i);
        }

        @Override
        public void stopService() throws RemoteException {
            Intent i = new Intent(getBaseContext(), DemoServer2.class);
            getBaseContext().stopService(i);
        }
    };

    @Override
    public void onTrimMemory(int level) {
        Log.e(TAG, "onTrimMemory" + "  level = " + level);
        keepService2();//保持Service2一直运行
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
//        Notification notification = new Notification(R.mipmap.ic_launcher,"启动服务发出通知", System.currentTimeMillis());
//        //设置内容和点击事件
//        Intent intent = new Intent(this, CircleProgressBarActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,intent , 0);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                //设置小图标
//                .setSmallIcon(R.mipmap.ic_launcher)
//                //设置通知标题
//                .setContentTitle("最简单的Notification")
//                //设置通知内容
//                .setContentText("只有小图标、标题、内容");
//        设置通知时间，默认为系统发出通知的时间，通常不用设置
//        .setWhen(System.currentTimeMillis());
//        startForeground(1, builder.build());
    }

    /**
     * 判断Service2是否还在运行，如果不是则启动Service2
     */
    private  void keepService2(){
        boolean isRun = ServiceUtil.isProessRunning(DemoServer.this, Process_Name);
        if (isRun == false) {
            try {
                Toast.makeText(getBaseContext(), "重新启动 Service", Toast.LENGTH_SHORT).show();
                ss.startService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent(this, DemoServer.class);
        startService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder)ss;
    }
}
