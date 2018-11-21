package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.util.EventLog;
import android.graphics.Canvas;

// Class that will run the game.
public class GameEngine {

    // Place to store the coordinates of last tap. lastTap[0] is x, while lastTap[1] is y.
    static int[] lastTap = new int[]{0, 0};

    private Bitmap image;

    public GameEngine(Bitmap bmp) {
        image = bmp;
    }


    public void update() {
        return;
    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 0.0f, 0.0f, null);
    }
    /*
    A method that will process what happens with user's input (click/tap).
     */
    public static void tapProcessor (int x, int y) {
        lastTap[0] = x;
        lastTap[1] = y;
    }
}
