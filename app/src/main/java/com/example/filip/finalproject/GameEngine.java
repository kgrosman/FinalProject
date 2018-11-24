package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.util.EventLog;
import android.graphics.Canvas;
import java.lang.Math;
import java.util.Objects;

// Class that will run the game and manage all the events.
public class GameEngine {

    // Place to store the coordinates of last tap. lastTap[0] is x, lastTap[1] is y.
    static int[] lastTap = new int[]{16000, 16000};

    public Bitmap image; // Image of the grid
    public static Units theUnit = null; // Selected unit, unlike the selected unit in GameView class this unit is the actual selected unit.
    public static SelectedUnit selected = null; // Selected unit, reference is not the same as the (selected) Unit itself.
    public static Units[][] BoardSprites = new Units[15][9]; //A 2D array of Units that stores the units for game engine and data processing, unlike GameView's Units[] this isn't involved in drawing units.
    public static Player green;
    public static Player red;


    //Constructor that creates the unit and it's image, doesn't set it's coordinates. Mostly used by onDraw function in GameView
    public GameEngine(Bitmap bmp) {
        image = bmp;
    }

    // no idea what this is for, but code doesn't work without this for some reason.
    public void update() {
        return;
    }

    //draws the board when grid.draw(canvas) is called in GameView function.
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 0.0f, 0.0f, null);
    }
    /*
    A method that will process what happens with user's input (click/tap).
     */
    public static void tapProcessor (int x, int y) {


        if (x / 128 >= 15 || y / 128 >= 9) { // If tap is outside the grid, do nothing. This will be changed later.
            return;
        }

        if (lastTap[0] / 128 == x / 128 && lastTap[1] / 128 == y / 128) { // if this tap was on the same square coordinates (see function below) as the last tap, do nothing. This makes sure that unit gets tapped only once.
            return;
        }

        lastTap[0] = x; //sets the lastTap coordinates
        lastTap[1] = y;

        if (selected == null && BoardSprites[x / 128][y / 128] == null) { //if user taps on empty square with no units selected, do nothing
            return;
        }

        if (BoardSprites[x / 128][y / 128] != null && theUnit == null) { //If no unit is selected and user taps on a unit, select it.
            theUnit = BoardSprites[x / 128][y / 128];
            selected = new SelectedUnit(GameView.theContext, x, y, theUnit.owner);
            return;
        }
        if (theUnit != null && BoardSprites[x / 128][y / 128] == null && //if user taps with unit selected on an empty square, move it
                (theUnit.movement >= getSquareDistance           //also check if unit is in range.
                        (getCoordinates(theUnit)[0], x / 128,
                                getCoordinates(theUnit)[1], y / 128))) {
            moveTo(theUnit, x / 128, y / 128); //and then move the unit, and un-select it.
            selected = null;
            theUnit = null;
            return;
        }

        if (theUnit != null && BoardSprites[x / 128][y / 128] != null &&
                BoardSprites[x / 128][y / 128].owner != theUnit.owner && //if user taps with unit selected on an opponent's unit, attack it
                (theUnit.movement >= getSquareDistance           //and check if unit is in range.
                        (getCoordinates(theUnit)[0], x / 128,
                                getCoordinates(theUnit)[1], y / 128))) {
            killUnit(BoardSprites[x / 128][y / 128], x / 128, y / 128); //and then move the unit, and un-select it.
            selected = null;
            theUnit = null;
            return;
            }

        if (selected != null) { //If user taps on a square that is out of range while some unit is selected, un-select the unit
            selected = null;
            theUnit = null;
            return;
        }

    }

    public static int[] getCoordinates (Units u) { //gets the coordinates of the unit.
        for (int i = 0; i < BoardSprites.length; i++) {
            for (int j = 0; j < BoardSprites[i].length; j++) {
                if (BoardSprites[i][j] == u) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    public static void moveTo(Units u, int x, int y) { //moves the unit to x and y
        int a = 0, b = 0;
        for (int i = 0; i < BoardSprites.length; i++) {
            for (int j = 0; j < BoardSprites[i].length; j++) {
                if (BoardSprites[i][j] == u) {
                    a = i;
                    b = j;
                    break;
                }
            }
        }
        u.moveTo(x,y);
        BoardSprites[x][y] = u;
        BoardSprites[a][b] = null;
    }

    public static void killUnit(Units u, int x, int y) { //kills the unit at given coordinates
        BoardSprites[x][y] = null;
        for (int i = 0; i < GameView.units.length; i++) {
            if (GameView.units[i] == u) {
                GameView.removeSprite(i);
                return;
            }
        }
    }

    //returns the SquareCoordinates of coordinates. Every Square coordinate represents a square on the board, since every square is 128 pixels the coordinates have to be divided by 128.
    public static int[] getSquareCoordinates (int a, int b) {
        int[] toReturn = new int[] { a / 128, b / 128};
        return toReturn;
    }


    //returns the distance between two coordinates, calculated by summing up difference between x coordinates and difference between y coordinates.
    public static int getSquareDistance(int x1, int x2, int y1, int y2) {
        int deltaX = x1 - x2; //Math.abs doesn't seem to work on phones properly :(
        if (deltaX < 0) {
            deltaX *= - 1;
        }
        int deltaY = y1 - y2;
        if (deltaY < 0) {
            deltaY *= - 1;
        }
        return deltaX + deltaY;
    }
}
