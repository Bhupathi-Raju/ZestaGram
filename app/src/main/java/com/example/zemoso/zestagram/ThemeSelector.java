package com.example.zemoso.zestagram;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zemoso on 25/10/17.
 */

public class ThemeSelector extends BaseObservable  {
    private int color;
    private String imageUrl;
    private String userName;
    public ThemeSelector()
    {
    }

    @Bindable
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        notifyPropertyChanged(BR.color);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView textView, int color)
    {
        Log.d("bind",String.valueOf(color));
        switch (color){
            case 1:
                textView.setTextColor(textView.getContext().getColor(R.color.white));
                break;
            case 2:
                textView.setTextColor(textView.getContext().getColor(R.color.black));
                break;
            case 3:
                textView.setTextColor(textView.getContext().getColor(R.color.red));
                break;
                default:
                    textView.setTextColor(textView.getContext().getColor(R.color.black));

        }
       // textView.setTextColor(0x4f3636);
    }
    @BindingAdapter("android:background")
    public static void setBackground(RelativeLayout relativeLayout, int color)
    {
        Log.d("bind background",String.valueOf(color));
        switch (color){
            case 1:
                relativeLayout.setBackground(relativeLayout.getContext().getResources().getDrawable(R.color.black));
                break;
            case 2:
                relativeLayout.setBackground(relativeLayout.getContext().getResources().getDrawable(R.color.white));
                break;
            case 3:
                relativeLayout.setBackground(relativeLayout.getContext().getResources().getDrawable(R.color.blue));
                break;
                default:
                    relativeLayout.setBackground(relativeLayout.getContext().getResources().getDrawable(R.color.white));
        }
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
