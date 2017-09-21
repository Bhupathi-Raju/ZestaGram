package com.example.zemoso.zestagram.Home.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zemoso.zestagram.Model.FeedInfo;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.utils.ZestaGramApplication;

import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by zemoso on 20/9/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    public List<FeedInfo> feedList;
    private static final String TAG = FeedAdapter.class.getSimpleName();
    FeedInfo feedInfo;
    public Context context;
    public FeedAdapter(Context context) {
        context = context;
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
        Log.e("feed",feedList.get(position).getImageUrl());
        feedInfo = feedList.get(position);
        String url = feedInfo.getImageUrl();
        if(context == null)
            Log.e("null","it's null");
        Glide.with(ZestaGramApplication.getInstance()).load(url).asBitmap().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(feedList == null)
            return 0;
        return feedList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public Toolbar toolbar;

        public MyViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.feed);
            toolbar = itemView.findViewById(R.id.imagetoolbar);
        }
    }

    public void addList(List<FeedInfo> feedList) {
        this.feedList = feedList;
        notifyDataSetChanged();
    }
}
