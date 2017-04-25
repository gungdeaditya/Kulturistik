package com.example.gungde.imk_m3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private static final int DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(this,DELAY);
    }

    @Override
    public void run() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}