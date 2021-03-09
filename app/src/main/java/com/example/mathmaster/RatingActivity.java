package com.example.mathmaster;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RatingActivity extends AppCompatActivity {
    RatingBar ratingBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        final MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.play);

        ratingBar = findViewById(R.id.rb);
        button = findViewById(R.id.RBB);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                String s = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), s + "Star", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
