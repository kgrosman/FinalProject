package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.util.EventLog;
import android.graphics.Canvas;

// Class that will run the game.
public class GameEngine {

    // Place to store the coordinates of last tap. lastTap[0] is x, lastTap[1] is y.
    static int[] lastTap = new int[]{100, 100};

    public Bitmap image;
    public static SelectedUnit selected = null;
    public static Object[][] BoardSprites = new Object[15][9];

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
        if (lastTap[0] / 128 == x / 128 && lastTap[1] / 128 == y / 128)  {
            return;
        }
        lastTap[0] = x;
        lastTap[1] = y;

        if (BoardSprites[x/128][y/128] != null && selected == null) {
            selected = new SelectedUnit(GameView.theContext,x , y);
        }

        if (BoardSprites[x/128][y/128] == null && selected != null) {
            selected = null;
        }
    }

    public static int[] getSquareCoordinates (int a, int b) {
        int[] toReturn = new int[] { a / 128, b / 128};
        return toReturn;
    }
}
