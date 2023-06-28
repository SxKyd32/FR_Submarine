package com.example.myapplication;

import android.content.Context;
import android.graphics.Rect;
import android.view.ViewGroup;


public class UpBar extends Bar {


    public UpBar(Context context, ViewGroup viewGroup) {
        super(context, viewGroup);


        int step = random.nextBoolean() ? 250 : -250;

        srcRect = new Rect(0, 0 +1500 + step, bitmap.getWidth(), bitmap.getHeight());
        dstRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight() - 1500 - step);
        //layout(getWidth() - bitmap.getWidth(), 0, getWidth(), getHeight());



    }

}
