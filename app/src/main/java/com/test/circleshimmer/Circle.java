package com.test.circleshimmer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

//Create By Santosh
public class Circle {
    private float Radius = 0;
    private int Alpha = 255;
    Circle(int radius) {
        this.Radius = radius;
    }
    public void Update(int mRadius) {
        Radius = Radius + 1.5f;
        Alpha = Alpha - 5;
        if (Alpha <= 0) {
            Alpha = 255;
            Radius = 0;
        }
    }
    public void Draw(Canvas canvas, float centerX, float centerY, Paint mPaintRadar, Paint mPaintRadarBorder) {
        mPaintRadar.setAlpha(Alpha);
        canvas.drawCircle(centerX, centerY, Radius, mPaintRadar);
        canvas.drawCircle(centerX, centerY, Radius, mPaintRadarBorder);

    }
}
