package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ScoreActivity extends AppCompatActivity {   //积分类

    boolean isPause = false;
    boolean isStop = true;
    TextView textView ;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) { //初始化界面，开始游戏

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textView = findViewById(R.id.textView_score);
        this.start();

    }

    public void start()  {

        final Thread thread = Thread.currentThread();


        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               try {
                                   thread.sleep(3000);
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                               runOnUiThread(new Runnable() {


                                   @Override
                                   public void run() {
                                       int n = Integer.parseInt(textView.getText().toString()) + 1;
                                       textView.setText( n + "");
                                   }
                               });

                           }        }
                , 1000,1000);
    }


    public void finish(View view) {

        timer.cancel(); //结束游戏 ，结束积分进程

        new AlertDialog.Builder(this)    //弹窗提示成绩
                .setMessage("  你一共前进了： " + textView.getText().toString() + " 米 ")
                .setPositiveButton("确定",null)
                .setCancelable(false)
                .show();


        ScoreDB  dbHelper = new ScoreDB(this);     //定义并打开数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into scores (_score) values (?)",new String[] {textView.getText().toString()});
        //存入分数
    }


    public void BtnJMP(View view) { //打开成绩排行榜
        Intent i = new Intent(this,ActivityList.class);
        startActivity(i);

    }


}


