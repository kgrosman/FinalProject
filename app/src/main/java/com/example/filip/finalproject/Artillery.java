package com.example.filip.finalproject;

import android.content.Context;

public class Artillery extends Units {

    public static int GreenAttack = 4; // Attack value of Green's infantry, this value can be changed
    public static int GreenDefence = 1; // Defence value of Green's infantry, this value can be changed
    public static int GreenHP = 10; // HP value of Green's infantry, this value can be changed
    public static int GreenMovement = 2; // Movement value of Green's infantry, this value can be changed

    Artillery(Context context, int x, int y, Player player) {
        super(context,x,y,player, "Artillery", GreenAttack, GreenDefence, GreenHP, GreenHP, GreenMovement);
    }
}
