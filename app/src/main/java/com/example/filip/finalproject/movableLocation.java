package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.EventLog;
import android.content.Context;
import android.graphics.Canvas;

public class movableLocation {

    public Bitmap icon;

    public movableLocation(Context context) {

        BitmapFactory.Options o = new Options();
        o.inScaled = false;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow, o);
    }

    //draw yellow circles at given coordinates, +37 centers the image
    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(icon, (x*128) + 37,(y*128) + 37, null);
    }
}
