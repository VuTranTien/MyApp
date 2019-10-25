package com.example.myapp;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import utils.ImageConverter;

public class oneTabChatActivity extends AppCompatActivity {
    ImageButton btnsend;
    ImageView img_avatar_OneTabChat;
    ImageButton btn_chooseImage;
    ImageButton btn_callVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_tab_chat);
        Anhxa();
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(oneTabChatActivity.this,"thisss",Toast.LENGTH_SHORT).show();
                //Todo: sự kiện cho btn send message
            }
        });



        btn_chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: sự kiện cho btn chọn ảnh từ điện thoại
            }
        });
        btn_callVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: sự kiện cho btn call video
            }
        });
    }
    public void Anhxa(){
        btnsend = (ImageButton) findViewById(R.id.btnsend);
        btn_callVideo = (ImageButton) findViewById(R.id.btn_callVideol);
        btn_chooseImage = (ImageButton) findViewById(R.id.btn_chooseImage); 
        img_avatar_OneTabChat = (ImageView) findViewById(R.id.img_avatar_OneTapChat);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.meo);
                                            //Todo: Thay R.drawable.meo bằng ảnh lấy từ Bundle
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 500);
        ImageView circularImageView = (ImageView) findViewById(R.id.img_avatar_OneTapChat);
        circularImageView.setImageBitmap(circularBitmap);
    }
}
