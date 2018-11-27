package com.example.filip.finalproject;

public class Player {

    public String color; //Player's color
    public int[] upgrades = new int[10]; //Player's upgrades, empty and unused for now

    Player (String str) {
        color = str;
    }
    public static String print(Player p) {
        return p.color;
    }
}
