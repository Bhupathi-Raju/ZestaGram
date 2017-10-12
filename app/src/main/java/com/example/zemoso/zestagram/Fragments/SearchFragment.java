package com.example.zemoso.zestagram.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.zemoso.zestagram.Adapters.FeedAdapter;
import com.example.zemoso.zestagram.Adapters.SearchAdapter;
import com.example.zemoso.zestagram.Adapters.StoriesAdapter;
import com.example.zemoso.zestagram.Interfaces.StoryFeedInterface;
import com.example.zemoso.zestagram.Java_beans.FeedInfo;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.utils.DownloadFromServer;

import org.json.JSONArray;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements StoryFeedInterface {

    private FeedInfo feedInfo = new FeedInfo();
    private RecyclerView recyclerView,storyView;
    private JSONArray value;
    private StoriesAdapter storiesAdapter;
    private SearchAdapter searchAdapter;
    public SearchFragment() {
        // Required empty public constructor
    }
    public static SearchFragment newInstance()
    {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SearchView searchView = view.findViewById(R.id.search);
        searchView.onActionViewExpanded();
        searchView.setQueryHint("Search Bing Images");
        searchView.setIconifiedByDefault(false);
        recyclerView = view.findViewById(R.id.search_recycler);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        searchAdapter = new SearchAdapter(getContext(),this,value,feedInfo);
        recyclerView.setAdapter(searchAdapter);
        storiesAdapter = new StoriesAdapter(getContext(),value,feedInfo);
        final DownloadFromServer downloadFromServer = new DownloadFromServer(searchAdapter,storiesAdapter,feedInfo);
        downloadFromServer.downloadData();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query",query);
                downloadFromServer.setQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("query",newText);
                downloadFromServer.setQuery(newText);
                return false;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public boolean setStoryAdapter(RecyclerView storiesRecyclerView) {
        if(storyView == null || ! storyView.equals(storiesRecyclerView)) {
            storyView = storiesRecyclerView;
            storyView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            storyView.setAdapter(storiesAdapter);
            return true;
        }
        else
            return false;
    }
}
