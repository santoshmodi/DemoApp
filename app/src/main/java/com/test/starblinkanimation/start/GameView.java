package com.test.starblinkanimation.start;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.test.starblinkanimation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rushd on 7/5/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private List<StartSprite> characterSprite = new ArrayList<>();
    private Ball ball;

    public GameView(Context context) {
        super(context);
        ball = new Ball(100, 100, 50, Color.GREEN);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        for (int i = 0; i < 20; i++) {
            characterSprite.add(new StartSprite(BitmapFactory.decodeResource(getResources(), R.drawable.marker)));
        }
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        for (StartSprite it : characterSprite) {
            it.update();
        }


    }

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        ball.move(canvas);
        canvas.drawOval(ball.oval, ball.paint);
        if (canvas != null) {
            for (StartSprite it : characterSprite) {
                it.draw(canvas);
            }


        }
    }


}