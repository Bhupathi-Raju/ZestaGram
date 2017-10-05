package com.example.zemoso.zestagram.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.zemoso.zestagram.Fragments.HomeFragment;
import com.example.zemoso.zestagram.Fragments.SearchFragment;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.utils.BottomNavigationBar;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity {

    //region variables
     private static final String TAG = HomeActivity.class.getSimpleName();
     private Context mContext;
    //endregion

    //region overrided methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        BottomNavigationBar bottomNavigationBar = new BottomNavigationBar();
        bottomNavigationBar.setupBottomNavigationView((BottomNavigationViewEx) findViewById(R.id.bottom_nav_bar));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.top_bar_menu);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = HomeFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer,fragment).addToBackStack("home").commit();
    }
    //endregion

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav_bar);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.ic_search:
                        Log.d(TAG,"search");
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        Fragment fragment = SearchFragment.newInstance();
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).addToBackStack("search").commit();
                        return true;
                    case R.id.ic_house:
                        getSupportFragmentManager().popBackStack("home",0);
                    default:
                        return true;
                }
            }
        });
    }

    //region privateMethods
    //endregion

}
