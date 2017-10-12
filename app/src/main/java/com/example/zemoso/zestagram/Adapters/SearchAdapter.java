package com.example.zemoso.zestagram.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zemoso.zestagram.Interfaces.StoryFeedInterface;
import com.example.zemoso.zestagram.Java_beans.FeedInfo;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.ViewHolders.SearchViewHolder;
import com.example.zemoso.zestagram.ViewHolders.StroyListViewHolder;
import com.example.zemoso.zestagram.utils.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zemoso on 9/10/17.
 */

public class SearchAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private StoryFeedInterface storyFeedInterface;
    private JSONArray value;
    private FeedInfo feedInfo;
    public SearchAdapter(Context context, StoryFeedInterface storyFeedInterface, JSONArray value, FeedInfo feedInfo)
    {
        this.mContext = context;
        this.storyFeedInterface = storyFeedInterface;
        this.value = value;
        this.feedInfo = feedInfo;
    }

    @Override
    public int getItemViewType(int position) {
      if(position == 0)
          return Constants.HolderType.story;
        else
            return Constants.HolderType.feed;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == Constants.HolderType.story)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story_list,parent,false);
            return new StroyListViewHolder(view);
        }
        if(viewType == Constants.HolderType.feed)
        {
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_results,parent,false);
            return new SearchViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof StroyListViewHolder) {
            storyFeedInterface.setStoryAdapter(((StroyListViewHolder) holder).getStoryRecyclerView());
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
        else if(holder instanceof SearchViewHolder)
        {
            if(value != null)
            {
                Log.d("changed value",value.toString());
                SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
                try {
                    JSONObject object = value.getJSONObject(position-1);
                    String url = object.getString("contentUrl");
                    Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.RESULT).into(searchViewHolder.getImageView());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        value = feedInfo.getArray();
        if (value == null)
        {
            Log.e("size","0");
            return 5;
        }
        else {
            Log.e("size",String.valueOf(value.length()));
            return value.length() + 1;
        }
    }
}
