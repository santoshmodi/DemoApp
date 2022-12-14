package com.test.circleshimmer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
//Create By Santosh
public class Square {
    private float Radius = 0;
    private int Alpha = 255;
    Square(int radius) {
        this.Radius = radius;
    }

    public void Update(int mRadius) {
        Radius = Radius + 1.5f;
        Alpha = Alpha - 3;
        if (Alpha <= 0) {
            Alpha = 255;
            Radius = 0;
        }
    }

    public void Draw(Canvas canvas, float centerX, float centerY, Paint mPaintRadar, Paint mPaintRadarBorder) {
        mPaintRadar.setAlpha(Alpha);
        Rect rectF = new Rect();
        rectF.set((int) (centerX - Radius), (int) (centerY - Radius), (int) (centerX + Radius), (int) (centerY + Radius));
        canvas.drawRect(rectF, mPaintRadar);
        canvas.drawRect(rectF, mPaintRadarBorder);

    }
}
