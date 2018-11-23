package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.EventLog;
import android.content.Context;
import android.graphics.Canvas;

public class Units {

    private Bitmap icon; // Unit's icon
    private int[] coordinates = new int[]{0, 0}; //Unit's coordinates

    //creates a new unit and initializes icon to Infantry unit. This will be changed later, and is only used in initialization faze atm.
    public Units(Bitmap bmp) {
        icon = bmp;

        // modifies GaveView's units array to add the unit.
        Units[] toReturn = new Units[GameView.units.length + 1];
        for (int k = 0; k < GameView.units.length; k++) {
            toReturn[k] = GameView.units[k];
        }
        toReturn[toReturn.length - 1] = this;
        GameView.units = toReturn;
    }

    //creates a new unit and initializes icon to Infantry unit. It also adds it to GaveView's units array and to GameEngine's Object[][] array.
    public Units(Context context) {

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
    }

    //draws the unit when unit[i].draw(canvas) is called in GameView function.
    public void draw(Canvas canvas) {
        canvas.drawBitmap(icon, coordinates[0] / 128, coordinates[1] / 128, null);
    }

}
