package com.example.mathmaster;

import android.content.Context;
import android.content.Intent;

public class Settings {

    public Context mContext;

    Settings(Context context) {
        mContext = context;
    }

    public void sound() {

    }

    public void rate() {
        Intent r = new Intent(mContext.getApplicationContext(), RatingActivity.class);
        mContext.startActivity(r);
    }

    public void share() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + "com.google.android.whatsapp");
        Intent.createChooser(share, "share app");
        mContext.startActivity(Intent.createChooser(share, "Share 'Math Master' via"));
    }

    public void about() {
        Intent aboutUs = new Intent(mContext.getApplicationContext(), AboutActivity.class);
        mContext.startActivity(aboutUs);
    }
}
