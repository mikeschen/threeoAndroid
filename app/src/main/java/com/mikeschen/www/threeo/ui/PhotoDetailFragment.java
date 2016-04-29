package com.mikeschen.www.threeo.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.models.Photo;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoDetailFragment extends Fragment {
    @Bind(R.id.photoMainImageView)
    ImageView mImageLabel;

    private Photo mPhoto;

    public static PhotoDetailFragment newInstance(Photo photo) {
        PhotoDetailFragment photoDetailFragment = new PhotoDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("photo", Parcels.wrap(photo));
        photoDetailFragment.setArguments(args);
        return photoDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhoto = Parcels.unwrap(getArguments().getParcelable("photo"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load("https://farm" + mPhoto.getFarm() + ".staticflickr.com/" + mPhoto.getServer() + "/" + mPhoto.getId() + "_" + mPhoto.getSecret() + "_m.jpg").into(mImageLabel);
        return view;
    }
}