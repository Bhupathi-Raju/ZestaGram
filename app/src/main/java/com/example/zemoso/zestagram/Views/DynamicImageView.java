package com.example.zemoso.zestagram.Views;

import android.content.*;
import android.util.*;

public class DynamicImageView extends android.support.v7.widget.AppCompatImageView {

    private float whRatio = 0;

    public DynamicImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicImageView(Context context) {
        super(context);
    }

    public void setRatio(float ratio) {
        whRatio = ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (whRatio != 0) {
            int width = getMeasuredWidth();
            int height = (int)(whRatio * width);
            setMeasuredDimension(width, height);
        }
    }
}