package com.test.viewpageindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.viewpager2.widget.ViewPager2;

import com.test.MainActivity;
import com.test.starblinkanimation.R;

public class MyIndicatorView extends LinearLayout {
    private int dotSize;
    private int dotColor;
    private int textFontSize;
    private int textFontColor;
    private int textBackground;
    private Context context;
    private ViewPager2 viewPager2;

    public MyIndicatorView(Context context) {
        super(context);
    }

    public MyIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MyIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyIndicatorViewAttrs);
        dotColor = a.getInt(R.styleable.MyIndicatorViewAttrs_dotColor, Color.GRAY);
        dotSize = a.getInt(R.styleable.MyIndicatorViewAttrs_dotSize, 15);
        textFontSize = a.getInt(R.styleable.MyIndicatorViewAttrs_textFontSize, 10);
        textFontColor = a.getInt(R.styleable.MyIndicatorViewAttrs_textFontColor, Color.WHITE);
        textBackground = a.getInt(R.styleable.MyIndicatorViewAttrs_textBackgrounds, Color.GRAY);
        a.recycle();
    }


    private View GetDotView(boolean showText) {
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(40);
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.GRAY);

        TextView vv = new TextView(context);
        if (showText) {
            vv.setBackground(shape);
            vv.setTextColor(textFontColor);
            vv.setTextSize(textFontSize);
            vv.setText(viewPager2.getCurrentItem() + "/" + viewPager2.getAdapter().getItemCount());
            vv.setPadding(20, 0, 20, 0);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10, 10, 10, 10);
            vv.setLayoutParams(lp);
        } else {

            vv.setTextColor(textFontColor);
            vv.setTextSize(textFontSize);
            vv.setBackground(shape);
            LayoutParams lp = new LayoutParams(dotSize, dotSize);
            lp.setMargins(10, 10, 10, 10);
            vv.setLayoutParams(lp);
        }
        return vv;

    }

    public void SetViewPager(ViewPager2 viewPager2) {
        this.viewPager2 = viewPager2;
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);


            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                try {
                    for (int i = 0; i < getChildCount(); i++) {
                        TextView vv = (TextView) getChildAt(i);
                        if (position == i) {
                            vv.setText(viewPager2.getCurrentItem() + "/" + viewPager2.getAdapter().getItemCount());
                            vv.setPadding(20, 0, 20, 0);
                            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            lp.setMargins(10, 10, 10, 10);
                            vv.setLayoutParams(lp);
                        } else {
                            vv.setText("");
                            LayoutParams lp = new LayoutParams(dotSize, dotSize);
                            lp.setMargins(10, 10, 10, 10);
                            vv.setLayoutParams(lp);

                        }
                    }
                    Animation animShake = AnimationUtils.loadAnimation(context, R.anim.pulse);
                    getChildAt(position).startAnimation(animShake);

                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                //UpdateView(viewPager2.getCurrentItem());
            }
        });
        UpdateView(0);
    }

    public void UpdateView(int position) {
        for (int i = 0; i < viewPager2.getAdapter().getItemCount() - 1; i++) {
            if (position == i) {
                addView(GetDotView(true));
            } else {
                addView(GetDotView(false));
            }
        }
        Animation animShake = AnimationUtils.loadAnimation(context, R.anim.pulse);
        getChildAt(position).startAnimation(animShake);
    }

}
