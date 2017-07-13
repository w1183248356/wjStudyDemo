package com.wjstudydemo.view.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.customview.BreakLineView;
import com.customview.NumView;
import com.customview.SignView;
import com.wjstudydemo.R;
import com.wjstudydemo.view.material_animation.BaseDetailActivity;

/**
 * @author wangjian
 * @title SignActivity
 * @description
 * @modifier
 * @date
 * @since 2017/7/7 17:23
 **/
public class SignActivity extends BaseDetailActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        SignView sv = (SignView) findViewById(R.id.signView);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.reset();
            }
        });
        BreakLineView bv = (BreakLineView) findViewById(R.id.bv);
        bv.startAnim();

        ImageView div = (ImageView) findViewById(R.id.dragImageView);
        NumView nv = (NumView) findViewById(R.id.mNum);
        nv.setOnAddOrSubListener(new NumView.OnAddOrSubListener(){

            @Override
            public void onAdd(int num) {
                if(num < 99999){
                    num ++;
                }
                nv.setNum(num);
            }

            @Override
            public void onSub(int num) {
                if(num > 0){
                    num --;
                }
                nv.setNum(num);
            }
        });

    }
}
