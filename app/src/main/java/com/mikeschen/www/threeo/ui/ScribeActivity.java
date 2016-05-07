package com.mikeschen.www.threeo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.adapters.PhotoListAdapter;
import com.mikeschen.www.threeo.models.Photo;
import com.mikeschen.www.threeo.services.FlickrService;


import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ScribeActivity extends AppCompatActivity {
    private ProgressDialog mAuthProgressDialog;
    @Bind(R.id.headlineTextView) TextView mHeadlineTextView;
    @Bind(R.id.storyTextView) TextView mStoryTextView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    public static final String TAG = ScribeActivity.class.getSimpleName();
    private PhotoListAdapter mAdapter;

    public ArrayList<Photo> mPhotos = new ArrayList<>();

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
        getPhotos(headline, story);
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Searching Flickr...");
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();
    }

    private void getPhotos(String headline, String story) {
        final FlickrService flickrService = new FlickrService();
        FlickrService.findPhotos(headline, story, new Callback() {

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
                        mAuthProgressDialog.dismiss();
                        mAdapter = new PhotoListAdapter(getApplicationContext(), mPhotos);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ScribeActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}

