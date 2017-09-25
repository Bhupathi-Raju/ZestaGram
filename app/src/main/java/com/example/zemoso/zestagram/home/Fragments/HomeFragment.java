package com.example.zemoso.zestagram.home.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zemoso.zestagram.home.Adapter.FeedAdapter;
import com.example.zemoso.zestagram.home.Adapter.StoriesAdapter;
import com.example.zemoso.zestagram.home.Model.FeedInfo;
import com.example.zemoso.zestagram.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //region variables
    private RecyclerView recyclerView,storiesRecyclerview;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    private FeedAdapter feedAdapter;
    private StoriesAdapter storiesAdapter;
    public List<FeedInfo> feedInfos = new ArrayList<>();
    //endregion

    //region constrcutor
    public HomeFragment() {
        // Required empty public constructor
    }
    //endregion

    //region Overrided Methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PopulateDate();
        recyclerView = view.findViewById(R.id.container);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        feedAdapter = new FeedAdapter(getContext(),feedInfos);
        recyclerView.setAdapter(feedAdapter);
    }
    //endregion

    //region DummyData Population
    private void PopulateDate()
    {
        FeedInfo feedInfo = new FeedInfo();
        feedInfo.setImageUrl("https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg");
        feedInfo.setContactName("Beach");
        feedInfos.add(feedInfo);
        FeedInfo feedInfo1 = new FeedInfo();
        feedInfo1.setImageUrl("https://cdn.pixabay.com/photo/2017/01/06/19/15/soap-bubble-1958650_960_720.jpg");
        feedInfo1.setContactName("Bubble");
        feedInfos.add(feedInfo1);
        FeedInfo feedInfo2 = new FeedInfo();
        feedInfo2.setImageUrl("https://wallpaperbrowse.com/media/images/4237670-images.jpg");
        feedInfo2.setContactName("Tiger");
        feedInfos.add(feedInfo2);
        FeedInfo feedInfo3 = new FeedInfo();
        feedInfo3.setImageUrl("https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg");
        feedInfo3.setContactName("Trees");
        feedInfos.add(feedInfo3);
        FeedInfo feedInfo4 = new FeedInfo();
        feedInfo4.setImageUrl("http://wallpoop.com/wp-content/uploads/2012/07/d-d-wallpaper-of-nature-download-wallpapers.jpg");
        feedInfo4.setContactName("walk");
        feedInfos.add(feedInfo4);
        FeedInfo feedInfo5 = new FeedInfo();
        feedInfo5.setImageUrl("https://www.bmw-yemen.com/content/dam/bmw/common/all-models/4-series/gran-coupe/2017/images-and-videos/images/BMW-4-series-gran-coupe-images-and-videos-1920x1200-11.jpg.asset.1487328156349.jpg");
        feedInfo5.setContactName("Car");
        feedInfos.add(feedInfo5);
        FeedInfo feedInfo6 = new FeedInfo();
        feedInfo6.setImageUrl("https://scontent.fhyd2-1.fna.fbcdn.net/v/t1.0-9/10409185_1675205939373829_1103690519889316987_n.jpg?oh=d7b42ac033296d8ceda618fffd298460&oe=5A47344B");
        feedInfo6.setContactName("E photo teesindhi nene");
        feedInfos.add(feedInfo6);

    }
   //endregion

}
