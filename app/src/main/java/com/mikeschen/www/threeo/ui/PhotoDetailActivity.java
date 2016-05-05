package com.mikeschen.www.threeo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.adapters.PhotoPagerAdapter;
import com.mikeschen.www.threeo.models.Photo;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    private PhotoPagerAdapter adapterViewPager;
    ArrayList<Photo> mPhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);
        mPhotos = Parcels.unwrap(getIntent().getParcelableExtra("photos"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        adapterViewPager = new PhotoPagerAdapter(getSupportFragmentManager(), mPhotos);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}

