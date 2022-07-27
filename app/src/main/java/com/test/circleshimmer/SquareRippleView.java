package com.test.circleshimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.starblinkanimation.R;

import java.util.ArrayList;
import java.util.List;

public class SquareRippleView extends View {

    private static final int DEFAULT_RIPPLE_COUNT = 4;
    private static final int DEFAULT_DURATION_TIME = 40;

    private int rippleColor;
    private float rippleStrokeWidth;


    final Handler handler = new Handler();// 1000 milliseconds == 1 second
    private Paint mPaintRadar;
    private Paint mPaintRadarBorder;
    private List<Square> item = new ArrayList<>();
    private Runnable runnable = new Runnable() {
        public void run() {
            for (Square cc : item) {
                cc.Update(getWidth() / 2);
            }
            invalidate();
            handler.postDelayed(this, DEFAULT_DURATION_TIME);
        }
    };

    public SquareRippleView(Context context) {
        super(context);
    }

    public SquareRippleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SquareRippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SquareRippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
//        rippleRadius = typedArray.getDimension(R.styleable.RippleBackground_rb_radius, getResources().getDimension(R.dimen.rippleRadius));
//        rippleDurationTime = typedArray.getInt(R.styleable.RippleBackground_rb_duration, DEFAULT_DURATION_TIME);
//        rippleAmount = typedArray.getInt(R.styleable.RippleBackground_rb_rippleAmount, DEFAULT_RIPPLE_COUNT);
//        rippleScale = typedArray.getFloat(R.styleable.RippleBackground_rb_scale, DEFAULT_SCALE);
//        rippleType = typedArray.getInt(R.styleable.RippleBackground_rb_type, DEFAULT_FILL_TYPE);
        typedArray.recycle();
//        rippleDelay = rippleDurationTime / rippleAmount;
        mPaintRadar = new Paint();
        mPaintRadar.setColor(rippleColor);
        mPaintRadar.setStyle(Paint.Style.FILL);

        mPaintRadarBorder = new Paint();
        mPaintRadarBorder.setColor(rippleColor);
        mPaintRadarBorder.setStyle(Paint.Style.STROKE);
        mPaintRadarBorder.setStrokeWidth(rippleStrokeWidth);
//        item.add(new Circle(0));
        handler.postDelayed(new Runnable() {
            public void run() {
                item.add(new Square(0));
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
        for (Square cc : item) {
            cc.Draw(canvas, centerX, centerY, mPaintRadar, mPaintRadarBorder);
        }

    }
}
