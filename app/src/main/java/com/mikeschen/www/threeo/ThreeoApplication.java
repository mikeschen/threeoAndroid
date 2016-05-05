package com.mikeschen.www.threeo;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Guest on 5/5/16.
 */
public class ThreeoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
