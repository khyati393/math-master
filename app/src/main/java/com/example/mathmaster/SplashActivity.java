package com.example.mathmaster;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;  //4 SECOND
    Animation tAnim,bAnim;
    TextView text1,text2,text3,text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tAnim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bAnim= AnimationUtils.loadAnimation(this,R.anim.botm_anim);

        text1=findViewById(R.id.t1);
        text2=findViewById(R.id.t2);
        text3=findViewById(R.id.t3);
        text4=findViewById(R.id.t4);

        text1.setAnimation(tAnim);
        text2.setAnimation(tAnim);
        text3.setAnimation(bAnim);
        text4.setAnimation(bAnim);

        //We can use splash screen in your first loading Activity like this:
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent;
                homeIntent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}