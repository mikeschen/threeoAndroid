package com.mikeschen.www.threeo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.adapters.FirebasePostListAdapter;
import com.mikeschen.www.threeo.models.Photo;

import butterknife.Bind;
import butterknife.ButterKnife;
//Friday SavedRestaurantListActivity goes into main
//use activity_scribe.xml for activity
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.fab) FloatingActionButton fab;
    public static final String TAG = MainActivity.class.getSimpleName();
    private Query mQuery;
    private Firebase mFirebasePostsRef;
    private FirebasePostListAdapter mAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.main_logo);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mFirebasePostsRef = new Firebase(Constants.FIREBASE_URL_POSTS);

        setUpFirebaseQuery();
        setUpRecyclerView();

//        fab.setOnClickListener(this);

//        setContentView(R.layout.activity_post);
//        mContentListView = (ListView) findViewById(R.id.contentListView);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String post = ((TextView)view).getText().toString();
//                Toast.makeText(MainActivity.this, post, Toast.LENGTH_LONG).show();
//            }
//        });

//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.fab:
//                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                    startActivity(intent);
//                    break;
//                default:
//                    break;
//            }
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_bugle:
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);

        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setUpFirebaseQuery() {
        String location = mFirebasePostsRef.toString();
        Log.d("location", location);
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebasePostListAdapter(mQuery, Photo.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
