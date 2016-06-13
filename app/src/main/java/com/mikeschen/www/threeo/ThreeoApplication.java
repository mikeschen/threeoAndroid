package com.mikeschen.www.threeo;

import android.app.Application;

import com.firebase.client.Firebase;

public class ThreeoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}