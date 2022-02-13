package com.example.drawingapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    myCanvas myCanvas;
    TabLayout tabs;
    TabLayout colorTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myCanvas = new myCanvas(this, null);
        setContentView(R.layout.activity_main);

        //color tabs
        colorTabs = (TabLayout) findViewById(R.id.color_tab_layout);
        colorTabs.setVisibility(View.GONE);
        setupColorTabIcons();
        tabs = (TabLayout) findViewById(R.id.tabLayout);

        tabs.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {

                        Log.d("mytag", "shape tag position " + tab.getPosition());
                        myCanvas.changeBrush(tab.getPosition());

                            if (tab.getPosition() == 4){
                                colorTabs.setVisibility(View.VISIBLE);
                            }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        if (tab.getPosition() == 4){
                            colorTabs.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );

        colorTabs.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        Toast.makeText(getApplicationContext(), "color tag position", tab.getPosition());
                        myCanvas.changeColor(tab.getPosition());
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

    private void setupColorTabIcons() {

        colorTabs.getTabAt(0).getIcon().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        colorTabs.getTabAt(1).getIcon().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        colorTabs.getTabAt(2).getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        colorTabs.getTabAt(3).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
    }

}