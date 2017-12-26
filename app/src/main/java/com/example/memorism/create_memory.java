package com.example.memorism;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.memorism.memory.MemoryContent;

public class create_memory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceate_journey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.quick_add_memory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText text = (EditText)findViewById(R.id.memory_input_string);
                String memory_string_value= text.getText().toString();

                text = (EditText)findViewById(R.id.detail_input_string);
                String detail_string_value = text.getText().toString();

                String new_id = String.valueOf(MemoryContent.ITEMS.size());

                MemoryContent.MemoryItem tmp_dummy = new MemoryContent.MemoryItem(new_id,memory_string_value,detail_string_value);

                MemoryContent.addItem(tmp_dummy);

                Snackbar.make(view, "Memory "+memory_string_value + " saved !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();




            }
        });
    }

}
