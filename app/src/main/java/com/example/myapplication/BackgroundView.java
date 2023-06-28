package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arcsoft.face.AgeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;




public class BackgroundView extends FrameLayout {




    Timer timer;
    private Context context;
    Random random = new Random();
    List<ValueAnimator> valueAnimators = new ArrayList<>();
    List<Rect> uprects = new ArrayList<>();
    List<Rect> dnrects = new ArrayList<>();




    public BackgroundView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void start() {

        clearBars();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                post(new Runnable() {
                    @Override
                    public void run() {

                        final int step = random.nextBoolean() ? 200 : 500;

                        final UpBar upBar = new UpBar(context, BackgroundView.this);
                        final DnBar dnBar = new DnBar(context, BackgroundView.this);

                        upBar.srcRect.set(0, 1600 + step, upBar.bitmap.getWidth(), upBar.bitmap.getHeight());
                        upBar.dstRect.set(0, 0, upBar.bitmap.getWidth(), upBar.bitmap.getHeight() - step - 1600);
                        upBar.layout(getWidth(), 0, getWidth(), getHeight());

                        dnBar.srcRect_2.set(0, step - 1600, dnBar.bitmap_2.getWidth(), dnBar.bitmap_2.getHeight());
                        dnBar.dstRect_2.set(0, 0, dnBar.bitmap_2.getWidth(), dnBar.bitmap_2.getHeight() - step + 1600);
                        dnBar.layout(getWidth(), 0, getWidth(), getHeight());


                        addView(upBar);
                        addView(dnBar);

                        // UpBar的平移动画效果
                        // 从getWidth() + barView.bitmap.getWidth() => 0
                        ValueAnimator valueAnimator = ValueAnimator.ofFloat(getWidth() + upBar.bitmap.getWidth(), 0);
                        // valueAnimators.add(valueAnimator);
                        // 时间
                        valueAnimator.setDuration(4500L);
                        // 线性方式
                        valueAnimator.setInterpolator(new LinearInterpolator());
                        // 监听值的变化
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                // 移动BarView
                                float x = (Float) valueAnimator.getAnimatedValue();
                                upBar.dstRect.set((int) x - upBar.bitmap.getWidth(), 0, (int) x, upBar.bitmap.getHeight() - step - 1600);
                                upBar.postInvalidate();

                                uprects.add(upBar.dstRect);




                                if (x < 0 || 0 == x) {
                                    valueAnimator.cancel();
                                    valueAnimators.remove(valueAnimator);
                                    BackgroundView.this.removeView(upBar);
                                }
                            }
                        });


                        // DnBar的平移动画效果
                        ValueAnimator valueAnimator_2 = ValueAnimator.ofFloat(getWidth() + dnBar.bitmap_2.getWidth(), 0);
                        // 时间
                        valueAnimator_2.setDuration(4500L);
                        // 线性方式
                        valueAnimator_2.setInterpolator(new LinearInterpolator());
                        // 监听值的变化
                        valueAnimator_2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator_2) {

                                float y = (Float) valueAnimator_2.getAnimatedValue();
                                dnBar.dstRect_2.set((int) y - dnBar.bitmap_2.getWidth(), 0, (int) y, upBar.bitmap.getHeight() - step + 1600);
                                dnBar.postInvalidate();

                                dnrects.add(dnBar.dstRect_2);


                                if (y < 0 || 0 == y) {
                                    valueAnimator_2.cancel();
                                    valueAnimators.remove(valueAnimator_2);
                                    BackgroundView.this.removeView(dnBar);
                                }

                            }
                        });

                        valueAnimator.start();
                        valueAnimator_2.start();

                        valueAnimators.add(valueAnimator);
                        valueAnimators.add(valueAnimator_2);
                    }
                });
            }
        }, 1000, 1500);
    }

    public void stop() {
        // 暂停背景动画
      //  Log.d("BackgroundView","调用了");
        timer.cancel();


        // 遍历list，对每个动画对象进行pause
        for (int i = 0; i < valueAnimators.size(); i++)
            valueAnimators.get(i).pause();
    }

    private void clearBars() { // 初始化Background

        for (int i = 0; i < valueAnimators.size(); i++)
            valueAnimators.get(i).cancel();

        valueAnimators.clear();
        uprects.clear();
        dnrects.clear();

        removeAllViews();
    }



}
