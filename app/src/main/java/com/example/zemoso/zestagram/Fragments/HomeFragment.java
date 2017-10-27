package com.example.zemoso.zestagram.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.zemoso.zestagram.Activities.CameraActivity;
import com.example.zemoso.zestagram.Adapters.FeedAdapter;
import com.example.zemoso.zestagram.Adapters.StoriesAdapter;
import com.example.zemoso.zestagram.Interfaces.StoryFeedInterface;
import com.example.zemoso.zestagram.Interfaces.setObjectInterface;
import com.example.zemoso.zestagram.Java_beans.FeedInfo;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.ThemeSelector;
import com.example.zemoso.zestagram.utils.DownloadFromServer;

import org.json.JSONArray;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements StoryFeedInterface {

    //region variables
    private RecyclerView recyclerView,storyView;
    private FeedAdapter feedAdapter;
    private JSONArray value;
    FeedInfo feedInfo = new FeedInfo();
    private StoriesAdapter storiesAdapter;
    private ThemeSelector selector = new ThemeSelector();
    private Toolbar toolbar;
    private ImageButton camera;
    private ImageButton settings;
    //endregion

    //region constrcutor
    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance()
    {
        return new HomeFragment();
    }
    //endregion

    //region LifeCycle Methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        camera = view.findViewById(R.id.camera);
        settings = view.findViewById(R.id.send);
        toolbar = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.story_recycler);
        storiesAdapter = new StoriesAdapter(getContext(),value,feedInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedAdapter = new FeedAdapter(getActivity(),this,value,feedInfo,selector);
        DownloadFromServer downloadFromServer = new DownloadFromServer(feedAdapter,storiesAdapter,feedInfo);
        downloadFromServer.downloadData();
        recyclerView.setAdapter(feedAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                Fragment settingsFragment = new SettingsFragment();
                setObjectInterface setObjectInterface = (setObjectInterface) settingsFragment;
                setObjectInterface.setObject(selector);
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer,settingsFragment).addToBackStack("settings").commit();
            }
        });

    }

    //endregion


    //region Interface Methods
    @Override
    public boolean setStoryAdapter(RecyclerView storiesRecyclerview) {
        if(storyView == null || ! storyView.equals(storiesRecyclerview)) {
            storyView = storiesRecyclerview;
            storyView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            storyView.setAdapter(storiesAdapter);
            return true;
        }
        else
            return false;
    }
    //endregion

}
