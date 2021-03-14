package com.example.mathmaster;

import android.content.Context;
import android.content.Intent;

public class settings {

    public Context context;

    settings(Context context) {
        this.context = context;
    }

    public void sound() {

    }

    public void rate() {
        Intent r = new Intent(context.getApplicationContext(), RatingActivity.class);
        context.startActivity(r);
    }

    public void share() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + "com.google.android.whatsapp");
        Intent.createChooser(share, "share app");
        context.startActivity(Intent.createChooser(share, "share using"));
    }
    public void about()
    {
        Intent r1 = new Intent(context.getApplicationContext(), AboutActivity.class);
        context.startActivity(r1);
    }
}
