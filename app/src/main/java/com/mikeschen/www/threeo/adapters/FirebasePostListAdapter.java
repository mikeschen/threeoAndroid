package com.mikeschen.www.threeo.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.R;
import com.mikeschen.www.threeo.models.Photo;
import com.mikeschen.www.threeo.util.FirebaseRecyclerAdapter;
import com.mikeschen.www.threeo.util.ItemTouchHelperAdapter;
import com.mikeschen.www.threeo.util.OnStartDragListener;

import java.util.Collections;

public class FirebasePostListAdapter extends FirebaseRecyclerAdapter<PhotoViewHolder, Photo> implements ItemTouchHelperAdapter {
    private final OnStartDragListener mDragStartListener;
    private Context mContext;
    public FirebasePostListAdapter(Query query, Class<Photo> itemClass, OnStartDragListener dragStartListener) {
        super(query, itemClass);
        mDragStartListener = dragStartListener;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        return new PhotoViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, int position) {
        holder.bindPhoto(getItem(position));
        holder.mPhotoImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(getItems(), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
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

    @Override
    public int getItemCount() {
        return getItems().size();
    }
}
