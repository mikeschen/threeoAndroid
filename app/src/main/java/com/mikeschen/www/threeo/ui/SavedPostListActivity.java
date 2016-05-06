package com.mikeschen.www.threeo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.adapters.FirebasePostListAdapter;
import com.mikeschen.www.threeo.models.Photo;

import butterknife.Bind;
import butterknife.ButterKnife;

//public class SavedPostListActivity extends AppCompatActivity {
//
//    private Query mQuery;
//    private Firebase mFirebasePostsRef;
//    private FirebasePostListAdapter mAdapter;
//
//    @Bind(R.id.recyclerView)
//    RecyclerView mRecyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
//        mFirebasePostsRef = new Firebase(Constants.FIREBASE_URL_POSTS);
//
//        setUpFirebaseQuery();
//        setUpRecyclerView();
//    }
//
//    private void setUpFirebaseQuery() {
//        String location = mFirebasePostsRef.toString();
//        mQuery = new Firebase(location);
//    }
//
//    private void setUpRecyclerView() {
//        mAdapter = new FirebasePostListAdapter(mQuery, Photo.class);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mAdapter);
//    }
//}
