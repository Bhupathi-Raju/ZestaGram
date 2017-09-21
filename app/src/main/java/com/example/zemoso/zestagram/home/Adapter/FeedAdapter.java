package com.example.zemoso.zestagram.home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zemoso.zestagram.home.Model.FeedInfo;
import com.example.zemoso.zestagram.R;

import java.util.List;

/**
 * Created by zemoso on 20/9/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    //region variables
    private List<FeedInfo> feedList;
    private static final String TAG = FeedAdapter.class.getSimpleName();
    private FeedInfo feedInfo;
    private Context context;
    //endregion

    //region Constructor
    public FeedAdapter(Context context,List<FeedInfo> feedList) {
        this.context = context;
        this.feedList = feedList;
    }
    //endregion

    //region Overrided Methods
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(TAG,"oncreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_feed,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        feedInfo = feedList.get(position);
        String url = feedInfo.getImageUrl();
        holder.name.setText(feedInfo.getContactName());
        Glide.with(context).load(url).asBitmap().into(holder.imageView);
        Glide.with(context).load(url).asBitmap().into(holder.profilePicture);
    }

    @Override
    public int getItemCount() {
        if(feedList == null)
            return 0;
        return feedList.size();
    }

    //endregion

    //region ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView,profilePicture;
        private Toolbar toolbar;
        private TextView name;
        private MyViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.feed);
            profilePicture = itemView.findViewById(R.id.thumbnail);
            toolbar = itemView.findViewById(R.id.imagetoolbar);
            name = itemView.findViewById(R.id.name);
        }
    }

    //endregion

}
