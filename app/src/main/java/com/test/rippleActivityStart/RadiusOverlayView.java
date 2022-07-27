package com.test.rippleActivityStart;


import android.annotation.TargetApi;
import android.content.Context;
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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * @author Victor Kosenko
 */
public class RadiusOverlayView extends View {
    public interface AnimationEvent {
        public void OnStart();

        public void OnComplete();
    }

    private Bitmap windowFrame;
    private AnimationEvent event;
    final Handler handler = new Handler();
    int Radius = 10; // 1000 milliseconds == 1 second

    public RadiusOverlayView(Context context) {
        super(context);

    }

    public RadiusOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public RadiusOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void Start(AnimationEvent ent) {
        this.event = ent;
        handler.postDelayed(new Runnable() {
            public void run() {
                Radius = Radius + 20;
                if (Radius < (getHeight() / 2 + 200)) {
                    handler.postDelayed(this, 10);
                    invalidate();
                } else {
                    event.OnComplete();
                    windowFrame.recycle();
                    windowFrame = null;
                }
            }
        }, 10);
        this.event.OnStart();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        createWindowFrame();
        canvas.drawBitmap(windowFrame, 0, 0, null);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }


    protected void createWindowFrame() {
        windowFrame = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(windowFrame);
        RectF outerRectangle = new RectF(0, 0, getWidth(), getHeight());
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); // Anti alias allows for smooth corners
        paint.setColor(Color.WHITE); // This is the color of your activity background
        paint.setAlpha(220);
        canvas.drawRect(outerRectangle, paint);

        paint.setColor(Color.TRANSPARENT); // An obvious color to help debugging
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)); // A out B http://en.wikipedia.org/wiki/File:Alpha_compositing.svg
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float radius = Radius;// Math.min(getWidth(), getHeight()) / 2 - getResources().getDimensionPixelSize(R.dimen.view_margin_small2);
        canvas.drawCircle(centerX, centerY, radius, paint);


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
