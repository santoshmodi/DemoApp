package com.test.workflow.workcation.screens.main;

import android.os.Bundle;
import butterknife.OnClick;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.test.starblinkanimation.R;
import com.test.workflow.workcation.common.maps.MapsUtil;
import com.test.workflow.workcation.common.mvp.MvpActivity;
import com.test.workflow.workcation.common.mvp.MvpFragment;
import com.test.workflow.workcation.common.mvp.MvpPresenter;
import com.test.workflow.workcation.common.mvp.MvpView;
import com.test.workflow.workcation.screens.main.home.HomeFragment;
import com.test.workflow.workcation.screens.main.map.DetailsFragment;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, OnMapReadyCallback {

    SupportMapFragment mapFragment;
    private LatLngBounds mapLatLngBounds;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main_workflow;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.provideMapLatLngBounds();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance(), HomeFragment.TAG)
                .addToBackStack(HomeFragment.TAG)
                .commit();
        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.explore)
    public void onItemExploreClicked() {
        if(getSupportFragmentManager().findFragmentByTag(DetailsFragment.TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.container, DetailsFragment.newInstance(this), DetailsFragment.TAG)
                    .addToBackStack(DetailsFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 1) {
            triggerFragmentBackPress(getSupportFragmentManager().getBackStackEntryCount());
        } else {
            finish();
        }
    }

    @Override
    public void setMapLatLngBounds(final LatLngBounds latLngBounds) {
        mapLatLngBounds = latLngBounds;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(
                mapLatLngBounds,
                MapsUtil.calculateWidth(getWindowManager()),
                MapsUtil.calculateHeight(getWindowManager(), getResources().getDimensionPixelSize(R.dimen.map_margin_bottom)), 150));
        googleMap.setOnMapLoadedCallback(() -> googleMap.snapshot(presenter::saveBitmap));
    }

    private void triggerFragmentBackPress(final int count) {
        ((MvpFragment<MvpView, MvpPresenter>)getSupportFragmentManager().findFragmentByTag(getSupportFragmentManager().getBackStackEntryAt(count - 1).getName())).onBackPressed();
    }

    public void superOnBackPressed() {
        super.onBackPressed();
    }
}
