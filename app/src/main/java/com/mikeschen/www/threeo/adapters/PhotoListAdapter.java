package com.mikeschen.www.threeo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.models.Photo;
import com.mikeschen.www.threeo.ui.PhotoDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by reviveit on 4/29/16.
 */
public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {
    private ArrayList<Photo> mPhotos = new ArrayList<>();
    private Context mContext;
    private static final int MAX_WIDTH = 150;
    private static final int MAX_HEIGHT = 150;

    public PhotoListAdapter(Context context, ArrayList<Photo> photos) {
        mContext = context;
        mPhotos = photos;
    }

    @Override
    public PhotoListAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoListAdapter.PhotoViewHolder holder, int position) {
        holder.bindPhoto(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.photoImageView) ImageView mPhotoImageView;
        private Context mContext;


        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(mContext, PhotoDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("photos", Parcels.wrap(mPhotos));
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
        }
    }
}