package com.mikeschen.www.threeo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Query;
import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.models.Photo;
import com.mikeschen.www.threeo.util.FirebaseRecyclerAdapter;

public class FirebasePostListAdapter extends FirebaseRecyclerAdapter<PhotoViewHolder, Photo> {
    public FirebasePostListAdapter(Query query, Class<Photo> itemClass) {
        super(query, itemClass);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        return new PhotoViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bindPhoto(getItem(position));
    }

    @Override
    protected void itemAdded(Photo item, String key, int position) {

    }

    @Override
    protected void itemChanged(Photo oldItem, Photo newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Photo item, String key, int position) {

    }

    @Override
    protected void itemMoved(Photo item, String key, int oldPosition, int newPosition) {

    }
}
