package com.example.zemoso.zestagram.home.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.zemoso.zestagram.home.Adapter.FeedAdapter;
import com.example.zemoso.zestagram.home.Adapter.StoriesAdapter;
import com.example.zemoso.zestagram.home.Model.FeedInfo;
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
        downloadData();
        ScrollView scrollView = view.findViewById(R.id.scrollviewgroup);
        scrollView.setSmoothScrollingEnabled(true);
        storiesRecyclerview = view.findViewById(R.id.stories);
        storiesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        storiesAdapter = new StoriesAdapter(getContext());
        storiesRecyclerview.setAdapter(storiesAdapter);
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
    }

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
                            JSONArray array = new JSONArray(object.getString("queryExpansions"));
                            JSONArray value = new JSONArray(object.getString("value"));
                            storiesAdapter.setArray(array);
                            feedAdapter.setArray(value);
                            Log.e("response",new JSONObject(array.getJSONObject(0).getString("thumbnail")).getString("thumbnailUrl"));
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

}
