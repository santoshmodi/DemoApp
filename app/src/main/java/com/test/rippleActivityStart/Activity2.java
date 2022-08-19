package com.test.rippleActivityStart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.test.rippleActivityStart.maps.Place;
//import com.test.rippleActivityStart.maps.PulseOverlayLayout;
import com.test.starblinkanimation.R;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity implements OnMapReadyCallback {
    RadiusOverlayView overlayview;
//    PulseOverlayLayout mapOverlayLayout;
    List<Place> bPlaces = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.test.starblinkanimation.R.layout.activity_2);

        overlayview = findViewById(R.id.overlayview);
//        mapOverlayLayout = findViewById(R.id.mapOverlayLayout);

        Place place = new Place();
        place.setLat(25);
        place.setLng(75);
        bPlaces.add(place);

        place = new Place();
        place.setLat(26);
        place.setLng(75);
        bPlaces.add(place);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
//        mapOverlayLayout.setupMap(googleMap);
        provideLatLngBoundsForAllPlaces();

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

    public void provideLatLngBoundsForAllPlaces() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Place place : bPlaces) {
            builder.include(new LatLng(place.getLat(), place.getLng()));
        }
        LatLngBounds latLngBounds = builder.build();

//        mapOverlayLayout.moveCamera(latLngBounds);
//        mapOverlayLayout.setOnCameraIdleListener(() -> {
//            for (int i = 0; i < bPlaces.size(); i++) {
//                mapOverlayLayout.createAndShowMarker(i, bPlaces.get(i).getLatLng());
//            }
//            mapOverlayLayout.setOnCameraIdleListener(null);
//        });
//        mapOverlayLayout.setOnCameraMoveListener(mapOverlayLayout::refresh);
    }
}