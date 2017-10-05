package com.example.zemoso.zestagram.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.zemoso.zestagram.Interfaces.StoryFeedInterface;
import com.example.zemoso.zestagram.Java_beans.FeedInfo;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.ViewHolders.StroyListViewHolder;
import com.example.zemoso.zestagram.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zemoso on 20/9/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = FeedAdapter.class.getSimpleName();
    private JSONArray array;
    private FeedInfo feedInfo;
    private Context context;
    private StoryFeedInterface storyFeedInterface;




    //region Constructor
    public FeedAdapter(Context context,StoryFeedInterface storyFeedInterface,JSONArray value,FeedInfo feedInfo) {
        this.context = context;
        this.feedInfo = feedInfo;
        this.storyFeedInterface = storyFeedInterface;
        this.array = value;
    }
    //endregion

    //region Overrided Methods


    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return Constants.HolderType.story;
        else
            return Constants.HolderType.feed;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(TAG,"oncreateViewHolder");
        if (viewType == Constants.HolderType.story)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story_list,parent,false);
            return new StroyListViewHolder(view);
        }
        else if(viewType == Constants.HolderType.feed) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_feed, parent, false);
            return new MyViewHolder(view);
        }
        else
            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StroyListViewHolder) {
            storyFeedInterface.setStoryAdapter(((StroyListViewHolder) holder).getStoryRecyclerView());
        } else if (holder instanceof MyViewHolder) {
            if (array == null)
                array = feedInfo.getArray();
            if (array != null)
                try {
                    MyViewHolder myViewHolder = (MyViewHolder) holder;
                    JSONObject object = array.getJSONObject(position - 1);
                    String url = object.getString("contentUrl");
                    Glide.with(context).load(url).asBitmap().into(myViewHolder.imageView);
                    Glide.with(context).load(url).asBitmap().into(myViewHolder.profilePicture);
                    myViewHolder.name.setText(object.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public int getItemCount() {
        if(array == null)
            return 5;
        return array.length()+1;
    }

    //endregion

    //region ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView,profilePicture;
        private ToggleButton fav;
        private TextView name;
        private MyViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.feed);
            profilePicture = itemView.findViewById(R.id.thumbnail);
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
