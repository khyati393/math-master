package com.example.mathmaster;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DivisionActivity extends AppCompatActivity {
    int value1;
    int value2;
    int score = 0;
    int temp;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        button=(Button)findViewById(R.id.b1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Right();
            }
        });
        button=(Button)findViewById(R.id.b2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wrong();            }
        });
        setNewNumbers();
    }

    public int Right() {
        TextView Attempt = findViewById(R.id.attempt);
        int userAnswer = Integer.parseInt(Attempt.getText().toString());
        int div = value1 / value2;
        if (userAnswer == div) {
            score++;
            setNewNumbers();
            temp=1;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(DivisionActivity.this);
            builder.setTitle("Game Over!")
                    .setMessage("your score " + score).setPositiveButton("Go To Home", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DivisionActivity.super.onBackPressed();
                }
            })
                    .setNegativeButton("Play Again", null).setCancelable(false);
            setNewNumbers();
            score = 0;

            AlertDialog alert = builder.create();
            alert.show();
             temp=0;
        }

        return temp;
    }


    private void setNewNumbers() {
        int random;
        value1 = random = (int) ((Math.random() * 9) + 1);
        value2 = random = (int) ((Math.random() * 9) + 1);
        int k = random = (int) ((Math.random() * 2) + 1);
        int j = random = (int) ((Math.random() * 19) + 1);
        TextView Number1 = findViewById(R.id.num1);
        Number1.setText("" + value1);
        TextView Number2 = findViewById(R.id.num2);
        Number2.setText("" + value2);
        TextView Attempt = findViewById(R.id.attempt);
        int div = (value1 / value2);
        if (k == 2)
            Attempt.setText("" + div);
        else Attempt.setText("" + j);

    }

    public int Wrong() {
        TextView Attempt = findViewById(R.id.attempt);
        int userAnswer = Integer.parseInt(Attempt.getText().toString());
        int div = value1 / value2;
        if (userAnswer != div) {
            score++;
            setNewNumbers();
               temp=1;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(DivisionActivity.this);
            builder.setTitle("Game Over!")
                    .setMessage("your score " + score).setPositiveButton("Go To Home", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DivisionActivity.super.onBackPressed();
                }
            })
                    .setNegativeButton("Play Again", null).setCancelable(false);
            setNewNumbers();
            score = 0;

            AlertDialog alert = builder.create();
            alert.show();
            temp=0;
        }
        return temp;
    }

}
