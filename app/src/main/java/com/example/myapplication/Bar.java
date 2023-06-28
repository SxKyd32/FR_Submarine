package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;


public class Bar extends View {

    Random random = new Random();

    Bitmap bitmap;
    Rect srcRect;
    Rect dstRect;
    Paint paint;


    public Bar(Context context, ViewGroup viewGroup) {
        super(context);
        // 解码png图片，转换成Bitmap对象
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bar);
        // 图片本身的区域
        srcRect = new Rect(0, 0, 0, 0);
        // 放置的区域
        dstRect = new Rect(0, 0, 0, 0);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // bitmap: 图片对象
        // src: 图片区域
        // dst: 放在屏幕的位置
        // paint：画笔
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
    }
}
