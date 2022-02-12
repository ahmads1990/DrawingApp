package com.example.drawingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    myCanvas myCanvas;
    TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myCanvas = new myCanvas(this, null);
        setContentView(R.layout.activity_main);

        tabs = (TabLayout) findViewById(R.id.tabLayout);

        tabs.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        Toast.makeText(getApplicationContext(),
                               String.valueOf(tab.getPosition()),
                                Toast.LENGTH_SHORT).show();
                        tabs.getTabAt(tab.getPosition()).getIcon().setAlpha(255);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );


    }

    public void monday (View view)
    {
        Toast.makeText(this, "hehe", Toast.LENGTH_SHORT).show();
    }
}