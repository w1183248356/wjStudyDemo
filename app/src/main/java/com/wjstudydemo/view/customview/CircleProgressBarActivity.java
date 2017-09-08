package com.wjstudydemo.view.customview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.customview.CircleProgressBar;
import com.wjstudydemo.nokillservice.DemoServer;
import com.wjstudydemo.R;
import com.wjstudydemo.nokillservice.DemoServer2;
import com.wjstudydemo.view.material_animation.BaseDetailActivity;

import java.util.Timer;

/**
 * Created by 49902 on 2017/8/24.
 *
 * @detail
 */

public class CircleProgressBarActivity extends BaseDetailActivity {

    int i = 0;
    private Timer timer = new Timer();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            bar.setProgress(i);
            i+=20;
            if(i > 100){
                timer.cancel();
            }
            super.handleMessage(msg);
        }
    };
    CircleProgressBar bar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cirvleprogressbar);
        Intent i1 = new Intent(this,DemoServer.class);
        startService(i1);

        Intent i2 = new Intent(this,DemoServer2.class);
        startService(i2);
        bar = (CircleProgressBar) findViewById(R.id.crb);
        bar.setAllProgress(100);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                handler.sendEmptyMessage(1);
//            }
//        };
//        timer.schedule(task, 100, 200);


    }
}
