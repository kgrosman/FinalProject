package com.example.filip.finalproject;

import android.content.Context;

public class Cavalry extends Units{

    public static int GreenAttack1 = 4; // Green Cavalry's first attack value, this value can be changed
    public static int GreenAttack2 = 2; //  Green Cavalry's first attack value, this value can be changed
    public static int GreenFirstAttackRange = 1; //  Green Cavalry's first attack range, this value can be changed
    public static int GreenSecondAttackRange = 1; //  Green Cavalry's second attack value, this value can be changed
    public static int GreenDefence = 1; // Defence value of Green's Cavalry, this value can be changed
    public static int GreenHP = 10; // HP value of Green's Cavalry, this value can be changed
    public static int GreenMovement = 6; // Movement value of Green's Cavalry, this value can be changed

    Cavalry(Context context, int x, int y, Player player) {
        super(context,x,y,player, "Cavalry", GreenAttack1, GreenAttack2, GreenFirstAttackRange, GreenSecondAttackRange, GreenDefence, GreenHP, GreenHP, GreenMovement);
    }
}
