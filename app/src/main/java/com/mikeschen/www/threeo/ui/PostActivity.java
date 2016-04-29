package com.mikeschen.www.threeo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mikeschen.www.threeo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.editHeadlineText) EditText mEditHeadlineText;
    @Bind(R.id.editStoryText) EditText mEditStoryText;
    @Bind(R.id.submitButton) Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                String headline = mEditHeadlineText.getText().toString();
                String story = mEditStoryText.getText().toString();
                Intent intent = new Intent(PostActivity.this, ScribeActivity.class);
                intent.putExtra("headline", headline);
                intent.putExtra("story", story);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}