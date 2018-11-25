package com.example.filip.finalproject;

import android.content.Context;

public class Artillery extends Units {

    public static int GreenAttack1 = 4; // Attack value of Green's infantry, this value can be changed
    public static int GreenAttack2 = 2;
    public static int GreenFirstAttackRange = 6;
    public static int GreenSecondAttackRange = 8;
    public static int GreenDefence = 1; // Defence value of Green's infantry, this value can be changed
    public static int GreenHP = 10; // HP value of Green's infantry, this value can be changed
    public static int GreenMovement = 2; // Movement value of Green's infantry, this value can be changed

    Artillery(Context context, int x, int y, Player player) {
        super(context,x,y,player, "Artillery", GreenAttack1,GreenAttack2, GreenFirstAttackRange, GreenSecondAttackRange, GreenDefence, GreenHP, GreenHP, GreenMovement);
    }
}
