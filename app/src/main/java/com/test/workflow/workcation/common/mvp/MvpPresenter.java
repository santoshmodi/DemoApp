package com.test.workflow.workcation.common.mvp;

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);
    void detachView();
    boolean isViewAttached();
}
