package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import utils.ImageConverter;

public class My_message_window extends AppCompatActivity {
    ListView lsv;
    List<One_line_message> lst;
    NameAdapter adapter;
    public My_message_window context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message_window);
        anhxa();
        adapter = new NameAdapter(this,R.layout.oneline_message,lst);
        lsv.setAdapter(adapter);
        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it;
                it = new Intent(My_message_window.this, oneTabChatActivity.class);
                startActivity(it);
            }
        });
    }
    public void anhxa(){
        lsv = (ListView) findViewById(R.id.lsv_user) ;
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.meo);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 500);


        lst = new ArrayList<One_line_message>();
        lst.add(new One_line_message("Tiến Vũ","hello!",R.drawable.meo,true));
        lst.add(new One_line_message("My friend","what are you doing?",R.drawable.meo,false));
    }

}
