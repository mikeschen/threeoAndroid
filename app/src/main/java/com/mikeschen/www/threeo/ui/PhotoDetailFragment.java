package com.mikeschen.www.threeo.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.models.Photo;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.photoMainImageView) ImageView mImageLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.savePostButton) Button mSavePostButton;
    private String mSource;

    private Photo mPhoto;

    public static PhotoDetailFragment newInstance(Photo photo, String source) {

        PhotoDetailFragment photoDetailFragment = new PhotoDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("photo", Parcels.wrap(photo));
        args.putString(Constants.KEY_SOURCE, source);
        photoDetailFragment.setArguments(args);
        return photoDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhoto = Parcels.unwrap(getArguments().getParcelable("photo"));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bugle:
                Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivity(intent);
//            case R.id.action_logout:
//                logout();
//                return true;
//            case R.id.action_photo:
//                onLaunchCamera();
            default:
                break;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        ButterKnife.bind(this, view);
        mSavePostButton.setOnClickListener(this);
        if (!mSource.equals(Constants.SOURCE_SAVED)) {
            mSavePostButton.setVisibility(View.GONE);
        }
        Picasso.with(view.getContext()).load("https://farm" + mPhoto.getFarm() + ".staticflickr.com/" + mPhoto.getServer() + "/" + mPhoto.getId() + "_" + mPhoto.getSecret() + "_n.jpg").into(mImageLabel);
        mWebsiteLabel.setOnClickListener(this);
        return view;
    }

      @Override
      public void onClick(View v) {
          if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mPhoto.getWebsite()));
            startActivity(webIntent);
          }
          if(v == mSavePostButton) {
              Firebase ref = new Firebase(Constants.FIREBASE_URL_POSTS);
              ref.push().setValue(mPhoto);
              Toast.makeText(getContext(), "Post Saved", Toast.LENGTH_SHORT).show();
              Intent intent = new Intent();
              intent.setClass(getActivity(), MainActivity.class);
              getActivity().startActivity(intent);
              mSavePostButton.setVisibility(View.GONE);
          }
      }
}