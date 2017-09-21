package com.example.zemoso.zestagram.Home.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zemoso.zestagram.Home.Adapter.FeedAdapter;
import com.example.zemoso.zestagram.Home.Model.FeedInfo;
import com.example.zemoso.zestagram.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    private FeedAdapter feedAdapter;
    public List<FeedInfo> feedInfos = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

    private void PopulateDate()
    {
        FeedInfo feedInfo = new FeedInfo();
        feedInfo.setImageUrl("https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg");
        feedInfo.setContactName("Nature");
        feedInfos.add(feedInfo);
        FeedInfo feedInfo1 = new FeedInfo();
        feedInfo1.setImageUrl("https://cdn.pixabay.com/photo/2017/01/06/19/15/soap-bubble-1958650_960_720.jpg");
        feedInfo1.setContactName("Nature");
        feedInfos.add(feedInfo1);
        FeedInfo feedInfo2 = new FeedInfo();
        feedInfo2.setImageUrl("https://wallpaperbrowse.com/media/images/4237670-images.jpg");
        feedInfo2.setContactName("Nature");
        feedInfos.add(feedInfo2);
        FeedInfo feedInfo3 = new FeedInfo();
        feedInfo3.setImageUrl("https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg");
        feedInfo3.setContactName("Nature");
        feedInfos.add(feedInfo3);
    }

}
