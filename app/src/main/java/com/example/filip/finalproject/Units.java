package com.example.filip.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;

/* When creating a new unit type :
    1. create a new class, and create (or copy) stats and constructor
    2. Create images (green, red, selected and regular) of your unit and add it as icon in units class (and in unit's constructor) and SelectedUnit class
    3. Make it available to buy/start with
*/
public class Units {

    private Bitmap icon; // Unit's icon
    public String unitType; // type of unit, for example if this unit is infantry, string will equal "Infantry"
    public int[] coordinates = new int[]{14, 8}; //Unit's coordinates, because this is the coordinates of the image value must be multiplied by 128 to display on board properly.
    public Player owner; //Player that owns the figure
    public boolean hasMove = true; // can unit move this turn
    public boolean hasAttack = true; // can unit attack this turn
    public int attack1; //Damage done by first attack.
    public int attack2; // Damage done by second attack, usually does less damage but has higher range then first attack.
    public int attack1Range; //Range of first attack.
    public int attack2Range; // Range of second attack.
    public int defence; //Unit's defence value. Damage to HP is calculated by HP -= (damage - defence). For example, if unit has 3hp, 1 defence and gets attacked by unit which has 2 attack damage, new HP will be 2.
    public int HP; //Current HP of the unit
    public int maxHP; //Max HP of the unit
    public int movement; //Movement distance of the unit


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
    public void setParameters(int atc1, int atc2, int atc1r, int atc2r,int def, int hp, int maxhp, int mov) {
        this.attack1 = atc1;
        this.attack2 = atc2;
        this.attack1Range = atc1r;
        this.attack2Range = atc2r;
        this.defence = def;
        this.HP = hp;
        this.maxHP = maxhp;
        this.movement = mov;
    }

    //creates a new unit, initialize icon to it's unit type, attribute attack, defence and HP values, and set the owner. It also adds it to GaveView's units array and to GameEngine's Object[][] array.
    public Units(Context context, int x, int y, Player player, String unitType) {

        //see above for meaning of these values

        this.unitType = unitType;
        this.owner = player;

        //these if statements get the icon of constructed unit
        if (owner == GameEngine.green && unitType.equals("Infantry")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.infg2, o);
        }
        if (owner == GameEngine.red && unitType.equals("Infantry")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.infr2, o);
        }
        if (owner == GameEngine.green && unitType.equals("Cavalry")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.cavg2, o);
        }
        if (owner == GameEngine.red && unitType.equals("Cavalry")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.cavr2, o);
        }
        if (owner == GameEngine.green && unitType.equals("Artillery")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.artg2, o);
        }
        if (owner == GameEngine.red && unitType.equals("Artillery")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.artr2, o);
        }
        if (owner == GameEngine.green && unitType.equals("Headquaters")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.hqg, o);
        }
        if (owner == GameEngine.red && unitType.equals("Headquaters")) {
            BitmapFactory.Options o = new Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.hqr, o);
        }

        //sets the starting coordinates of the unit
        coordinates[0] = x;
        coordinates[1] = y;

        //adds the unit to GameView.units array, which stores units that have to be drawn.
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

    //Same as above, but draw then on given coordinates instead of unit's current coordinates.
    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(icon, x, y, null);
    }
}
