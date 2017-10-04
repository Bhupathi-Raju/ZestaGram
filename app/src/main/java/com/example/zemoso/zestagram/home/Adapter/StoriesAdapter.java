package com.example.zemoso.zestagram.home.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.home.Model.FeedInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zemoso on 22/9/17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.MyViewHolder> {

    private JSONArray array;
    private Context context;
    public FeedInfo feedInfo;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("stories","onCreateVieHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story_feed,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("stories", "onBindVieHolder");
        //   feedInfo = feedInfos.get(position);
        if (array != null) {
            try {
                JSONObject object = array.getJSONObject(position);
                JSONObject urlObject = new JSONObject(object.getString("thumbnail"));
                String url = urlObject.getString("thumbnailUrl");
                Glide.with(context).load(url).asBitmap().into(holder.storyImage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public StoriesAdapter(Context context) {

        Log.e("stories","StoriesAdapter");
        this.context = context;
    }

    @Override
    public int getItemCount() {
        if(array == null)
            return 0;
        else
            return array.length();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView storyImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.e("stories","MyviewHolder");
            storyImage = itemView.findViewById(R.id.thumbnail_story);
        }
    }
    public void setArray(JSONArray array)
    {
        this.array = array;
        notifyDataSetChanged();
    }
}
