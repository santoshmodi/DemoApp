package com.test.workflow.workcation.screens.main;

import com.google.android.gms.maps.model.LatLngBounds;
import com.test.workflow.workcation.common.mvp.MvpView;

public interface MainView extends MvpView {
    void setMapLatLngBounds(final LatLngBounds latLngBounds);
}
