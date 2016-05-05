package com.mikeschen.www.threeo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.editHeadlineText) EditText mEditHeadlineText;
    @Bind(R.id.editStoryText) EditText mEditStoryText;
    @Bind(R.id.submitButton) Button mSubmitButton;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                String headline = mEditHeadlineText.getText().toString();
                String story = mEditStoryText.getText().toString();
                addToSharedPreferences(headline);
                addToSharedPreferences(story);
                Intent intent = new Intent(PostActivity.this, ScribeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.HEADLINE_KEY, location).apply();
        mEditor.putString(Constants.STORY_KEY, location).apply();
    }
}