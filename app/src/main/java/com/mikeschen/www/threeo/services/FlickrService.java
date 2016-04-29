package com.mikeschen.www.threeo.services;

import android.util.Log;

import com.mikeschen.www.threeo.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by reviveit on 4/29/16.
 */
public class FlickrService {
    public static void findPhotos(String headline, Callback callback) {
        String CONSUMER_KEY = Constants.FLICKR_CONSUMER_KEY;
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.FLICKR_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.FLICKR_QUERY_PARAMETER, "flickr.photos.search");
        urlBuilder.addQueryParameter("api_key", Constants.FLICKR_CONSUMER_KEY);
        urlBuilder.addQueryParameter("format", "json");
        urlBuilder.addQueryParameter("nojsoncallback", "1");
        urlBuilder.addQueryParameter("text", headline);
        String url = urlBuilder.build().toString();
        Log.d("string url", url);
        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
