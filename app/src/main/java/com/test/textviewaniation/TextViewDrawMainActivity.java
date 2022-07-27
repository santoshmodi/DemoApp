package com.test.textviewaniation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.test.starblinkanimation.R;

public class TextViewDrawMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_draw_main);
        MyTextViewCharDraw myTextViewCharDraw = findViewById(R.id.textview);
        myTextViewCharDraw.StartAnimation();
    }
}