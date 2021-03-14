package com.example.mathmaster;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class RatingActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        setUpToolbar();

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.play);

        ratingBar = findViewById(R.id.rb);
        button = findViewById(R.id.RBB);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                String s = String.valueOf(ratingBar.getRating());
                if (ratingBar.getRating() > 0) {
                    Toast.makeText(getBaseContext(), "Thank you for " + s + " Star", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            }
        });
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
        Objects.requireNonNull(getSupportActionBar()).setTitle("Rate Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
