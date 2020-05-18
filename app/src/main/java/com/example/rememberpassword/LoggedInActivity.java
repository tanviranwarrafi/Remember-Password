package com.example.rememberpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
    }

    public void onBackPressed() {
        Intent start = new Intent(LoggedInActivity.this,MainActivity.class);
        startActivity(start);
        finishActivity(0);
    }
}
