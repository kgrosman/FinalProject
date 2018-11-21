package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private GameEngine grid = null;

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
        grid = new GameEngine(BitmapFactory.decodeResource(getResources(), R.mipmap.grid, o));
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
        }
    }
}
