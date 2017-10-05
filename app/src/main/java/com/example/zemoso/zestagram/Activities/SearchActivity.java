package com.example.zemoso.zestagram.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.utils.BottomNavigationBar;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BottomNavigationBar bottomNavigationBar = new BottomNavigationBar();
        bottomNavigationBar.setupBottomNavigationView((BottomNavigationViewEx)findViewById(R.id.bottom_nav_bar));
    }
}
