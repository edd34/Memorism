package com.example.memorism;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.memorism.memory.MemoryContent;

import java.io.File;

public class ShowPicture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{

            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            File root= new File(Environment.getExternalStorageDirectory()+File.separator+"MyMemorism");

            ImageView imageview = (ImageView) findViewById(R.id.picture);

            Bitmap bMap = BitmapFactory.decodeFile(root+File.separator+ ItemDetailFragment.mItem.date+".jpg");
            imageview.setImageBitmap(RotateBitmap(bMap,90));
        } catch (Exception e)
        {
            Log.e("SD Card","Error while trying to load picture",e);
        }

    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

}
