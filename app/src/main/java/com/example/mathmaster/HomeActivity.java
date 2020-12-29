package com.example.mathmaster;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Spinner spinner;
    settings settings = new settings(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //write code here
        // for drop down
        spinner = (Spinner) findViewById(R.id.spinner1);

        final List<String> list = new ArrayList<String>();   //for drop dwon list
        list.add(0, "Choose");
        list.add("Addition");
        list.add("Subtraction");
        list.add("Multiplication");
        list.add("Division");



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setPrompt("Select your Choice");
        //Attaching data adapter to spinner
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
                // spinner item text alignment center
                view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                if (parent.getItemAtPosition(position).equals("Choose")) {
                    //do nothing
                } else
                    Toast.makeText(spinner.getContext(), "You Selected : " + list.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Function for confirme before Exit..
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Really Exit ")
                .setMessage("Are you sure ?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HomeActivity.super.onBackPressed();
            }
        })
                .setNegativeButton("Cancel", null).setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }

    //for Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.sound:
                Toast.makeText(this, "sound clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.theme:
                Toast.makeText(this, "theme clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.sharing:
                if (item.getItemId() == R.id.sharing)
                    settings.share();
                break;

            case R.id.rating:
                if (item.getItemId() == R.id.rating)
                    settings.rate();

                break;
            case R.id.about:
                Toast.makeText(this, "about clicked", Toast.LENGTH_SHORT).show();
                break;
        }


        return true;
    }
}
