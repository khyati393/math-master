package com.example.mathmaster;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class settings {

    public Context context;

    settings(Context context) {
        this.context = context;
    }

    public void sound() {

    }

    public void rate() {
        Intent rate = new Intent(Intent.ACTION_VIEW);
        rate.setData(Uri.parse("market://details?id=" + "math master"));
        context.startActivity(rate);
    }

    public void share() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + "com.google.android.whatsapp");
        Intent.createChooser(share, "share app");
        context.startActivity(Intent.createChooser(share, "share using"));
    }
}
