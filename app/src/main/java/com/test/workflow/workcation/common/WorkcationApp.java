package com.test.workflow.workcation.common;

import android.app.Application;

import com.test.starblinkanimation.R;
import com.test.workflow.workcation.common.model.BaliDataProvider;
import com.test.workflow.workcation.common.model.MapsApiManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class WorkcationApp extends Application {

    private static WorkcationApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        MapsApiManager.instance().initialize();
        BaliDataProvider.instance().initialize();
        initCalligraphy();
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
//                .setFontAttrId(com.test.starblinkanimation.R.attr.fontPath)
                .build()
        );
    }

    public static WorkcationApp getInstance() {
        return sInstance;
    }
}
