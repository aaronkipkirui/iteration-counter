package com.kemoi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int iterationCount;
    private TextView iterationCountTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iterationCountTextView = findViewById(R.id.iterationCountTextView);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Button button = findViewById(R.id.startSecondActivityButton);

        //load iteration count
        iterationCount = sharedPreferences.getInt("iterationCount", 0);
        updateCount();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //increment the iteration count
                iterationCount++;
                //update the TextView
                updateCount();
                //save the count
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("iterationCount", iterationCount);
                editor.apply();
                //start second activity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateCount() {
        iterationCountTextView.setText("Iteration Count: "+iterationCount);
    }
}