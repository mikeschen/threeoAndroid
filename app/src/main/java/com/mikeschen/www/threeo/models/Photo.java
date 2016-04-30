package com.mikeschen.www.threeo.models;

import android.util.Log;

import org.parceler.Parcel;

/**
 * Created by reviveit on 4/29/16.
 */

@Parcel
public class Photo {
    private int mFarm;
    private String mServer;
    private String mId;
    private String mSecret;
    private String mOwner;
    private String mTitle;

    public Photo() {}

    public Photo(int farm, String server, String id, String secret, String owner, String title) {
        this.mFarm = farm;
        this.mServer = server;
        this.mId = id;
        this.mSecret = secret;
        this.mOwner = owner;
        this.mTitle = title;
    }

    public int getFarm() {
        return mFarm;
    }
    public String getServer() {
        return mServer;
    }
    public String getId() {
        return mId;
    }
    public String getSecret() {
        return mSecret;
    }
    public String getWebsite() {
        Log.d("userWeb", "https://www.flickr.com/photos/" + mOwner);
        return "https://www.flickr.com/photos/" + mOwner;
    }
    public String getTitle() {
        return mTitle;
    }
}
