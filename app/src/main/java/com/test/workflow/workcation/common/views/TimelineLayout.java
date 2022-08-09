package com.test.workflow.workcation.common.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.test.starblinkanimation.R;

import butterknife.ButterKnife;

public class TimelineLayout extends LinearLayout {

    public TimelineLayout(final Context context) {
        this(context, null, 0);
    }

    public TimelineLayout(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimelineLayout(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, com.test.starblinkanimation.R.layout.layout_timeline, this);
    }
}
