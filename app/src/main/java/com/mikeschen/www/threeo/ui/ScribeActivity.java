package com.mikeschen.www.threeo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.mikeschen.www.threeo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScribeActivity extends Activity {
    @Bind(R.id.headlineTextView) TextView mHeadlineTextView;
    @Bind(R.id.storyTextView) TextView mStoryTextView;

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
    }
}
