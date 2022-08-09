package com.test.workflow.workcation.screens.main;

import android.graphics.Bitmap;
import com.test.workflow.workcation.common.maps.MapBitmapCache;
import com.test.workflow.workcation.common.model.BaliDataProvider;
import com.test.workflow.workcation.common.mvp.MvpPresenterImpl;

public class MainPresenterImpl extends MvpPresenterImpl<MainView> implements MainPresenter {
    @Override
    public void saveBitmap(final Bitmap bitmap) {
        MapBitmapCache.instance().putBitmap(bitmap);
    }

    @Override
    public void provideMapLatLngBounds() {
        getView().setMapLatLngBounds(BaliDataProvider.instance().provideLatLngBoundsForAllPlaces());
    }
}
