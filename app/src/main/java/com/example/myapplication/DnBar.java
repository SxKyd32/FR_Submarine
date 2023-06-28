package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.ViewGroup;

public class DnBar extends Bar {

    Bitmap bitmap_2;
    Rect srcRect_2;
    Rect dstRect_2;
    Paint paint_2;

    public DnBar(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);

        Resources res = this.getContext().getResources();
        bitmap = BitmapFactory.decodeResource(res, R.drawable.bar);
        Matrix matrix = new Matrix();
        matrix.postRotate(180); // 翻转180度
        bitmap_2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);


        srcRect_2 = new Rect(0, 0, 0, 0);
        dstRect_2 = new Rect(0, 0, 0, 0);

        paint_2 = new Paint();

    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(bitmap_2, srcRect_2, dstRect_2, paint_2);
    }
}
