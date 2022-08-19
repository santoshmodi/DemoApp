package com.test.circleshimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.starblinkanimation.R;

import java.util.ArrayList;
import java.util.List;

//Create By Santosh
public class CircleRippleView extends View {
    private static final int DEFAULT_RIPPLE_COUNT = 1;
    private static final int DEFAULT_DURATION_TIME = 40;
    private int rippleColor;
    private float rippleStrokeWidth;
    final Handler handler = new Handler();
    private Paint mPaintRadar;
    private Paint mPaintRadarBorder;
    private List<Circle> item = new ArrayList<>();

    private Runnable runnable = new Runnable() {
        public void run() {
            for (Circle cc : item) {
                cc.Update(getWidth() / 2);
            }
            invalidate();
            handler.postDelayed(this, DEFAULT_DURATION_TIME);
        }
    };

    public CircleRippleView(Context context) {
        super(context);
    }

    public CircleRippleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleRippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CircleRippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(runnable);
    }

    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode())
            return;

        if (null == attrs) {
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleBackground);
        rippleColor = typedArray.getColor(R.styleable.RippleBackground_rb_color, getResources().getColor(R.color.black));
        rippleStrokeWidth = typedArray.getDimension(R.styleable.RippleBackground_rb_strokeWidth, getResources().getDimension(R.dimen.rippleStrokeWidth));
        typedArray.recycle();
        mPaintRadar = new Paint();
        mPaintRadar.setColor(rippleColor);
        mPaintRadar.setStyle(Paint.Style.FILL);
        mPaintRadarBorder = new Paint();
        mPaintRadarBorder.setColor(rippleColor);
        mPaintRadarBorder.setStyle(Paint.Style.STROKE);
        mPaintRadarBorder.setStrokeWidth(rippleStrokeWidth);
        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("myHandler: here!"); // Do your work here
                item.add(new Circle(0));
                if (item.size() < DEFAULT_RIPPLE_COUNT) {
                    handler.postDelayed(this, 500);
                }
            }
        }, 500);
        handler.postDelayed(
                runnable
                , DEFAULT_DURATION_TIME);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final float centerX = getWidth() / 2;
        final float centerY = getHeight() / 2;
        for (Circle cc : item) {
            cc.Draw(canvas, centerX, centerY, mPaintRadar, mPaintRadarBorder);
        }

    }
}
