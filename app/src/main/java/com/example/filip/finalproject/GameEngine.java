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
    public static Units enemyTappedUnit = null;
    public static SelectedUnit enemySelected = null;
    public static SelectedUnit selected = null; // Selected unit, reference is not the same as the (selected) Unit itself.
    public static Units[][] BoardSprites = new Units[15][9]; //A 2D array of Units that stores the units for game engine and data processing, unlike GameView's Units[] this isn't involved in drawing units.
    public static Player green; //stores the reference to a player that is in charge of Green units
    public static Player red;//stores the reference to a player that is in charge of Red units
    public static Player playing = null;

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
        canvas.drawBitmap(image, -1.0f, -1.0f, null);
    }
    /*
    A method that will process what happens with user's input (click/tap).
     */
    public static void tapProcessor (int x, int y) {

        if (selected != null && x/128 == selected.coordinates[0] && y / 128 == selected.coordinates[1]) { //This makes sure that unit gets tapped only once.
            return;
        }

        if (x / 128 == 16 && y / 128 == 6 && (selected != null || enemySelected  != null)) { //un-selects the unit
            selected = null;
            theUnit = null;
            enemySelected = null;
            enemyTappedUnit = null;
            lastTap[0] = x; //sets the lastTap coordinates
            lastTap[1] = y;
            return;
        }
        if (x / 128 == 16 && y / 128 == 8  && ((lastTap[0] / 128 != x / 128) || (lastTap[1] / 128 != y / 128)) && theUnit == null && enemyTappedUnit == null) { //switches active player.
            if (playing == green) {
                playing = red;
            }
            else if (playing == red) {
                playing = green;
            }
            // gives movement and attack to next player's units
            for (int i = 0; i < BoardSprites.length; i++) {
                for (int j = 0; j < BoardSprites[i].length; j++) {
                    if (BoardSprites[i][j]!= null && BoardSprites[i][j].owner == playing) {
                        BoardSprites[i][j].hasMove = true;
                        BoardSprites[i][j].hasAttack = true;
                    }
                    if (BoardSprites[i][j]!= null && BoardSprites[i][j].owner != playing) {
                        BoardSprites[i][j].hasMove = false;
                        BoardSprites[i][j].hasAttack = false;
                    }
                }
            }
            lastTap[0] = x; //sets the lastTap coordinates
            lastTap[1] = y;
            return;
        }

        lastTap[0] = x; //sets the lastTap coordinates
        lastTap[1] = y;

        if (x / 128 >= 15 || y / 128 >= 9) { // If tap is outside the grid, do nothing.
            return;
        }

        if (selected == null && BoardSprites[x / 128][y / 128] == null) { //if user taps on empty square with no units selected, do nothing
            return;
        }

        //If user taps on his unit, select it, or display info on enemy unit.
        if (BoardSprites[x / 128][y / 128] != null
                && ((BoardSprites[x / 128][y / 128].hasMove == true || BoardSprites[x / 128][y / 128].hasAttack == true) || (BoardSprites[x / 128][y / 128].owner != playing))) {
            if (BoardSprites[x / 128][y / 128].owner == playing) {
                theUnit = BoardSprites[x / 128][y / 128];
                selected = new SelectedUnit(GameView.theContext, x, y, theUnit.owner, theUnit.unitType);
                return;
            }
            else if (BoardSprites[x / 128][y / 128].owner != playing) {
                enemyTappedUnit = BoardSprites[x / 128][y / 128];
                enemySelected = new SelectedUnit(GameView.theContext, x, y, BoardSprites[x / 128][y / 128].owner, BoardSprites[x / 128][y / 128].unitType);
            }

        }
        if (theUnit != null && BoardSprites[x / 128][y / 128] == null && //if user taps with unit selected on an empty square, move it TODO : make sure unit cannot move over another unit
                (theUnit.movement >= getSquareDistance           //also check if unit is in range.
                        (getCoordinates(theUnit)[0], x / 128,
                                getCoordinates(theUnit)[1], y / 128))
                && theUnit.hasMove == true) {
            moveTo(theUnit, x / 128, y / 128); //and then move the unit, and un-select it.
            if (theUnit.hasAttack) { //if unit has attack, don't un-select it yet. TODO : if no units are in range, un-select it because it cannot attack anyway
                theUnit.hasMove = false;
                return;
            }
            if (!(theUnit.hasAttack)) { //if units doesn't have an attack, un-select it
                theUnit.hasMove = false;
                selected = null;
                theUnit = null;
                return;
            }
        }

        if (theUnit != null && BoardSprites[x / 128][y / 128] != null &&
                BoardSprites[x / 128][y / 128].owner != theUnit.owner && //if user taps with unit selected on an opponent's unit, attack it
                (theUnit.attack1Range >= getSquareDistance           //and check if unit is in range of first (stronger) attack.
                        (getCoordinates(theUnit)[0], x / 128,
                                getCoordinates(theUnit)[1], y / 128))
                && theUnit.hasAttack == true) {
            DamageUnit(BoardSprites[x / 128][y / 128].attack1, BoardSprites[x / 128][y / 128], x / 128, y / 128); //and then move the unit, and un-select it.
            if (theUnit.hasMove) { //if unit has a move, don't un-select it yet.
                theUnit.hasAttack = false;
                return;
            }
            if (!theUnit.hasMove) {  //if units doesn't have a move, un-select it
                theUnit.hasAttack = false;
                selected = null;
                theUnit = null;
                return;
            }
        }

        if (theUnit != null && BoardSprites[x / 128][y / 128] != null &&
                BoardSprites[x / 128][y / 128].owner != theUnit.owner && //if user taps with unit selected on an opponent's unit, attack it
                (theUnit.attack2Range >= getSquareDistance           //and check if unit is in range of second (weaker) attack.
                        (getCoordinates(theUnit)[0], x / 128,
                                getCoordinates(theUnit)[1], y / 128))
                && theUnit.hasAttack == true) {
            DamageUnit(theUnit.attack2, BoardSprites[x / 128][y / 128], x / 128, y / 128);
            if (theUnit.hasMove) {
                theUnit.hasAttack = false;
                return;
            }
            if (!theUnit.hasMove) {
                theUnit.hasAttack = false;
                selected = null;
                theUnit = null;
                return;
            }
        }

        if (selected != null) { //If user taps on a square that is out of range while some unit is selected, do nothing
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
        selected = new SelectedUnit(GameView.theContext, x * 128, y * 128, theUnit.owner, theUnit.unitType);
        BoardSprites[a][b] = null;
    }

    public static void DamageUnit(int damage, Units u, int x, int y) { //damages the unit at given coordinates

        BoardSprites[x][y].HP -= (damage - BoardSprites[x][y].defence);

        if (BoardSprites[x][y].HP <= 0) { //if unit has less than 1HP, remove it
            BoardSprites[x][y] = null;
            for (int i = 0; i < GameView.units.length; i++) {
                if (GameView.units[i] == u) {
                    GameView.removeSprite(i);
                    enemyTappedUnit = null;
                    GameView.enemySelected = null;
                    return;
                }
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
