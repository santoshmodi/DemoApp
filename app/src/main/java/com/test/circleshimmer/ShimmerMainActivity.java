package com.test.circleshimmer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.test.starblinkanimation.R;

public class ShimmerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shimmer_main);
//        ((RippleBackground) findViewById(R.id.content)).startRippleAnimation();
    }
}