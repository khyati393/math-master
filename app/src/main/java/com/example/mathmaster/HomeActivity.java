package com.example.mathmaster;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;

import com.example.mathmaster.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    AppCompatTextView spinner;
    Settings settings = new Settings(getBaseContext());
    int positionList;
    Button button;
    private ActivityHomeBinding mBinding;
    public static StreakRoomDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        mDatabase = StreakRoomDatabase.getDatabase(getApplication());

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.play);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                nextPage();
            }
        });
        initializeViewsAndListeners();

    }

    // Function for confirm before Exit..
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Really Exit ")
                .setMessage("Are you sure ?")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

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

            case R.id.sharing: {
                if (item.getItemId() == R.id.sharing)
                    settings.share();
            }
            break;

            case R.id.rating: {
                if (item.getItemId() == R.id.rating)
                    settings.rate();
            }
            break;

            case R.id.about: {
                if (item.getItemId() == R.id.about)
                    settings.about();
            }
            break;
        }

        return true;
    }


    private void initializeViewsAndListeners() {

        mBinding.buttonStreakList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StreakBoardActivity.getStartIntent(getBaseContext()));
            }
        });

        mBinding.autoTextArithmeticChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showArithmeticChooseDropdown();
            }
        });


    }

//    private void userSelectionSpinnerSetup() {
//
//        // for drop down
//        spinner = (AppCompatTextView) findViewById(R.id.spinner1);
//
//        final List<String> list = new ArrayList<String>();   //for drop down list
//        list.add(0, "Choose");
//        list.add("Addition");
//        list.add("Subtraction");
//        list.add("Multiplication");
//        list.add("Division");
//
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setPrompt("Select your Choice");
//        //Attaching data adapter to spinner
//        spinner.setAdapter(arrayAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                spinner.setSelection(position);
//                // spinner item text alignment center
//                view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//
//                if (parent.getItemAtPosition(position).equals("Choose")) {
//                    positionList = position;
//                } else {
////                    Toast.makeText(getBaseContext(), "You Selected : " + list.get(position), Toast.LENGTH_SHORT).show();
//                    positionList = position;
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//    }

    /*Branch*/
    private void showArithmeticChooseDropdown() {

        List<String> list = new ArrayList<String>();   //for drop down list
        list.add(0, "Choose");
        list.add("Addition");
        list.add("Subtraction");
        list.add("Multiplication");
        list.add("Division");


        ArrayAdapter adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.layout_only_textview, R.id.textView, list);
        mBinding.autoTextArithmeticChoose.setAdapter(adapter);

        mBinding.autoTextArithmeticChoose.showDropDown();

        mBinding.autoTextArithmeticChoose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                positionList = position;
            }
        });
    }

    public void nextPage() {

        switch (positionList) {

            case 0:
            default:
                Toast.makeText(getBaseContext(), "Please select an arithmetic operation to play", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                Intent i1 = new Intent(getBaseContext(), AdditionActivity.class);
                startActivity(i1);
                break;

            case 2:
                Intent i2 = new Intent(getBaseContext(), SubtractionActivity.class);
                startActivity(i2);
                break;

            case 3:
                Intent i3 = new Intent(getBaseContext(), MultiplicationActivity.class);
                startActivity(i3);
                break;

            case 4:
                Intent i4 = new Intent(getBaseContext(), DivisionActivity.class);
                startActivity(i4);
                break;

        }
    }

}
