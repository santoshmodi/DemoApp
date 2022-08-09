package com.test.workflow.workcation.screens.main;

import android.graphics.Bitmap;

import com.test.workflow.workcation.common.mvp.MvpPresenter;

public interface MainPresenter extends MvpPresenter<MainView> {
    void saveBitmap(Bitmap googleMap);

    void provideMapLatLngBounds();
}
