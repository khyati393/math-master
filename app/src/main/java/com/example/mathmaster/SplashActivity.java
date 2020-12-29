package com.example.mathmaster;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASHtimeOUT = 4000;  //4 SECOND

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //We can use splash screen in your first loading Activity like this:
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent hTntent;
                hTntent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(hTntent);
                finish();
            }
        }, SPLASHtimeOUT);
    }
}