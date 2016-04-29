package com.mikeschen.www.threeo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.models.Photo;
import com.mikeschen.www.threeo.services.FlickrService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ScribeActivity extends Activity {
    public ArrayList<Photo> mPhotos = new ArrayList<>();
    @Bind(R.id.headlineTextView) TextView mHeadlineTextView;
    @Bind(R.id.storyTextView) TextView mStoryTextView;
    @Bind(R.id.listView) ListView mListView;
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
        final FlickrService flickrService = new FlickrService();

        FlickrService.findPhotos(headline, new Callback() {
        @Override
        public void onFailure (Call call, IOException e){
            e.printStackTrace();
        }

        @Override
        public void onResponse (Call call, Response response) {
            mPhotos = flickrService.processResults(response);

            ScribeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String[] photoFarms = new String[mPhotos.size()];
                    for (int i = 0; i < photoFarms.length; i++) {
                        photoFarms[i] = mPhotos.get(i).getFarm() + "";
                    }
                    ArrayAdapter adapter = new ArrayAdapter(ScribeActivity.this,
                            android.R.layout.simple_list_item_1, photoFarms);
                    mListView.setAdapter(adapter);
                }
            });
        }
        });
    }
}

