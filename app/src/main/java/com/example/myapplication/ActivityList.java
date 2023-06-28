package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityList extends AppCompatActivity {

    ListView listView ;
    ScoreDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);

        ScoreDB  dbHelper = new ScoreDB(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("select _score from score order by _score  desc",null);
        //定位游标
        c.moveToFirst();
        c.getInt(0);
        List<Map<String,Object>> data = new ArrayList<>();

        //row1   存入第一行排行榜数据
        Map<String,Object> row1 = new HashMap<>();
        row1.put("img",R.drawable.a);
        row1.put("score",c.getInt(0) + "m");
        data.add(row1);

        //row2   存入第二行排行榜数据
        Map<String,Object> row2 = new HashMap<>();
        c.moveToNext();
        row2.put("img",R.drawable.b);
        row2.put("score",c.getInt(0) + "m");
        data.add(row2);

        //row3   存入第三行排行榜数据
        Map<String,Object> row3 = new HashMap<>();
        c.moveToNext();
        row3.put("img",R.drawable.c);
        row3.put("score",c.getInt(0) + "m");
        data.add(row3);

        //row4
        Map<String,Object> row4 = new HashMap<>();
        c.moveToNext();
        row4.put("img",R.drawable.d);;
        row4.put("score",c.getInt(0) + "m");
        data.add(row4);

        //row5
        Map<String,Object> row5 = new HashMap<>();
        c.moveToNext();
        row5.put("img",R.drawable.e);;
        row5.put("score",c.getInt(0) + "m");
        data.add(row5);

        //row6
        Map<String,Object> row6 = new HashMap<>();
        c.moveToNext();
        row6.put("img",R.drawable.f);;
        row6.put("score",c.getInt(0) + "m");
        data.add(row6);

        //row7
        Map<String,Object> row7 = new HashMap<>();
        c.moveToNext();
        row7.put("img",R.drawable.g);;
        row7.put("score",c.getInt(0) + "m");
        data.add(row7);

        //row8
        Map<String,Object> row8 = new HashMap<>();
        c.moveToNext();
        row8.put("img",R.drawable.h);;
        row8.put("score",c.getInt(0) + "m");
        data.add(row8);

        //row9
        Map<String,Object> row9 = new HashMap<>();
        c.moveToNext();
        row9.put("img",R.drawable.i);;
        row9.put("score",c.getInt(0) + "m");
        data.add(row9);

        //row10
        Map<String,Object> row10 = new HashMap<>();
        c.moveToNext();
        row10.put("img",R.drawable.j);
        row10.put("score",c.getInt(0) + "m");
        data.add(row10);

        c.close();
        db.close();
        //行布局
        //R.layout.layout_item;
        //绑定
        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.list_item,   //Context:上下文
                new String[]{"img",/*"name",*/"score"},                                         //data:数据
                new int[]{R.id.imageView,R.id.textView1/*,R.id.textView2*/});           //layout_item :行布局
        // String[] : 数据data中每行的key
        //  int[]  :  行布局控件id,必须与参数4对应
        listView.setAdapter(adapter);  //  绑定适配器

    }



}
