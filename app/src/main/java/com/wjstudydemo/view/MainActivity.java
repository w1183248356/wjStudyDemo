package com.wjstudydemo.view;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.wjstudydemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setGradients(SeekBar seekBar, int color){
        int[] mColors = new int[]{R.color.color_blue, color};
        GradientDrawable mDrawble = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, mColors);
        mDrawble.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        mDrawble.setGradientRadius(15);
        mDrawble.setStroke(15, getResources().getColor(R.color.color_zise));//设置边框的
        seekBar.setProgressDrawable(mDrawble);
    }
}
