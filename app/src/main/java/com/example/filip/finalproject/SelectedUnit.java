package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Canvas;

public class SelectedUnit {

    private Bitmap icon;
    private int[] coordinates = new int[]{0, 0};

    SelectedUnit(Context context,int x ,int y) {
        coordinates[0] = x;
        coordinates[1] = y;

        if (GameEngine.BoardSprites[x/128][y/128] instanceof Units) {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inScaled = false;
            icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.infgs, o);
            return;
        }

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.nulll, o);
    }

    SelectedUnit(Context context) {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.nulll, o);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(icon, coordinates[0] / 128, coordinates[1] / 128, null);
    }
}
