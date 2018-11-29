package com.example.filip.finalproject;

import android.content.Context;

public class Artillery extends Units {

    // TODO : fix these comments

    public static int GreenAttack1 = 50; // Green Cavalry's first attack value, this value can be changed
    public static int GreenAttack2 = 4; //  Green Cavalry's first attack value, this value can be changed
    public static int GreenFirstAttackRange = 1; //  Green Cavalry's first attack range, this value can be changed
    public static int GreenSecondAttackRange = 6; //  Green Cavalry's second attack value, this value can be changed
    public static int GreenDefence = 2; // Defence value of Green's Cavalry, this value can be changed
    public static int GreenHP = 7; // HP value of Green's Cavalry, this value can be changed
    public static int GreenMovement = 1; // Movement value of Green's Cavalry, this value can be changed


    public static int RedAttack1 = 50; // Red Cavalry's first attack value, this value can be changed
    public static int RedAttack2 = 4; //  Red Cavalry's first attack value, this value can be changed
    public static int RedFirstAttackRange = 1; //  Red Cavalry's first attack range, this value can be changed
    public static int RedSecondAttackRange = 6; //  Red Cavalry's second attack value, this value can be changed
    public static int RedDefence = 2; // Defence value of Red's Cavalry, this value can be changed
    public static int RedHP = 7; // HP value of Red's Cavalry, this value can be changed
    public static int RedMovement = 1; // Movement value of Red's Cavalry, this value can be changed


    Artillery(Context context, int x, int y, Player player) {
        super(context, x, y, player, "Artillery");
        if (player.color.equals("green")) {
            this.setParameters(GreenAttack1, GreenAttack2, GreenFirstAttackRange, GreenSecondAttackRange, GreenDefence, GreenHP, GreenHP, GreenMovement);
        } else {
            this.setParameters(RedAttack1, RedAttack2, RedFirstAttackRange, RedSecondAttackRange, RedDefence, RedHP, RedHP, RedMovement);
        }

    }
}
