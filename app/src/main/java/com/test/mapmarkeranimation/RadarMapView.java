package com.test.mapmarkeranimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

public class RadarMapView extends MapView implements OnMapReadyCallback {

    private OnMapReadyCallback mMapReadyCallback;
    private GoogleMap mGoogleMap;
    private Marker mMarker;
    private Paint mPaintRadar;

    public RadarMapView(@NonNull Context context) {
        super(context);
        init();
    }

    public RadarMapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarMapView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RadarMapView(@NonNull Context context, @Nullable GoogleMapOptions options) {
        super(context, options);
        init();
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        drawRadarOverTheMap(canvas);
        canvas.restore();
    }

    private void drawRadarOverTheMap(Canvas canvas) {
        if (mGoogleMap == null) {
            return;
        }

        final float centerX = getX() + getWidth() / 2;
        final float centerY = getY() + getHeight() / 2;

        canvas.drawCircle(centerX, centerY, 150, mPaintRadar);
        canvas.drawCircle(centerX, centerY, 300, mPaintRadar);
        canvas.drawCircle(centerX, centerY, 450, mPaintRadar);
    }

    private void init() {
        setWillNotDraw(false);

        mPaintRadar = new Paint();
        mPaintRadar.setColor(Color.GREEN);
        mPaintRadar.setStyle(Paint.Style.STROKE);
        mPaintRadar.setStrokeWidth(10);
    }

    @Override
    public void getMapAsync(OnMapReadyCallback callback) {
        mMapReadyCallback = callback;
        super.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                invalidate();
            }
        });
        if (mMapReadyCallback != null) {
            mMapReadyCallback.onMapReady(googleMap);
        }
    }

}
