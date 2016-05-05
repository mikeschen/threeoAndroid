package com.mikeschen.www.threeo.ui;

import android.content.Intent;
<<<<<<< HEAD
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
=======
import android.os.Bundle;
import android.app.Activity;
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

<<<<<<< HEAD
import com.mikeschen.www.threeo.Constants;
=======
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
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

<<<<<<< HEAD
public class ScribeActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentHeadline;
    private String mRecentStory;
=======
public class ScribeActivity extends Activity {
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
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
        getPhotos(headline);
<<<<<<< HEAD

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentHeadline = mSharedPreferences.getString(Constants.HEADLINE_KEY, null);
        mRecentStory = mSharedPreferences.getString(Constants.STORY_KEY, null);
        Log.d("Shared Pref headline", mRecentHeadline);
        Log.d("Shared Pref story", mRecentStory);
=======
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
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
                    mAdapter = new PhotoListAdapter(getApplicationContext(), mPhotos);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(ScribeActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);
                }
            });
        }
        });
    }
}

