package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.EventLog;
import android.content.Context;
import android.graphics.Canvas;

public class Units {

    private Bitmap icon; // Unit's icon
    public String unitType;
    public int[] coordinates = new int[]{14, 8}; //Unit's coordinates, because this is the coordinates of the image value must be multiplied by 128 to display on board properly.
    public Player owner;
    public int attack;
    public int defence;
    public int HP;
    public int maxHP;
    public int movement;

    //Two methods below are from previous versions of the code, I might need them again later.
    /*public Units(Bitmap bmp) {
        icon = bmp;

        // modifies GaveView's units array to add the unit.
        Units[] toReturn = new Units[GameView.units.length + 1];
        for (int cv = 0; cv < GameView.units.length; cv++) {
            toReturn[cv] = GameView.units[cv];
        }
        toReturn[toReturn.length - 1] = this;
        GameView.units = toReturn;
    }*/

    /*public Units(Context context) {

        BitmapFactory.Options o = new Options();
        o.inScaled = false;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.infg, o);
        Units[] toReturn = new Units[GameView.units.length + 1];
        for (int k = 0; k < GameView.units.length; k++) {
            toReturn[k] = GameView.units[k];
        }
        toReturn[toReturn.length - 1] = this;
        GameView.units = toReturn;
        GameEngine.BoardSprites[coordinates[0]][coordinates[1]] = this;
    }*/

    //creates a new unit and initializes icon to Infantry unit, and sets the owner. It also adds it to GaveView's units array and to GameEngine's Object[][] array.
    public Units(Context context, int x, int y, Player player, String unitType, int atc, int def, int hp, int maxhp, int mov) {

        this.attack = atc;
        this.defence = def;
        this.HP = hp;
        this.maxHP = maxhp;
        this.movement = mov;
        this.unitType = unitType;
        this.owner = player;
        if (owner == GameEngine.green && unitType.equals("Infantry")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.infg, o);
        }
        if (owner == GameEngine.red && unitType.equals("Infantry")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.infr, o);
        }

        coordinates[0] = x;
        coordinates[1] = y;

        Units[] toReturn = new Units[GameView.units.length + 1];
        for (int k = 0; k < GameView.units.length; k++) {
            toReturn[k] = GameView.units[k];
        }
        toReturn[toReturn.length - 1] = this;
        GameView.units = toReturn;
        GameEngine.BoardSprites[coordinates[0]][coordinates[1]] = this;
    }

    //changes the coordinates of the unit
    public void moveTo(int x, int y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }
    //draws the unit when unit[i].draw(canvas) is called in GameView function.
    public void draw(Canvas canvas) {
        canvas.drawBitmap(icon, coordinates[0] * 128, coordinates[1] * 128, null);
    }

}
