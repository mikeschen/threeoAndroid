package com.mikeschen.www.threeo.ui;

import android.content.Intent;
<<<<<<< HEAD
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
=======
import android.os.Bundle;
import android.app.Activity;
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

<<<<<<< HEAD
import com.mikeschen.www.threeo.Constants;
=======
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
import com.mikeschen.www.threeo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

<<<<<<< HEAD
public class PostActivity extends AppCompatActivity implements View.OnClickListener {
=======
public class PostActivity extends Activity implements View.OnClickListener {
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
    @Bind(R.id.editHeadlineText) EditText mEditHeadlineText;
    @Bind(R.id.editStoryText) EditText mEditStoryText;
    @Bind(R.id.submitButton) Button mSubmitButton;

<<<<<<< HEAD
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

=======
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

<<<<<<< HEAD
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

=======
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                String headline = mEditHeadlineText.getText().toString();
                String story = mEditStoryText.getText().toString();
<<<<<<< HEAD
                addToSharedPreferences(headline);
                addToSharedPreferences(story);
                Intent intent = new Intent(PostActivity.this, ScribeActivity.class);
=======
                Intent intent = new Intent(PostActivity.this, ScribeActivity.class);
                intent.putExtra("headline", headline);
                intent.putExtra("story", story);
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
                startActivity(intent);
                break;
            default:
                break;
        }
    }
<<<<<<< HEAD

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.HEADLINE_KEY, location).apply();
        mEditor.putString(Constants.STORY_KEY, location).apply();
    }
=======
>>>>>>> d454c774cbd59e34f44f2e4725e03bcb5b81fdae
}