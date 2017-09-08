package com.wjstudydemo.nokillservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.wjstudydemo.util.ServiceUtil;

/**
 * Created by wangjian on 2017/9/5.
 *
 * @detail
 */

public class DemoServer2 extends Service {
    private String TAG = getClass().getName();
    private String Process_Name = "com.wjstudydemo:demoservice";

    private StrongService ss = new StrongService.Stub(){

        @Override
        public void startService() throws RemoteException {
            Intent i = new Intent(getBaseContext(), DemoServer.class);
            getBaseContext().startService(i);
        }

        @Override
        public void stopService() throws RemoteException {
            Intent i = new Intent(getBaseContext(), DemoServer.class);
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
        keepService2();
    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent(this, DemoServer2.class);
        startService(intent);
    }

    /**
     * 判断Service2是否还在运行，如果不是则启动Service2
     */
    private  void keepService2(){
        boolean isRun = ServiceUtil.isProessRunning(DemoServer2.this, Process_Name);
        if (isRun == false) {
            try {
                Toast.makeText(getBaseContext(), "重新启动 Service2", Toast.LENGTH_SHORT).show();
                ss.startService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder)ss;
    }
}
