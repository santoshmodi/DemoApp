package com.test.workflow.workcation.common.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter> extends Fragment implements MvpView {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(presenter == null)
            presenter = createPresenter();
        //noinspection unchecked
        presenter.attachView(this);
    }

    protected abstract P createPresenter();

    public abstract void onBackPressed();

    @LayoutRes
    public abstract int getLayout();

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }
}
