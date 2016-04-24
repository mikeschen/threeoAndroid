package com.mikeschen.www.threeo;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.contentListView) ListView mContentListView;
    @Bind(R.id.fab) FloatingActionButton fab;
    private String[] headlines = new String[] {"Uber Settles", "Apple Services in China", "F.B.I. Director Says 1.3 Mil For iPhone Hack", "Alphabet Earnings Disappoint", "Microsoft Cloud Biz Falls Short"};
    public static final String TAG = MainActivity.class.getSimpleName();

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

//        fab.setOnClickListener(this);

//        setContentView(R.layout.activity_post);
//        mContentListView = (ListView) findViewById(R.id.contentListView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, headlines);
        mContentListView.setAdapter(adapter);

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
}
