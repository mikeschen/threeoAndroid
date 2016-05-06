package com.mikeschen.www.threeo;

/**
 * Created by reviveit on 4/29/16.
 */
public class Constants {
    public static final String FLICKR_CONSUMER_KEY = BuildConfig.FLICKR_CONSUMER_KEY;
    public static String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/";
    public static final String FETCH_RECENTS_METHOD = "flickr.photos.getRecent";
    public static final String SEARCH_METHOD =  "flickr.photos.search";
    public static final String FLICKR_QUERY_PARAMETER = "method";
    public static final String FIREBASE_URL = BuildConfig.FIREBASE_ROOT_URL;
    public static final String FIREBASE_HEADLINE = "headline";
    public static final String FIREBASE_URL_HEADLINE = FIREBASE_URL + "/" + FIREBASE_HEADLINE;
    public static final String FIREBASE_STORY = "story";
    public static final String FIREBASE_URL_STORY = FIREBASE_URL + "/" + FIREBASE_STORY;
    public static final String FIREBASE_POSTS = "posts";
    public static final String FIREBASE_URL_POSTS = FIREBASE_URL + "/" + FIREBASE_POSTS;
}
