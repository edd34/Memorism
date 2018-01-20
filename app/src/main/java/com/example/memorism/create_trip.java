package com.example.memorism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.memorism.memory.MemoryContent;

public class create_trip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        final Intent intent_create_memory = new Intent(this,create_memory.class);
        Button button_createNewTrip = (Button) findViewById(R.id.button_done_trip_name_create);

        final EditText text = (EditText)findViewById(R.id.trip_name_input_string);


        button_createNewTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                MemoryContent.trip_name_prompted= text.getText().toString();
                startActivity(intent_create_memory);
            }
        });
    }
}
