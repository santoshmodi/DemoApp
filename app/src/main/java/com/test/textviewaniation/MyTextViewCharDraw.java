package com.test.textviewaniation;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.test.circleshimmer.Circle;

import java.util.ArrayList;
import java.util.List;

public class MyTextViewCharDraw extends androidx.appcompat.widget.AppCompatTextView {
    final Handler handler = new Handler();// 1000 milliseconds == 1 second
    private List<String> MyStringData = new ArrayList<>();
    private int StartIndex = 0;
    private int StartDrawIndex = 0;
    private int Draw = 1; //1 for draw , 0 for erase
    private Runnable runnable = new Runnable() {
        public void run() {
            String CurrentText = getText().toString().replace("|", "");
            if (StartIndex > MyStringData.size() - 1)
                StartIndex = 0;
            String TempString = MyStringData.get(StartIndex);
            System.out.println("myHandler: here!"); // Do your work here
            if (Draw == 1) {
                CurrentText = TempString.substring(0, StartDrawIndex);
                StartDrawIndex++;
                if (StartDrawIndex > TempString.length()) {
                    Draw = 0;
                }
            } else {
                if ((CurrentText.length() - 1) >= 1) {
                    CurrentText = CurrentText.substring(0, CurrentText.length() - 1);
                } else {
                    Draw = 1;
                    StartIndex++;
                    StartDrawIndex = 0;
                }
            }
            setText(CurrentText );
            handler.postDelayed(this, 200);
        }
    };

    public MyTextViewCharDraw(Context context) {
        super(context);

    }

    public MyTextViewCharDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextViewCharDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(runnable);
    }

    public void StartAnimation() {
        MyStringData.add("^444FRDE");
        MyStringData.add("^SDSEDSF");
        MyStringData.add("^5SDF4SDF");
        MyStringData.add("^TESTAPP");
        if (MyStringData.size() > 0) {
            handler.postDelayed
                    (runnable, 200);
        }
    }
}
