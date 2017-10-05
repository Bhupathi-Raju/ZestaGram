package com.example.zemoso.zestagram.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.zemoso.zestagram.Java_beans.FeedInfo;
import com.example.zemoso.zestagram.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zemoso on 22/9/17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.MyViewHolder> {

    private JSONArray array;
    private Context context;
    private FeedInfo feedInfo;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("stories","onCreateVieHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story_feed,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("stories", "onBindVieHolder");
        if (array!=null)
            try {
                JSONObject object = array.getJSONObject(position);
                String url = object.getString("thumbnailUrl");
                Glide.with(context).load(url).asBitmap().into(holder.storyImage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    public StoriesAdapter(Context context , JSONArray value , FeedInfo feedInfo) {

        Log.e("stories","StoriesAdapter");
        this.context = context;
        this.array = value;
        this.feedInfo = feedInfo;
    }

    @Override
    public int getItemCount() {
        if (array == null) {
            array = feedInfo.getArray();
            return 0;
        }
        else {
            return array.length();
        }
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
}
