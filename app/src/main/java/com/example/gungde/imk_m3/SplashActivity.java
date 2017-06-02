package com.example.gungde.imk_m3;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;

import com.example.gungde.imk_m3.utils.BaseActivity;

public class SplashActivity extends BaseActivity implements Runnable {

    private static final int DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideToolbar();
        binding(R.layout.activity_splash);
        setHandler();
    }

    private void hideToolbar() {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
    }

    private void setHandler() {
        new Handler().postDelayed(this,DELAY);
    }

    @Override
    public void run() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}