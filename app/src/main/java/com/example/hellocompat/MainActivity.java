package com.example.hellocompat;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mHelloTextView;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloTextView = (TextView) findViewById(R.id.hello_textview);

        if(savedInstanceState != null) {
            mHelloTextView.setTextColor(savedInstanceState.getInt("color"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color", mHelloTextView.getCurrentTextColor());
    }

    public void changeColor(View view) {
        Random random = new Random();

        String colorName = mColorArray[random.nextInt(20)];
        int colorResourseName = getResources().getIdentifier(colorName,
                "color", getApplicationContext().getPackageName());
        System.out.println(Build.VERSION.SDK_INT);
        if(Build.VERSION.SDK_INT > 23){
            int colorRes= ContextCompat.getColor(this, colorResourseName);
            mHelloTextView.setTextColor(colorRes);
        } else {
            Toast.makeText(this, "Buy a new phone!", Toast.LENGTH_LONG).show();
        }
    }
}
