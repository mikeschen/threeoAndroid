package com.mikeschen.www.threeo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.models.Photo;
import com.mikeschen.www.threeo.ui.PhotoDetailActivity;
import com.mikeschen.www.threeo.ui.ScribeActivity;
import com.mikeschen.www.threeo.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 125;
    private static final int MAX_HEIGHT = 125;
    @Bind(R.id.photoImageView) ImageView mPhotoImageView;
    @Bind(R.id.headlineDetailTextView) TextView mHeadlineDetailTextView;
    @Bind(R.id.storyDetailTextView) TextView mStoryDetailTextView;
    private Context mContext;
    private ArrayList<Photo> mPhotos = new ArrayList<>();

    public PhotoViewHolder(View itemView, ArrayList<Photo> photos) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mPhotos = photos;
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, PhotoDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("photos", Parcels.wrap(mPhotos));
                if (mContext.getClass().getSimpleName().equals(ScribeActivity.class.getSimpleName())) {
                    intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_SAVED);
                } else {
                    intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
                }
                mContext.startActivity(intent);
            }
        });
    }

    public void bindPhoto(Photo photo) {
        Picasso.with(mContext)
                .load("https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + "_m.jpg")
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mPhotoImageView);
        mHeadlineDetailTextView.setText(photo.getHeadline());
        mStoryDetailTextView.setText(photo.getStory());
    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }
}


