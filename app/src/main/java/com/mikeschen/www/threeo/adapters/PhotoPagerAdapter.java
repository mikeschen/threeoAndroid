package com.mikeschen.www.threeo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mikeschen.www.threeo.models.Photo;
import com.mikeschen.www.threeo.ui.PhotoDetailFragment;

import java.util.ArrayList;

/**
 * Created by reviveit on 4/29/16.
 */
public class PhotoPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Photo> mPhotos;

    public PhotoPagerAdapter(FragmentManager fm, ArrayList<Photo> photos) {
        super(fm);
        mPhotos = photos;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoDetailFragment.newInstance(mPhotos.get(position));
    }

    @Override
    public int getCount() {
        return mPhotos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPhotos.get(position).getId();
    }
}
