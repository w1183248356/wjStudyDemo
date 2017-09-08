package com.customview;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static util.DisplayUtil.px2dip;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class CircleProgressBar extends View {
    private static final String TAG = "CircleProgressBar";

    private int mWidth;
    private int mHeight;
    //内外圆的半径
    private int mInCrlR;
    private int mOutCrlR;
    //内外圆的颜色
    private int mInColor;
    private int mOutColor;
    private int mOutPainWidth;
    private int mIntPainWidth;

    private int mPaddingTop;
    private int mPaddingLeft;
    private int mPaddingBottom;
    private int mPaddingRight;

    //当前进度
    private int mProgress;
    //总进度
    private int mAllProgress;


    private Paint mInPaint;
    private Paint mOutPaint;
    private Paint mFinishPaint;

    private ValueAnimator mAnimator;
    private float xStart;
    private float yStart;
    private float xCenter;
    private float yCenter;
    private float xEnd;
    private float yEnd;
    private float mAnimatorX;
    private float mAnimatorY;

    public void setAllProgress(int mAllProgress) {
        this.mAllProgress = mAllProgress;
    }

    public int getAllProgress() {
        return mAllProgress;
    }

    public void setProgress(int mProgress) {
        if (mAllProgress <= 0) {
            new Throwable("总进度为0，请先设置总进度");
        }
        if (mProgress < 0) {
            new Throwable("当前进度不能小于0");
        }
        this.mProgress = mProgress;
        invalidate();
    }

    public int getProgress() {
        return mProgress;
    }

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        initAtts(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initAtts(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.numview);
        mOutCrlR = typedArray.getDimensionPixelSize(R.styleable.circle_progressbar_cpbOutCrlR, px2dip(context, 120));
        mInCrlR = typedArray.getDimensionPixelSize(R.styleable.circle_progressbar_cpbInCrlR, px2dip(context, 110));
        mOutColor = typedArray.getColor(R.styleable.circle_progressbar_cpbOutCol, Color.BLUE);
        mInColor = typedArray.getColor(R.styleable.circle_progressbar_cpbinCol, Color.BLUE);
        mOutPainWidth = typedArray.getDimensionPixelSize(R.styleable.circle_progressbar_cpbOutLineWidth, 2);

        mIntPainWidth = mOutCrlR - mOutPainWidth / 2 - mInCrlR;

        //这里必须手动设置
        mAllProgress = 10;
        mProgress = 7;

        mOutPaint = new Paint();
        mOutPaint.setStyle(Paint.Style.STROKE);
        mOutPaint.setStrokeWidth(mOutPainWidth);
        mOutPaint.setDither(true);
        mOutPaint.setAntiAlias(true);
        mOutPaint.setColor(mOutColor);

        mInPaint = new Paint();
        mInPaint.setStyle(Paint.Style.STROKE);
        mInPaint.setStrokeWidth(mIntPainWidth);
        mInPaint.setDither(true);
        mInPaint.setAntiAlias(true);
        mInPaint.setColor(mInColor);

        mFinishPaint = new Paint();
        mFinishPaint.setStyle(Paint.Style.STROKE);
        mFinishPaint.setStrokeWidth(5);
        mFinishPaint.setDither(true);
        mFinishPaint.setAntiAlias(true);
        mFinishPaint.setColor(Color.WHITE);

        xStart = mOutCrlR / 2;//x取直径的1/4;
        yStart = mOutCrlR + mOutCrlR / 8;
        xCenter = mOutCrlR - mOutCrlR / 8;
        yCenter = mOutCrlR * 3 / 2;
        xEnd = mOutCrlR * 3 / 2;//直径的3/4;
        yEnd = mOutCrlR / 2;
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("linex", xStart, xCenter, xEnd);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("liney", yStart, yCenter, yEnd);
        initLineAnimator(holderX, holderY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = Math.min(widthSize, heightSize);
        } else {
            width = 2 * mOutCrlR + mOutPainWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = Math.min(widthSize, heightSize);
        } else {
            height = 2 * mOutCrlR + mOutPainWidth;
        }

        mPaddingBottom = getPaddingBottom();
        mPaddingTop = getPaddingTop();
        mPaddingRight = getPaddingRight();
        mPaddingLeft = getPaddingLeft();

        width += getPaddingLeft() + getPaddingRight();
        height += getPaddingTop() + getPaddingBottom();
        mWidth = width;
        mHeight = height;
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerWidth = mWidth / 2;
        int centerHight = mHeight / 2;
        int lineWidth = mOutPainWidth / 2;
        int intline = mIntPainWidth / 2;
        //在控件中间画外面的圆
        canvas.drawCircle(centerWidth, centerHight, mOutCrlR, mOutPaint);

        if (mAllProgress > mProgress) {
            RectF oval = new RectF(centerWidth - mOutCrlR + lineWidth + intline, centerHight + intline - mOutCrlR + lineWidth,
                    centerWidth + mOutCrlR - lineWidth - intline, centerHight + mOutCrlR - lineWidth - intline);
            if (mAllProgress > 0 && mProgress > -1) {
                int point = mProgress * 360 / mAllProgress;
                canvas.drawArc(oval, -90, point, false, mInPaint);
            }
        } else if (mAllProgress <= mProgress) {
            Paint finish = new Paint();
            finish.setStyle(Paint.Style.FILL);
            finish.setStrokeWidth(mOutPainWidth);
            finish.setDither(true);
            finish.setAntiAlias(true);
            finish.setColor(mOutColor);
            canvas.drawCircle(centerWidth, centerHight, mOutCrlR, finish);
            if (mAnimator != null && !mAnimator.isStarted() && mAnimatorX < xEnd) {
                mAnimator.start();
            }
            if(mAnimatorX < xCenter){
                Log.e(TAG, "mAnimatorX < xCenter");
                canvas.drawLine(xStart, yStart, mAnimatorX, mAnimatorY, mFinishPaint);
            } else {
                Log.e(TAG, "ELSE");
                canvas.drawLine(xStart, yStart, xCenter, yCenter - 2.5f, mFinishPaint);
                canvas.drawLine(xCenter, yCenter, mAnimatorX, mAnimatorY, mFinishPaint);
            }
        }
    }

    private static final int SCALE_ANIMATOR_DURATION = 200;

    public void initLineAnimator(PropertyValuesHolder x, PropertyValuesHolder y) {
        initLineAnimator(x, y, SCALE_ANIMATOR_DURATION);
    }

    public void initLineAnimator(PropertyValuesHolder x, PropertyValuesHolder y, long duration) {
        mAnimator = ValueAnimator.ofPropertyValuesHolder(x, y);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorX = (float) animation.getAnimatedValue("linex");
                mAnimatorY = (float) animation.getAnimatedValue("liney");
                invalidate();
            }
        });
        mAnimator.setDuration(duration);
    }

    private class LineAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {

        /**
         * 创建一个画对钩的动画
         * <p>
         *
         * @param start  开始
         * @param center 中间
         * @param end    最后
         */
        public LineAnimator(ObjectAnimator start, ObjectAnimator center, ObjectAnimator end) {
            this(start, center, end, SCALE_ANIMATOR_DURATION);
        }

        public LineAnimator(ObjectAnimator start, ObjectAnimator center, ObjectAnimator end, long duration) {
            super();

            setObjectValues(start, center, end);
            setDuration(duration);
            addUpdateListener(this);
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float value = (float) animation.getAnimatedValue();
            invalidate();
        }
    }

}
