package com.example.mathmaster;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AdditionActivity extends AppCompatActivity {

    int value1;
    int value2;
    int score = 0;
    Button button;
    MediaPlayer mediaPlayer, mediaPlayer1;
    private StreakDao mStreakDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        mediaPlayer = MediaPlayer.create(this, R.raw.right);
        mediaPlayer1 = MediaPlayer.create(this, R.raw.wrong);
        button = (Button) findViewById(R.id.b1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Right();
            }
        });
        button = (Button) findViewById(R.id.b2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wrong();
            }
        });
        setNewNumbers();

        mStreakDao = StreakRoomDatabase.getDatabase(getApplication()).streakDao();

    }

    public void Right() {

        TextView Attempt = findViewById(R.id.attempt);
        int userAnswer = Integer.parseInt(Attempt.getText().toString());
        int sum = value1 + value2;

        if (userAnswer == sum) {
            score++;
            mediaPlayer.start();
            setNewNumbers();
        } else {
            mediaPlayer1.start();
            AlertDialog.Builder builder = new AlertDialog.Builder(AdditionActivity.this);
            builder.setTitle("Game Over!")
                    .setMessage("your score " + score)
                    .setCancelable(false)
                    .setPositiveButton("Go To Home", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (score > 0) {
                                insertStreakNote(new StreakEntity(UUID.randomUUID().toString(), score, "Addition", System.currentTimeMillis()), false);
                            } else {
                                onBackPressed();
                            }
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            if (score > 0) {
                                insertStreakNote(new StreakEntity(UUID.randomUUID().toString(), score, "Addition", System.currentTimeMillis()), true);
                            }
                            dialog.dismiss();
                        }
                    });


            AlertDialog alert = builder.create();
            alert.show();

        }

    }

    private void setNewNumbers() {

        int random;
        value1 = random = (int) ((Math.random() * 9) + 1);
        value2 = random = (int) ((Math.random() * 9) + 1);
        int k = random = (int) ((Math.random() * 2) + 1);
        int j = random = (int) ((Math.random() * 39) + 1);
        TextView Number1 = findViewById(R.id.num1);
        Number1.setText("" + value1);
        TextView Number2 = findViewById(R.id.num2);
        Number2.setText("" + value2);
        TextView Attempt = findViewById(R.id.attempt);
        int sum = value1 + value2;
        if (k == 2)
            Attempt.setText("" + sum);
        else Attempt.setText("" + j);

    }

    public void Wrong() {

        TextView Attempt = findViewById(R.id.attempt);
        int userAnswer = Integer.parseInt(Attempt.getText().toString());
        int sum = value1 + value2;

        if (userAnswer != sum) {
            score++;
            mediaPlayer.start();
            setNewNumbers();
        } else {

            mediaPlayer1.start();
            AlertDialog.Builder builder = new AlertDialog.Builder(AdditionActivity.this);
            builder.setTitle("Game Over!")
                    .setCancelable(false)
                    .setMessage("your score = " + score)
                    .setPositiveButton("Go To Home", new DialogInterface.OnClickListener() {
                        @SuppressLint("CheckResult")
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            if (score > 0) {
                                insertStreakNote(new StreakEntity(UUID.randomUUID().toString(), score, "Addition", System.currentTimeMillis()), false);
                            } else {
                                onBackPressed();
                            }
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Play Again", new DialogInterface.OnClickListener() {
                        @SuppressLint("CheckResult")
                        @Override
                        public void onClick(final DialogInterface dialog, int i) {
                            if (score > 0) {
                                insertStreakNote(new StreakEntity(UUID.randomUUID().toString(), score, "Addition", System.currentTimeMillis()), true);
                            }
                            dialog.dismiss();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @SuppressLint("CheckResult")
    private void insertStreakNote(final StreakEntity streakEntity, final boolean isPlayAgain) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {

                long str = mStreakDao.insert(streakEntity);
                if (!TextUtils.isEmpty(String.valueOf(str))) {
                    e.onNext(String.valueOf(str));
                } else {
                    e.onError(new Exception());
                }

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String success) throws Exception {

                        if (isPlayAgain) {
                            setNewNumbers();
                            score = 0;
                        } else {
                            onBackPressed();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getBaseContext(), "Error with insertion in database at Addition", Toast.LENGTH_LONG).show();
                        throwable.printStackTrace();
                    }
                });

    }

}


