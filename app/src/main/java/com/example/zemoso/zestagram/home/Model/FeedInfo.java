package com.example.zemoso.zestagram.home.Model;

/**
 * Created by zemoso on 20/9/17.
 */

public class FeedInfo {
     private String contactName;
     private String imageUrl;
     private String picUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
