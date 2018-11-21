package com.example.filip.finalproject;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Picture;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;


public class FullscreenActivity extends Activity implements View.OnClickListener{

    /*
    This method should draw all objects on screen.
     */

    public class test extends View {
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            canvas.drawPaint(paint);
            paint.setTextSize(16);
            canvas.drawText("My Text", GameEngine.lastTap[0], GameEngine.lastTap[1], paint);
        }

        public test(Context context) {
            super(context);
        }
    }
    @Override
    public void onClick(View view) {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
    }
}
