package com.example.memorism;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

import java.util.Random;

/**
 * Created by farid on 26/12/17.
 */

public class misc_funct {

    public static double randomNumberposXY(int min, int max)
    {
        Random r = new Random();
        float random = min + r.nextFloat() * (max - min);
        return random;
    }




}
