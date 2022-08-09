package com.test.rippleActivityStart;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.test.starblinkanimation.R;

//Create By Santosh
public class RadiusOverlayView extends View {
    public interface AnimationEvent {
        public void OnStart();
        public void OnComplete();
    }

    private AnimationEvent event;
    final Handler handler = new Handler();
    private int radius = 10;
    private int backgroundColor = Color.WHITE;

    public RadiusOverlayView(Context context) {
        super(context);
    }

    public RadiusOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RadiusOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TransparentCardView);
        backgroundColor = a.getInt(R.styleable.TransparentCardView_backgroundColor, Color.WHITE);
        radius = a.getInt(R.styleable.TransparentCardView_radiusDefault, 10);
        a.recycle();


    }

    public void Start(AnimationEvent ent) {
        this.event = ent;
        handler.postDelayed(new Runnable() {
            public void run() {
                Log.e("calling", "handler");
                radius = radius + 40;
                if (radius < (getHeight() / 2 + 200)) {
                    handler.postDelayed(this, 10);
                    invalidate();
                } else {
                    event.OnComplete();
                }
            }
        }, 10);
        this.event.OnStart();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF outerRectangle = new RectF(0, 0, getWidth(), getHeight());
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); // Anti alias allows for smooth corners
        paint.setColor(backgroundColor); // This is the color of your activity background
        canvas.drawRect(outerRectangle, paint);
        paint.setColor(Color.TRANSPARENT); // An obvious color to help debugging
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); // A out B http://en.wikipedia.org/wiki/File:Alpha_compositing.svg
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }


    @Override
    public boolean isInEditMode() {
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }
}
