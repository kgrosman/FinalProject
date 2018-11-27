package com.example.filip.finalproject;

import android.content.Context;

public class Artillery extends Units {

    // TODO : fix these comments
    public static int GreenAttack1 = 50; // Attack value of Green's infantry, this value can be changed
    public static int GreenAttack2 = 4;
    public static int GreenFirstAttackRange = 1;
    public static int GreenSecondAttackRange = 6;
    public static int GreenDefence = 2; // Defence value of Green's infantry, this value can be changed
    public static int GreenHP = 7; // HP value of Green's infantry, this value can be changed
    public static int GreenMovement = 1; // Movement value of Green's infantry, this value can be changed

    Artillery(Context context, int x, int y, Player player) {
        super(context,x,y,player, "Artillery", GreenAttack1,GreenAttack2, GreenFirstAttackRange, GreenSecondAttackRange, GreenDefence, GreenHP, GreenHP, GreenMovement);
    }
}
