package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;


//Most of the coded code as mentioned in comments is from this class is copied from Java tutorial found online(https://www.youtube.com/watch?v=6prI4ZB_rXI). It's a great tutorial that got me started.

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public static Context theContext; //context of the View, required for adding textures to units in other classes.
    public MainThread thread; //Game's thread. Copied from net, I have no idea why this is important :)
    public GameEngine grid = null; // Grid of the game
    public static SelectedUnit selected = null; //Selected unit
    public static Units[] units = new Units[0]; // Array of units that will be drawn, they don't have the physical location on board (In GameEngine class, BoardSprites does that).

    //also copied from internet, obviously important but I have no idea what it does.
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }

    //another internet copy, doesn't seem to be doing anything except overriding a method.
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //this is where the board and all starting units are initialized (with their respective textures).
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        BitmapFactory.Options o = new Options();
        o.inScaled = false;
        grid = new GameEngine(BitmapFactory.decodeResource(this.getResources(), R.mipmap.grid, o)); // these lines create the board.
        theContext = this.getContext(); // Stores the context, see the variable comment above
        for (int i = 0; i < 3; i++) {
            new Units(theContext, 0, i * 2); // Creates a starting unit. Contexts MUST be passed, or game won't add texture to the unit.
        }

        selected = new SelectedUnit(theContext); // adds selected unit to the board, but doesn't show it until it has to.

        thread.start(); // starts the tread

    }

    //Destroys the (image of) board, also copied from internet.
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    // updates the grid, not sure if this is necessary.
    public void update() {
            grid.update();
    }

    //"Refreshes" the image, or (in other words) re-draws the whole screen.
    @Override
    public void draw (Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            grid.draw(canvas);  //draws the grid first, because that is the bottom layer.

            for (int mn = 0; mn < units.length; mn++) {
                units[mn].draw(canvas); //draws the units from units array found in GameView class.
            }

            if (GameEngine.selected != null) { //If no units are selected yet, do nothing
                GameEngine.selected.draw(canvas); //If they are, draw it.
            }

            //bottom five lines draw the text that displays the tap coordinates, should be removed in final version.
            Paint paint = new Paint();
            paint.setTextSize(60);
            paint.setColor(Color.RED);
            int[] tapCoord = GameEngine.getSquareCoordinates(GameEngine.lastTap[0], GameEngine.lastTap[1]);
            canvas.drawText( tapCoord[0]+ " " + tapCoord[1] + " " + units.length + " " , 2000, 1000, paint);

        }
    }
}
