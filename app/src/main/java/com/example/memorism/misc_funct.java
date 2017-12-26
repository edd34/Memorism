package com.example.memorism;

import java.util.Random;

/**
 * Created by farid on 26/12/17.
 */

public class misc_funct {

    public static float randomNumberposXY(int min, int max)
    {
        Random r = new Random();
        float random = min + r.nextFloat() * (max - min);
        return random;
    }
}
