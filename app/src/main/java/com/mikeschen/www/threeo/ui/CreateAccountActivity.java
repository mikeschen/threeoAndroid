package com.mikeschen.www.threeo.ui;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.mikeschen.www.threeo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAccountActivity extends Activity {
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "Imperial.ttf");
        mNameEditText.setTypeface(custom_font);
        mPasswordEditText.setTypeface(custom_font);
    }
}
