package com.example.zemoso.zestagram.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zemoso.zestagram.R;

/**
 * Created by zemoso on 4/10/17.
 */

public class StroyListViewHolder extends RecyclerView.ViewHolder{

    private RecyclerView storyRecyclerView;

    public StroyListViewHolder(View itemView) {
        super(itemView);
        storyRecyclerView = itemView.findViewById(R.id.story_recycler_view);
    }

    public RecyclerView getStoryRecyclerView() {
        return storyRecyclerView;
    }
}
