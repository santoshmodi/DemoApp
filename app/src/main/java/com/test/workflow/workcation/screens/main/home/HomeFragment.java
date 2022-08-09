package com.test.workflow.workcation.screens.main.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.starblinkanimation.R;
import com.test.workflow.workcation.common.mvp.MvpFragment;
import com.test.workflow.workcation.screens.main.MainActivity;

public class HomeFragment extends MvpFragment<HomeView, HomePresenter> implements HomeView {
    public static final String TAG = HomeFragment.class.getSimpleName();

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    public void onBackPressed() {
        ((MainActivity) getActivity()).superOnBackPressed();
    }

    @Override
    public int getLayout() {
        return com.test.starblinkanimation.R.layout.fragment_home;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
