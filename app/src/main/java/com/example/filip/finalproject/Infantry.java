package com.example.filip.finalproject;

import android.content.Context;

public class Infantry extends Units {

    public static int GreenAttack = 4; // Attack value of Green's infantry, this value can be changed
    public static int GreenDefence = 1; // Defence value of Green's infantry, this value can be changed
    public static int GreenHP = 10; // HP value of Green's infantry, this value can be changed
    public static int GreenMovement = 4; // Movement value of Green's infantry, this value can be changed

    Infantry(Context context, int x, int y, Player player) {
        super(context,x,y,player, "Infantry", GreenAttack, GreenDefence, GreenHP, GreenHP, GreenMovement);
    }
}
