package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ScoreDB extends SQLiteOpenHelper {   //数据库类

    public ScoreDB(@Nullable Context context) {
        // name: 数据库文件名，不存在，新建；存在，忽略
        super(context, "score.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 数据库文件不存在时，执行（先卸载APP，在安装）
        // 1.建表
        sqLiteDatabase.execSQL("create table score(_id integer primary key autoincrement, _score integer)");


    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // 数据库文件存在时，新版本与以前不一致，执行
    }
}