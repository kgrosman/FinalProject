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

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public static Context theContext;
    public MainThread thread;
    public GameEngine grid = null;
    public static SelectedUnit selected = null;
    public static Units[] units = new Units[0];

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        BitmapFactory.Options o = new Options();
        o.inScaled = false;
        grid = new GameEngine(BitmapFactory.decodeResource(this.getResources(), R.mipmap.grid, o));
        theContext = this.getContext();
        new Units(theContext);
        selected = new SelectedUnit(theContext);

        thread.start();

    }

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

    public void update() {
            grid.update();
    }

    @Override
    public void draw (Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            grid.draw(canvas);

            for (int j = 0; j < units.length; j++) {
                units[j].draw(canvas);
            }

            if (GameEngine.selected != null) {
                GameEngine.selected.draw(canvas);
            }

            Paint paint = new Paint();
            paint.setTextSize(60);
            paint.setColor(Color.RED);
            int[] tapCoord = GameEngine.getSquareCoordinates(GameEngine.lastTap[0], GameEngine.lastTap[1]);
            canvas.drawText( tapCoord[0]+ " " + tapCoord[1] + " " + units.length , GameEngine.lastTap[0], GameEngine.lastTap[1], paint);

        }
    }
}
