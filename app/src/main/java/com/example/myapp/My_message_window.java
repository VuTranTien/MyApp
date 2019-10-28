package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import utils.ImageConverter;

public class My_message_window extends AppCompatActivity {

    ListView lsv;
    List<One_line_message> lst;
    NameAdapter adapter;
    ImageView img_myavatar;
    ImageButton btn_dangxuat;


    public My_message_window context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message_window);
        Anhxa();

        //Đổ data từ Adapter sa listview
        adapter = new NameAdapter(this,R.layout.oneline_message,lst);
        lsv.setAdapter(adapter);
        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it;
                if(lsv.getSelectedView().findViewById(R.id.status)==null)  {
                    it = new Intent(My_message_window.this, oneTabChatActivity.class);
                    startActivity(it);

                }
            }
        });

        btn_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: đăng xuất & chuyển sang main_activity



            }
        });
    }
    public void Anhxa(){
        lsv = (ListView) findViewById(R.id.lsv_user) ;
        img_myavatar = (ImageView) findViewById(R.id.img_myavatar);
        btn_dangxuat = (ImageButton) findViewById(R.id.btn_dangxuat);

        Bitmap my_avatar_bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.default_avatar);
        //Todo: thay R.drawable.default_avatar bằng ảnh lấy từ bundle

        Bitmap my_avatar_circularBitmap = ImageConverter.getRoundedCornerBitmap(my_avatar_bitmap, 500);
        img_myavatar.setImageBitmap(my_avatar_circularBitmap);

        lst = new ArrayList<One_line_message>();
        lst.add(new One_line_message("Tiến Vũ","hello!",R.drawable.meo,true));
        lst.add(new One_line_message("My friend","what are you doing?",R.drawable.meo,false));
    }

}
