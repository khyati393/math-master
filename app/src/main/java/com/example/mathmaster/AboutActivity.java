package com.example.mathmaster;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    Animation tAnim, bAnim;
    ImageView img;
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setUpToolbar();

        tAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bAnim = AnimationUtils.loadAnimation(this, R.anim.botm_anim);

        img = findViewById(R.id.image1);
        text1 = findViewById(R.id.t1);
        text2 = findViewById(R.id.t2);

        img.setAnimation(tAnim);
        text1.setAnimation(tAnim);
        text2.setAnimation(tAnim);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
