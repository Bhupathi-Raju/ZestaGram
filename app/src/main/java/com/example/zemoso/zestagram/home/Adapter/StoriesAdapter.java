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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zemoso on 22/9/17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.MyviewHolder> {

    private List<FeedInfo> feedInfos;
    private Context context;
    public FeedInfo feedInfo;
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("stories","onCreateVieHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story_feed,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        Log.e("stories","onBindVieHolder");
        feedInfo = feedInfos.get(position);
        String url = feedInfo.getImageUrl();
        Glide.with(context).load(url).asBitmap().into(holder.storyImage);
    }

    public StoriesAdapter(Context context,List<FeedInfo> feedInfos) {

        Log.e("stories","StoriesAdapter");
        this.context = context;
        this.feedInfos =feedInfos;
    }

    @Override
    public int getItemCount() {
          return feedInfos.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder
    {

        private ImageView storyImage;
        public MyviewHolder(View itemView) {
            super(itemView);
            Log.e("stories","MyviewHolder");
            storyImage = itemView.findViewById(R.id.thumbnail_story);
        }
    }
}
