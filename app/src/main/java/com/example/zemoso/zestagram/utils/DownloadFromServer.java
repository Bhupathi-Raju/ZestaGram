package com.example.zemoso.zestagram.utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.zemoso.zestagram.Adapters.FeedAdapter;
import com.example.zemoso.zestagram.Adapters.SearchAdapter;
import com.example.zemoso.zestagram.Adapters.StoriesAdapter;
import com.example.zemoso.zestagram.Java_beans.FeedInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by zemoso on 9/10/17.
 */

public class DownloadFromServer {
    private JSONArray value;
    private String query = "nature";
    private FeedInfo feedInfo;
    private RecyclerView.Adapter adapter,storiesAdapter;
    public DownloadFromServer(RecyclerView.Adapter adapter,RecyclerView.Adapter storiesAdapter,FeedInfo feedInfo)
    {
        this.feedInfo =feedInfo;
        this.adapter = adapter;
        this.storiesAdapter = storiesAdapter;
    }
    public DownloadFromServer()
    {

    }
    public void downloadData()
    {
        Log.e("response","preparing download");
        String url = String.format("https://api.cognitive.microsoft.com/bing/v5.0/images/search?q=%1$s&count=10&offset=0&mkt=en-us&safeSearch=Moderate",query);
        //String url = "https://api.cognitive.microsoft.com/bing/v5.0/images/search?q=%1$s&count=10&offset=0&mkt=en-us&safeSearch=Moderate";
        final StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            value = new JSONArray(object.getString("value"));
                            feedInfo.setArray(value);
                            Log.d("value",value.toString());
                            if(adapter instanceof FeedAdapter)
                            {
                                FeedAdapter feedAdapter = (FeedAdapter) adapter;
                                feedAdapter.notifyDataSetChanged();
                                storiesAdapter.notifyDataSetChanged();

                            }
                            if(adapter instanceof SearchAdapter)
                            {
                                SearchAdapter searchAdapter = (SearchAdapter) adapter;
                                searchAdapter.notifyDataSetChanged();
                                storiesAdapter.notifyDataSetChanged();
                                Log.d("value","notified");
                            }
                            Log.e("response",new JSONObject(value.getJSONObject(0).getString("thumbnail")).getString("width") + query);
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
           /* @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("q",query);
                params.put("mkt","en-us");
                return params;*/
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("Ocp-Apim-Trace", "true");
                params.put("Ocp-Apim-Subscription-Key", "03baf534e17b48fdaf2de1c603a30eac ");
                return params;
            }
        };

        Log.e("request",request.toString());
        ZestaGramApplication.getInstance().addToRequestQueue(request);
    }
    public void setQuery(String query)
    {
        Log.d("settingquery",query);
        this.query = query;
        downloadData();
    }
}
