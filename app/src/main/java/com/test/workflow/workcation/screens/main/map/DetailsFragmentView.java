package com.test.workflow.workcation.screens.main.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.test.workflow.workcation.common.model.Place;
import com.test.workflow.workcation.common.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

public interface DetailsFragmentView extends MvpView {
    void drawPolylinesOnMap(ArrayList<LatLng> decode);

    void provideBaliData(List<Place> places);

    void onBackPressedWithScene(LatLngBounds latLngBounds);

    void moveMapAndAddMaker(LatLngBounds latLngBounds);

    void updateMapZoomAndRegion(LatLng northeastLatLng, LatLng southwestLatLng);
}
