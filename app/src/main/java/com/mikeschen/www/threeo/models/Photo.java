package com.mikeschen.www.threeo.models;

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

    public Photo() {}

    public Photo(int farm, String server, String id, String secret) {
        this.mFarm = farm;
        this.mServer = server;
        this.mId = id;
        this.mSecret = secret;
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
        return "https://www.flickr.com/photos/" + mId;
    }
}
