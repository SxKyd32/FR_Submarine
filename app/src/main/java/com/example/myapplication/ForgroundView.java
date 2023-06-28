package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ForgroundView extends FrameLayout {

    // private Context context;
    BoatView boatView;
   // ObjectAnimator a;
    ObjectAnimator b;

    public ForgroundView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        boatView = new BoatView(context);
    }


    // 出场动画
    public void start() {
        removeAllViews();//不确定
        addView(boatView);

        AnimatorSet set = new AnimatorSet();
        ObjectAnimator a = ObjectAnimator.ofFloat(boatView, "y", 0, 500);
        ObjectAnimator b = ObjectAnimator.ofFloat(boatView, "rotation", 0, 360);
        set.setDuration(1000);
        set.play(a).with(b);
        set.start();
    }

    // 移动
    public void move(int x, int y) {
        // 获取潜水艇当前的坐标
        float curX = boatView.getX();
        float curY = boatView.getY();

        // 移动
        AnimatorSet set = new AnimatorSet();

        // 旋转
      //  a= ObjectAnimator.ofFloat(boatView, "rotation", boatView.getRotation(), (float) Math.toDegrees(Math.atan((y - curY) / 200 )));
       // a.setDuration(100);

        // 平移
        Path path = new Path();
        path.moveTo(curX, curY);
        path.lineTo(x, y);
        b = ObjectAnimator.ofFloat(boatView, "x", "y", path);
        b.setDuration(200);

        set.play(b);
        set.start();

    }
    public void stop()
    {
     // a.pause();
      b.pause();



    }



}
