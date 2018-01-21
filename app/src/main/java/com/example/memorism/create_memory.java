package com.example.memorism;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memorism.memory.MemoryContent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class create_memory extends AppCompatActivity {

    private Spinner spinnerChooseTrip = null;
    private TextView selectTrip;

    double lat;
    double lon;

    public static String new_id = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceate_journey);

        ArrayAdapter<String> adapter;

        /*final Intent intent = getIntent();
        String activity = intent.getExtras().getString("activity");*/
        Intent intent = getIntent();
        String activity = getIntent().getExtras().getString("activity");

        if(activity.equals("create_trip"))
        {
            List<String> m_Maliste = new ArrayList<String>();
                m_Maliste.add(MemoryContent.trip_name_prompted);
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,m_Maliste);

            spinnerChooseTrip = (Spinner) findViewById(R.id.spinner);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerChooseTrip.setAdapter(adapter);
        }
        else if(activity.equals("MainMenu")){
            if(MemoryContent.ITEMS.size()==0)
            {
                Intent intent_create_trip = new Intent(this,create_trip.class);
                startActivity(intent_create_trip);
            }
            else
            {
                adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,MemoryContent.getAllTripName());
                spinnerChooseTrip = (Spinner) findViewById(R.id.spinner);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerChooseTrip.setAdapter(adapter);
                spinnerChooseTrip.setSelection(MemoryContent.getAllTripName().size()-1);
            }
        }
        else
        {
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,MemoryContent.getAllTripName());
            spinnerChooseTrip = (Spinner) findViewById(R.id.spinner);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerChooseTrip.setAdapter(adapter);
            spinnerChooseTrip.setSelection(MemoryContent.getAllTripName().size()-1);
        }





        new_id = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
        ActivityCompat.requestPermissions(create_memory.this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION},123);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent_take = new Intent(this,take_photo.class);

        ImageView take_show_picture = (ImageView) findViewById(R.id.take_picture_view);
        take_show_picture.setOnLongClickListener(new View.OnLongClickListener() {
                                                     @Override
                                                     public boolean onLongClick(View v) {
                                                         startActivity(intent_take);
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
            public void onClick(final View view) {

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



                    MemoryContent.MemoryItem tmp_dummy = new MemoryContent.MemoryItem(spinnerChooseTrip.getSelectedItem().toString(),new_id,memory_string_value,detail_string_value,lat,lon);

                    MemoryContent.addItem(tmp_dummy);

                    Snackbar.make(view, "Memory "+memory_string_value + " saved !", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(view.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(view.getContext());
                    }
                    builder.setTitle("Memory correctly saved")
                            .setMessage("Your memory is correctly saved")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    Intent intent = new Intent(view.getContext(), MainMenu.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    view.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(view.getContext(), MainMenu.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    view.getContext().startActivity(intent);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(false)
                            .show();
                }
            };
        });

    }
}
