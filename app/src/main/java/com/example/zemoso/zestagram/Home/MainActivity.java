package com.example.zemoso.zestagram.Home;

import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.zemoso.zestagram.Home.Adapter.FeedAdapter;
import com.example.zemoso.zestagram.Model.FeedInfo;
import com.example.zemoso.zestagram.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     private RecyclerView recyclerView;
     private FeedAdapter feedAdapter;
     public List<FeedInfo> feedInfos = new ArrayList<>();
     LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
     private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.top_bar_menu);
        recyclerView = (RecyclerView) findViewById(R.id.container);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        PopulateDate();
        feedAdapter = new FeedAdapter(this);
        feedAdapter.addList(feedInfos);
        recyclerView.setAdapter(feedAdapter);
    }



    private void setupBottomNavigationView()
    {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav_bar);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }
    private void PopulateDate()
    {
        FeedInfo feedInfo = new FeedInfo();
        feedInfo.setImageUrl("https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg");
        feedInfo.setContactName("Nature");
        feedInfos.add(feedInfo);
        FeedInfo feedInfo1 = new FeedInfo();
        feedInfo.setImageUrl("https://cdn.pixabay.com/photo/2017/01/06/19/15/soap-bubble-1958650_960_720.jpg");
        feedInfo.setContactName("Nature");
        feedInfos.add(feedInfo1);
        FeedInfo feedInfo2 = new FeedInfo();
        feedInfo.setImageUrl("https://wallpaperbrowse.com/media/images/4237670-images.jpg");
        feedInfo.setContactName("Nature");
        feedInfos.add(feedInfo2);
        FeedInfo feedInfo3 = new FeedInfo();
        feedInfo.setImageUrl("https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg");
        feedInfo.setContactName("Nature");
        feedInfos.add(feedInfo3);
    }
}
