package com.example.memorism;

import android.Manifest;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.memorism.memory.MemoryContent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class create_memory extends AppCompatActivity {

    double lat;
    double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceate_journey);
        ActivityCompat.requestPermissions(create_memory.this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION},123);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView take_show_picture = (ImageView) findViewById(R.id.take_picture_view);
        take_show_picture.setOnLongClickListener(new View.OnLongClickListener() {
                                                     @Override
                                                     public boolean onLongClick(View v) {
                                                         Snackbar.make(v, "you have clicked the picture !", Snackbar.LENGTH_LONG)
                                                                 .setAction("Action", null).show();
                                                         return true;
                                                     }
                                                 }


        );

        take_show_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hey long click to open the camera !", Snackbar.LENGTH_LONG)
                        .show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.quick_add_memory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GPStracker g = new GPStracker(getApplicationContext());
                Location l = g.getLocation();
                if(l != null)
                {
                    lat = l.getLatitude();
                    lon = l.getLongitude();

                    EditText text = (EditText)findViewById(R.id.memory_input_string);
                    String memory_string_value= text.getText().toString();

                    text = (EditText)findViewById(R.id.detail_input_string);
                    String detail_string_value = text.getText().toString();

                    String new_id = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());

                    MemoryContent.MemoryItem tmp_dummy = new MemoryContent.MemoryItem(new_id,memory_string_value,detail_string_value,lat,lon);

                    MemoryContent.addItem(tmp_dummy);

                    Snackbar.make(view, "Memory "+memory_string_value + " saved !", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();




                }
            };
        });

    }
}
