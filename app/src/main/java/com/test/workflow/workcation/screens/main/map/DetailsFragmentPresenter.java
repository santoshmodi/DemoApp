package com.test.workflow.workcation.screens.main.map;

import com.google.android.gms.maps.model.LatLng;
import com.test.workflow.workcation.common.mvp.MvpPresenter;

public interface DetailsFragmentPresenter extends MvpPresenter<DetailsFragmentView> {

    void drawRoute(LatLng first, final int position);

    void provideBaliData();

    void onBackPressedWithScene();

    void moveMapAndAddMarker();
}
