package com.example.zemoso.zestagram.Home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zemoso.zestagram.Home.Model.FeedInfo;
import com.example.zemoso.zestagram.R;

import java.util.List;

/**
 * Created by zemoso on 20/9/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private List<FeedInfo> feedList;
    private static final String TAG = FeedAdapter.class.getSimpleName();
    private FeedInfo feedInfo;
    private Context context;

    public FeedAdapter(Context context,List<FeedInfo> feedList) {
        this.context = context;
        this.feedList = feedList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(TAG,"oncreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_feed,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        feedInfo = feedList.get(position);
        String url = feedInfo.getImageUrl();
        Glide.with(context).load(url).asBitmap().into(holder.imageView);
        Glide.with(context).load(url).asBitmap().into(holder.profilePicture);
    }

    @Override
    public int getItemCount() {
        if(feedList == null)
            return 0;
        return feedList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView,profilePicture;
        private Toolbar toolbar;
        private MyViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.feed);
            profilePicture = itemView.findViewById(R.id.thumbnail);
            toolbar = itemView.findViewById(R.id.imagetoolbar);
        }
    }

}
