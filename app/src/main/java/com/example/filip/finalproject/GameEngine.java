package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.util.EventLog;
import android.graphics.Canvas;

// Class that will run the game and manage all the events.
public class GameEngine {

    // Place to store the coordinates of last tap. lastTap[0] is x, lastTap[1] is y.
    static int[] lastTap = new int[]{100, 100};

    public Bitmap image; // Image of the grid
    public static SelectedUnit selected = null; // Selected unit, reference is different than  the (selected) unit itself.
    public static Object[][] BoardSprites = new Object[15][9]; //A 2D array of Objects (Units) that stores the units for game engine and data processing, unlike GameView's Units[] units this isn't involved in drawing units.

    //Constructor that creates the unit and it's image, doesn't set it's coordinates. Mostly used by onDraw function in GameView
    public GameEngine(Bitmap bmp) {
        image = bmp;
    }

    // no idea what this is for, but code doesn't work without this for some reason.
    public void update() {
        return;
    }

    //draws the board when grid.dwar(canvas) is called in GameView function.
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 0.0f, 0.0f, null);
    }
    /*
    A method that will process what happens with user's input (click/tap).
     */
    public static void tapProcessor (int x, int y) {


        if (x/128 >= 15 || y/128 >=9 ) { // If tap is outside the grid, do nothing for now. This will be changed later.
            return;
        }

        if (lastTap[0] / 128 == x / 128 && lastTap[1] / 128 == y / 128)  { // if this tap was on the same square coordinates (see function below) as the last tap, do nothing. This makes sure that unit gets tapped only once.
            return;
        }
        lastTap[0] = x; //sets the lastTap coordinates
        lastTap[1] = y;

        if (BoardSprites[x/128][y/128] != null && selected == null) { //If no unit is selected and user taps on a unit, select it.
            selected = new SelectedUnit(GameView.theContext,x , y);
        }

        if (BoardSprites[x/128][y/128] == null && selected != null) { //If user taps on an empty square while some unit is selected, un-select the unit
            selected = null;
        }
    }

    //returns the SquareCoordinates of coordinates. Every Square coordinate represents a square on the board, since every square is 128 pixels the coordinates have to be divided by 128.
    public static int[] getSquareCoordinates (int a, int b) {
        int[] toReturn = new int[] { a / 128, b / 128};
        return toReturn;
    }
}
