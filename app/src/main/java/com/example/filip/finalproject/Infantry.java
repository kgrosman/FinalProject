package com.example.filip.finalproject;

import android.content.Context;

public class Infantry extends Units {

    // TODO : fix these comments
    public static int GreenAttack1 = 11; //  Green infantry's first attack value, this value can be changed
    public static int GreenAttack2 = 2; //  Green infantry's second attack value, this value can be changed
    public static int GreenFirstAttackRange = 1; //  Green infantry's first attack range, this value can be changed
    public static int GreenSecondAttackRange = 4; //  Green infantry's second attack value, this value can be changed
    public static int GreenDefence = 0; // Defence value of Green's infantry, this value can be changed
    public static int GreenHP = 4; // HP value of Green's infantry, this value can be changed
    public static int GreenMovement = 2; // Movement value of Green's infantry, this value can be changed

    Infantry(Context context, int x, int y, Player player) {
        super(context,x,y,player, "Infantry", GreenAttack1,GreenAttack2,GreenFirstAttackRange, GreenSecondAttackRange, GreenDefence, GreenHP, GreenHP, GreenMovement);
    }
}
