package com.mikeschen.www.threeo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mikeschen.www.threeo.models.Photo;
import com.mikeschen.www.threeo.ui.PhotoDetailFragment;

import java.util.ArrayList;

public class PhotoPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Photo> mPhotos;
    private String mSource;

    public PhotoPagerAdapter(FragmentManager fm, ArrayList<Photo> photos, String source) {
        super(fm);
        mPhotos = photos;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoDetailFragment.newInstance(mPhotos.get(position), mSource);
    }

    @Override
    public int getCount() {
        return mPhotos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPhotos.get(position).getTitle();
    }
}
