package com.example.zemoso.zestagram.Activities;

import android.os.Bundle;

import com.example.zemoso.zestagram.Fragments.CameraFragment;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.utils.SwipeActivityClass;

public class CameraActivity extends SwipeActivityClass {

    private static final String TAG = CameraActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, CameraFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected void onSwipeRight() {

    }

    @Override
    protected void onSwipeLeft() {
         finish();
    }
}
