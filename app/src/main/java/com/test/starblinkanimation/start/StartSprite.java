package com.test.starblinkanimation.start;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class StartSprite {

    private Bitmap image;
    private int x, y;
    private int xVelocity = new Random().nextInt(20);
    private int yVelocity = new Random().nextInt(20);
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private Rect rect = new Rect();

    public StartSprite(Bitmap bmp) {
        image = bmp;
        image = Bitmap.createScaledBitmap(bmp, 100, 100, false);
        x = new Random().nextInt(500);
        y = new Random().nextInt(500);
        rect.left = x;
        rect.top = y;
        rect.right = x + 100;
        rect.bottom = y + 100;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, null, rect, null);
    }

    public void update() {

        x += xVelocity;
        y += yVelocity;
        rect.left = x;
        rect.top = y;
        rect.right = x + 100;
        rect.bottom = y + 100;
        
        if ((x > screenWidth - image.getWidth()) || (x < 0)) {
            xVelocity = xVelocity * -1;
        }
        if ((y > screenHeight - image.getHeight()) || (y < 0)) {
            yVelocity = yVelocity * -1;
        }

    }


}