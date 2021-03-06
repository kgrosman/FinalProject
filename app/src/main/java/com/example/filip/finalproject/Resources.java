package com.example.filip.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.EventLog;
import android.content.Context;
import android.graphics.Canvas;

public class Resources {

    /* When creating a new resource type :
    1. create a new class, and create (or copy) stats and constructor
    2. Create an image of your resource and add it as icon in resource class (and in resource's constructor)
    3. Make it yield resources to player class
*/

    public String resourceType;
    public int[] coordinates = new int[]{10, 8};// coordinates of a Resource
    public int[] collectorCoordinates = new int[]{11, 8};// coordinates of a base collecting a resource

    private Bitmap icon; // resource's icon

        public Resources(Context context, int x, int y, int collectorX, int collectorY, String resourceType) {
            if (resourceType.equals("oil")) {
                BitmapFactory.Options o = new Options();
                o.inScaled = false;
                icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.oil_drill, o);
            }
            Resources[] toReturn = new Resources[GameView.resources.length + 1];
            for (int k = 0; k < GameView.units.length; k++) {
                toReturn[k] = GameView.resources[k];
            }
            toReturn[toReturn.length - 1] = this;
            GameView.resources = toReturn;
            GameEngine.BoardResources[x][y] = this;

            //sets the starting coordinates of the resource
            coordinates[0] = x;
            coordinates[1] = y;
            collectorCoordinates[0] = collectorX;
            collectorCoordinates[1] = collectorY;
            this.resourceType = resourceType;
        }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(icon, coordinates[0] * 128, coordinates[1] * 128, null);
    }

    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(icon, x, y, null);
    }
}
