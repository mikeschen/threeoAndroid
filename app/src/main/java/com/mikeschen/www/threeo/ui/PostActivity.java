package com.mikeschen.www.threeo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.R;
import butterknife.Bind;
import butterknife.ButterKnife;


public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.editHeadlineText) EditText mEditHeadlineText;
    @Bind(R.id.editStoryText) EditText mEditStoryText;
    @Bind(R.id.submitButton) Button mSubmitButton;
    private Firebase mHeadlineRef;
    private ValueEventListener mHeadlineRefListener;

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        mHeadlineRef = new Firebase(Constants.FIREBASE_URL_HEADLINE);
        mHeadlineRefListener = mHeadlineRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String headlines = dataSnapshot.getValue().toString();
                Log.d("headline updated", headlines);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
        mSubmitButton.setOnClickListener(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHeadlineRef.removeEventListener(mHeadlineRefListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                String headline = mEditHeadlineText.getText().toString();
                String story = mEditStoryText.getText().toString();
                savePostToFirebase(headline, story);
                Intent intent = new Intent(PostActivity.this, ScribeActivity.class);
                intent.putExtra("headline", headline);
                intent.putExtra("story", story);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void savePostToFirebase(String headline, String story) {
        Firebase headlineRef = new Firebase(Constants.FIREBASE_URL_HEADLINE);
        headlineRef.push().setValue(headline);
        Firebase storyRef = new Firebase(Constants.FIREBASE_URL_STORY);
        storyRef.push().setValue(story);
    }
}