package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.rcl.excalibur.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Nullable  @Bind(R.id.forward_button) Button forwardButton;
    @Nullable  @Bind(R.id.back_button) Button backButton;
    @Nullable  @Bind(R.id.main_background_image) LinearLayout llMainBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Nullable @OnClick(R.id.forward_button)
    void goForward() {
        llMainBackground.setBackgroundResource(R.drawable.map);
        forwardButton.setVisibility(View.INVISIBLE);
        backButton.setVisibility(View.VISIBLE);
    }

    @Nullable  @OnClick(R.id.back_button)
    void goBack() {
        llMainBackground.setBackgroundResource(R.drawable.splash);
        forwardButton.setVisibility(View.VISIBLE);
        backButton.setVisibility(View.INVISIBLE);
    }

    /**
     * Goes to the user list screen.
     */
    @OnClick(R.id.btn_LoadDataDB)
    void navigateToUserList() {
        startActivity(new Intent(this, LoadFromDBActivity.class));
    }
}
