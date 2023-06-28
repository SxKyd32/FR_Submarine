package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class BoatView extends View {
    Bitmap bmp = null;
    Paint paint = null;
    Rect srcRect = null;
    Rect dstRect = null;

    public BoatView(Context context) {
        super(context);



        paint = new Paint();
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.avator);
        srcRect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        dstRect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bmp, srcRect, dstRect, paint);
    }
}
