package com.example.mathmaster;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //write code here
    }

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
                Toast.makeText(this, "sharing clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rating:
                Toast.makeText(this, "rating clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "about clicked", Toast.LENGTH_SHORT).show();
                break;
        }


        return true;
    }
}
