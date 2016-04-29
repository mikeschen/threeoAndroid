package com.mikeschen.www.threeo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.services.FlickrService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ScribeActivity extends Activity {
    @Bind(R.id.headlineTextView) TextView mHeadlineTextView;
    @Bind(R.id.storyTextView) TextView mStoryTextView;
    public static final String TAG = ScribeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scribe);
        ButterKnife.bind(this);

        Intent intent =  getIntent();
        String headline = intent.getStringExtra("headline");
        String story = intent.getStringExtra("story");
        mHeadlineTextView.setText("Headline: " + headline);
        mStoryTextView.setText("Story: " + story);
        getPhotos(headline);
    }

    private void getPhotos(String headline) {
        FlickrService.findPhotos(headline, new Callback() {
        @Override
        public void onFailure (Call call, IOException e){
            e.printStackTrace();
        }
            @Override
            public void onResponse (Call call, Response response)throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

