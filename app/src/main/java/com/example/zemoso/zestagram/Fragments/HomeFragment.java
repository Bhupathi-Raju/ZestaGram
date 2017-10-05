package com.example.zemoso.zestagram.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.zemoso.zestagram.Adapters.FeedAdapter;
import com.example.zemoso.zestagram.Adapters.StoriesAdapter;
import com.example.zemoso.zestagram.Interfaces.StoryFeedInterface;
import com.example.zemoso.zestagram.Java_beans.FeedInfo;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.utils.ZestaGramApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements StoryFeedInterface {

    //region variables
    private RecyclerView recyclerView,storyView;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    private FeedAdapter feedAdapter;
    private JSONArray value;
    FeedInfo feedInfo = new FeedInfo();
    private StoriesAdapter storiesAdapter;
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
        downloadData();
        recyclerView = view.findViewById(R.id.container);
        storiesAdapter = new StoriesAdapter(getContext(),value,feedInfo);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        feedAdapter = new FeedAdapter(getContext(),this,value,feedInfo);
        recyclerView.setAdapter(feedAdapter);
    }
    //endregion

    //region Server Interaction
    private void downloadData()
    {
        Log.e("response","preparing download");
        String url = "https://api.cognitive.microsoft.com/bing/v5.0/images/search?q=nature&count=10&offset=0&mkt=en-us&safeSearch=Moderate";
        final StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            value = new JSONArray(object.getString("value"));
                            feedInfo.setArray(value);
                            storiesAdapter.notifyDataSetChanged();
                            feedAdapter.notifyDataSetChanged();
                            Log.e("response",new JSONObject(value.getJSONObject(0).getString("thumbnail")).getString("width"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("q","nature");
                params.put("mkt","en-us");
                return params;
            }
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("Ocp-Apim-Trace", "true");
                params.put("Ocp-Apim-Subscription-Key", "2660f55bf57c4b68bc3d8fa3d9a4e3bb ");
                return params;
            }
        };

        Log.e("request",request.toString());
        ZestaGramApplication.getInstance().addToRequestQueue(request);
    }
  //endregion

    //region Interface Methods
    @Override
    public boolean setStoryAdapter(RecyclerView storiesRecyclerview) {
        if(storyView == null || ! storyView.equals(storiesRecyclerview)) {
            storiesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            storiesRecyclerview.setAdapter(storiesAdapter);
            return true;
        }
        else
            return false;
    }
    //endregion

}
