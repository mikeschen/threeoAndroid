package com.mikeschen.www.threeo.services;

import android.util.Log;

import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.models.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    public ArrayList<Photo> processResults(Response response) {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject flickrJSON = new JSONObject(jsonData);
                JSONObject photosJSON = flickrJSON.getJSONObject("photos");
                JSONArray photoJSON = photosJSON.getJSONArray("photo");
                for(int i=0; i < photoJSON.length(); i++) {
                    JSONObject imageJSON = photoJSON.getJSONObject(i);
                    int farm = imageJSON.getInt("farm");
                    Log.d("farm", farm + "");
                    String server = imageJSON.getString("server");
                    String id = imageJSON.getString("id");
                    String secret = imageJSON.getString("secret");
                    Log.d("secret", secret);
                    Photo photo = new Photo(farm, server, id, secret);
                    photos.add(photo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return photos;
    }

}
