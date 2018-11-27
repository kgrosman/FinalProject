package com.example.filip.finalproject;

import android.content.Context;

public class Headquaters extends Units{

    // TODO : fix these comments
    public static int GreenAttack1 = 50; //  Green infantry's first attack value, this value can be changed
    public static int GreenAttack2 = 1; //  Green infantry's second attack value, this value can be changed
    public static int GreenFirstAttackRange = 1; //  Green infantry's first attack range, this value can be changed
    public static int GreenSecondAttackRange = 4; //  Green infantry's second attack value, this value can be changed
    public static int GreenDefence = 2; // Defence value of Green's infantry, this value can be changed
    public static int GreenHP = 20; // HP value of Green's infantry, this value can be changed
    public static int GreenMovement = 0; // Movement value of Green's infantry, this value can be changed

    Headquaters(Context context, int x, int y, Player player) {
        super(context,x,y,player, "Headquaters", GreenAttack1,GreenAttack2,GreenFirstAttackRange, GreenSecondAttackRange, GreenDefence, GreenHP, GreenHP, GreenMovement);
    }
}
