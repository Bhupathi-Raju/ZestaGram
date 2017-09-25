package com.example.zemoso.zestagram.home.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
    private StoriesAdapter storiesAdapter;
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
        if(position == 0)
        {
            storiesAdapter = new StoriesAdapter(context,feedList);
            holder.storiesRecyclerview.setAdapter(storiesAdapter);
            holder.stories.setVisibility(View.VISIBLE);
            holder.watchAll.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.stories.setVisibility(View.INVISIBLE);
            holder.watchAll.setVisibility(View.INVISIBLE);
        }
            Log.e("position",String.valueOf(position));
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
        private RecyclerView storiesRecyclerview;
        private ToggleButton fav;
        private TextView name,stories,watchAll;
        private MyViewHolder(final View itemView) {
            super(itemView);
            storiesRecyclerview = itemView.findViewById(R.id.stories);
            storiesRecyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            imageView = itemView.findViewById(R.id.feed);
            profilePicture = itemView.findViewById(R.id.thumbnail);
            /*toolbar = itemView.findViewById(R.id.imagetoolbar);
            toolbar.inflateMenu(R.menu.image_feed_menu);*//**//**/
            stories = itemView.findViewById(R.id.text_stories);
            watchAll = itemView.findViewById(R.id.text_watch_all);
            name = itemView.findViewById(R.id.name);
            fav = itemView.findViewById(R.id.fav_toggle);
            fav.setChecked(false);
            fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                        fav.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.ic_favorite_black_24px));
                    else
                        fav.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.ic_favorite_black_border_24px));
                }
            });
        }
    }
    //endregion
}
