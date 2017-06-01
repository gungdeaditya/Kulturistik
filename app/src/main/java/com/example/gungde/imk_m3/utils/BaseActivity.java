package com.example.gungde.imk_m3.utils;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by gungdeaditya on 01/06/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected void binding(int layout){
        setContentView(layout);
        ButterKnife.bind(this);
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
