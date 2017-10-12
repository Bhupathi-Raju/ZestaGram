package com.example.zemoso.zestagram.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.zemoso.zestagram.R;
import com.example.zemoso.zestagram.Views.DynamicImageView;

/**
 * Created by zemoso on 9/10/17.
 */

public class SearchViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    public SearchViewHolder(View itemView) {
        super(itemView);
        imageView =  itemView.findViewById(R.id.search_image);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
