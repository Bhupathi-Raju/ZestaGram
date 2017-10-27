package com.example.zemoso.zestagram.Adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zemoso.zestagram.Interfaces.StoryFeedInterface;
import com.example.zemoso.zestagram.Java_beans.FeedInfo;
import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.ThemeSelector;
import com.example.zemoso.zestagram.ViewHolders.StroyListViewHolder;
import com.example.zemoso.zestagram.databinding.LayoutImageFeedBinding;
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
    private ThemeSelector selector;




    //region Constructor
    public FeedAdapter(Context context, StoryFeedInterface storyFeedInterface, JSONArray value, FeedInfo feedInfo,ThemeSelector selector) {
        this.context = context;
        this.feedInfo = feedInfo;
        this.storyFeedInterface = storyFeedInterface;
        this.array = value;
        this.selector=selector;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "oncreateViewHolder");
        if (viewType == Constants.HolderType.story) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story_list, parent, false);
            return new StroyListViewHolder(view);
        } else if (viewType == Constants.HolderType.feed) {
                 LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                 LayoutImageFeedBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.layout_image_feed,parent,false);
                return new MyViewHolder(binding);
        }
            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StroyListViewHolder) {
            storyFeedInterface.setStoryAdapter(((StroyListViewHolder) holder).getStoryRecyclerView());
        } else if (holder instanceof MyViewHolder) {
            if (array != null)
                try {
                    JSONObject object = array.getJSONObject(position - 1);
                    String url = object.getString("contentUrl");
                    String name = object.getString("name");
                    selector.setUserName(name);
                    selector.setImageUrl(url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            ((MyViewHolder) holder).bind(selector);

        }

    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView imageView,String url)
    {
        Log.d("bind","loading Image");
        Glide.with(imageView.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }
    @Override
    public int getItemCount() {
        if(array == null) {
            array = feedInfo.getArray();
            return 5;
        }
        else {
            return array.length() + 1;
        }
    }

    //endregion

    //region ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ToggleButton fav;
        private final LayoutImageFeedBinding binding;

        public MyViewHolder(LayoutImageFeedBinding binding)
        {
            super(binding.getRoot());
            this.binding= binding;
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
        public void bind(ThemeSelector selector)
        {
            Log.d("bind",String.valueOf(selector.getColor()));
            binding.setColorPicker(selector);
            binding.executePendingBindings();
        }
    }
    //endregion
}
