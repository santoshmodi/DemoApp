package com.test.rippleActivityStart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.test.starblinkanimation.R;

public class Activity2 extends AppCompatActivity implements OnMapReadyCallback {
    RadiusOverlayView overlayview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.test.starblinkanimation.R.layout.activity_2);

        overlayview = findViewById(R.id.overlayview);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        overlayview.Start(new RadiusOverlayView.AnimationEvent() {
            @Override
            public void OnStart() {

            }

            @Override
            public void OnComplete() {
                overlayview.setVisibility(View.GONE);
            }
        });
    }
}