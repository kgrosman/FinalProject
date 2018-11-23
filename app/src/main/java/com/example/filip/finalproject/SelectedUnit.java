package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Canvas;

//selected unit on the board
public class SelectedUnit {

    private Bitmap icon; // Unit's icon
    private int[] coordinates = new int[]{0, 0}; //Unit's coordinates

    SelectedUnit(Context context,int x ,int y) { //Creates a selected unit on given coordinates
        coordinates[0] = x; //sets the unit at coordinates
        coordinates[1] = y;

        if (GameEngine.BoardSprites[x/128][y/128] instanceof Units) { //if selected unit is Green Infantry, use infgs (INFantry Green Selected) texture
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.infgs, o);
            return;
        }

        BitmapFactory.Options o = new BitmapFactory.Options(); //if selected unit is Unknown, null texture instead of trying to draw with null and crashing the app. Null texture should never appear, because it's caused by a bug!
        o.inScaled = false;
        icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.nulll, o);
    }

    SelectedUnit(Context context) { //creates the selected unit with "null" texture, used by GameView to create a selected unit variable before any unit is selected. Null texture should never appear, because it's caused by a bug!
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.nulll, o);
    }

    //draws the selected unit when selected.draw(canvas) is called in GameView function.
    public void draw(Canvas canvas) {
        canvas.drawBitmap(icon, coordinates[0] / 128, coordinates[1] / 128, null);
    }
}
