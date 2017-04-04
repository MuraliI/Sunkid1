package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.rcl.excalibur.R;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.mvp.presenter.EmailPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class EmailActivity extends BaseActivity<EmailPresenter> implements View.OnKeyListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
        presenter.init();
    }

    @OnClick(R.id.imageViewBack)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

    @OnFocusChange(R.id.editTextEmail)
    void onFocusChange(boolean hasFocus) {
        presenter.setFocus(hasFocus);
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, EmailActivity.class);
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getKeyCode() == 66 && event.getAction() == KeyEvent.ACTION_UP) {
            presenter.verifyEmail(((EditText) v).getText().toString().replaceAll("\n", ""));
        }
        return false;
    }
}
