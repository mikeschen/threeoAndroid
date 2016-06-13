package com.mikeschen.www.threeo.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
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

import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoDetailFragment extends Fragment implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    @Bind(R.id.photoMainImageView) ImageView mImageLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.savePostButton) Button mSavePostButton;
    private String mSource;
    private static final int REQUEST_IMAGE_CAPTURE = 111;

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
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
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
              String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
              Firebase ref = new Firebase(Constants.FIREBASE_URL_POSTS).child(userUid);
              Firebase pushRef = ref.push();
              String postPushId = pushRef.getKey();
              pushRef.setValue(mPhoto);
              mPhoto.setPushId(postPushId);

              Toast.makeText(getContext(), "Post Saved", Toast.LENGTH_SHORT).show();
              Intent intent = new Intent();
              intent.setClass(getActivity(), MainActivity.class);
              getActivity().startActivity(intent);
              mSavePostButton.setVisibility(View.GONE);
          }
      }
}