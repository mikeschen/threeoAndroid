package com.mikeschen.www.threeo.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.mikeschen.www.threeo.Constants;
import com.mikeschen.www.threeo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.textView4) TextView mTextView4;
    @Bind(R.id.passwordLoginButton) Button mPasswordLoginButton;
    @Bind(R.id.registerTextView) TextView mRegisterTextView;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    private Firebase mFirebaseRef;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "Imperial.ttf");
        mTextView4.setTypeface(custom_font);
        mEmailEditText.setTypeface(custom_font);
        mPasswordEditText.setTypeface(custom_font);
        mPasswordLoginButton.setOnClickListener(this);
        mRegisterTextView.setOnClickListener(this);
        String signupEmail = mSharedPreferences.getString(Constants.KEY_USER_EMAIL, null);
        if (signupEmail != null) {
            mEmailEditText.setText(signupEmail);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mPasswordLoginButton) {
            loginWithPassword();
        }
        if (view == mRegisterTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void loginWithPassword() {
        final String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (email.equals("")) {
            mEmailEditText.setError("Please enter your email");
        }
        if (password.equals("")) {
            mPasswordEditText.setError("Password cannot be blank");
        }

        mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {

            @Override
            public void onAuthenticated(AuthData authData) {
                mSharedPreferencesEditor.putString(Constants.KEY_USER_EMAIL, email).apply();
                if (authData != null) {
                    String userUid = authData.getUid();

                    String userInfo = authData.toString();

                    mSharedPreferencesEditor.putString(Constants.KEY_UID, userUid).apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                switch (firebaseError.getCode()) {
                    case FirebaseError.INVALID_EMAIL:
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        mEmailEditText.setError("Please check that you entered your email correctly");
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        mEmailEditText.setError(firebaseError.getMessage());
                        break;
                    case FirebaseError.NETWORK_ERROR:
                        showErrorToast("There was a problem with the network connection");
                        break;
                    default:
                        showErrorToast(firebaseError.toString());
                }
            }
        });
    }

    private void showErrorToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
