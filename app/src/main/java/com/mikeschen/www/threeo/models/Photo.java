package com.mikeschen.www.threeo.models;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.parceler.Parcel;

@Parcel
@JsonIgnoreProperties(ignoreUnknown=true)
public class Photo {
    private String headline;
    private String story;
    private int farm;
    private String server;
    private String id;
    private String secret;
    private String owner;
    private String title;

    public Photo() {}

    public Photo(String headline, String story, int farm, String server, String id, String secret, String owner, String title) {
        this.headline = headline;
        this.story = story;
        this.farm = farm;
        this.server = server;
        this.id = id;
        this.secret = secret;
        this.owner = owner;
        this.title = title;
    }

    public String getHeadline() { return headline; }
    public String getStory() { return story; }
    public int getFarm() {
        return farm;
    }
    public String getServer() {
        return server;
    }
    public String getId() {
        return id;
    }
    public String getSecret() {
        return secret;
    }
    public String getWebsite() {
        Log.d("userWeb", "https://www.flickr.com/photos/" + owner);
        return "https://www.flickr.com/photos/" + owner;
    }
    public String getTitle() {
        return title;
    }
}
