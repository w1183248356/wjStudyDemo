package com.wjstudydemo.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wjstudydemo.R;

/**
 * @author wangjian
 * @title LayoutTop
 * @description 头部
 * @modifier
 * @date
 * @since 2016/12/22 14:33
 **/
public class LayoutTop {
    private TextView title;
    private ImageButton imgRight;
    private ImageButton imgLeft;
    private Button right;
    private Button left;
    private LayoutTop.OnLeftListener onLeft;
    private LayoutTop.OnRightListener onRight;

    public LayoutTop(Context context, View view) {
        this.title = (TextView)view.findViewById(R.id.title);
        this.imgRight = (ImageButton)view.findViewById(R.id.imagebutton_right);
        this.imgLeft = (ImageButton)view.findViewById(R.id.imagebutton_left);
        this.right = (Button)view.findViewById(R.id.button_right);
        this.left = (Button)view.findViewById(R.id.button_left);
        this.imgRight.setVisibility(View.GONE);
        this.imgLeft.setVisibility(View.GONE);
        this.right.setVisibility(View.GONE);
        this.left.setVisibility(View.GONE);
    }

    public LayoutTop(Activity activity) {
        this.title = (TextView)activity.findViewById(R.id.title);
        this.imgRight = (ImageButton)activity.findViewById(R.id.imagebutton_right);
        this.imgLeft = (ImageButton)activity.findViewById(R.id.imagebutton_left);
        this.right = (Button)activity.findViewById(R.id.button_right);
        this.left = (Button)activity.findViewById(R.id.button_left);
        this.imgRight.setVisibility(View.GONE);
        this.imgLeft.setVisibility(View.GONE);
        this.right.setVisibility(View.GONE);
        this.left.setVisibility(View.GONE);
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int resId) {
        this.title.setText(resId);
    }

    public void setTitle(int resId, int color) {
        this.title.setText(resId);
        this.title.setTextColor(color);
    }

    public void setTitle(CharSequence text, int color) {
        this.title.setText(text);
        this.title.setTextColor(color);
    }

    public void setLeftButton(CharSequence text) {
        this.left.setVisibility(View.VISIBLE);
        this.left.setText(text);
        this.left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(LayoutTop.this.onLeft != null) {
                    LayoutTop.this.onLeft.onClick();
                }

            }
        });
    }

    public void setLeftButton(int resId) {
        this.left.setVisibility(View.VISIBLE);
        this.left.setText(resId);
        this.left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(LayoutTop.this.onLeft != null) {
                    LayoutTop.this.onLeft.onClick();
                }

            }
        });
    }

    public void setRightButton(CharSequence text) {
        this.right.setVisibility(View.VISIBLE);
        this.right.setText(text);
        this.right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(LayoutTop.this.onRight != null) {
                    LayoutTop.this.onRight.onClick();
                }

            }
        });
    }

    public void setRightButton(int resId) {
        this.right.setVisibility(View.VISIBLE);
        this.right.setText(resId);
        this.right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(LayoutTop.this.onRight != null) {
                    LayoutTop.this.onRight.onClick();
                }

            }
        });
    }

    public void setImagerightButton(int resId) {
        this.imgRight.setVisibility(View.VISIBLE);
        this.imgRight.setImageResource(resId);
        this.imgRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(LayoutTop.this.onRight != null) {
                    LayoutTop.this.onRight.onClick();
                }

            }
        });
    }

    public void setImageleftButton(int resId) {
        this.imgLeft.setVisibility(View.VISIBLE);
        this.imgLeft.setImageResource(resId);
        this.imgLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(LayoutTop.this.onLeft != null) {
                    LayoutTop.this.onLeft.onClick();
                }

            }
        });
    }

    public void setOnLeftListener(LayoutTop.OnLeftListener leftListener) {
        this.onLeft = leftListener;
    }

    public void setOnRightListener(LayoutTop.OnRightListener rightListener) {
        this.onRight = rightListener;
    }

    public interface OnRightListener {
        void onClick();
    }

    public interface OnLeftListener {
        void onClick();
    }
}
