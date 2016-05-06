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

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoViewHolder> {
    private ArrayList<Photo> mPhotos = new ArrayList<>();
    private Context mContext;

    public PhotoListAdapter(Context context, ArrayList<Photo> photos) {
        mContext = context;
        mPhotos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(view, mPhotos);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bindPhoto(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}