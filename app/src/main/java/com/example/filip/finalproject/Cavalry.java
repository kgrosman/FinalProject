package com.example.filip.finalproject;

import android.content.Context;

public class Cavalry extends Units{

    public static int GreenAttack = 4; // Attack value of Green's infantry, this value can be changed
    public static int GreenDefence = 1; // Defence value of Green's infantry, this value can be changed
    public static int GreenHP = 10; // HP value of Green's infantry, this value can be changed
    public static int GreenMovement = 6; // Movement value of Green's infantry, this value can be changed

    Cavalry(Context context, int x, int y, Player player) {
        super(context,x,y,player, "Cavalry", GreenAttack, GreenDefence, GreenHP, GreenHP, GreenMovement);
    }
}
