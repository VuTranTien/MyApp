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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_tab_chat);
        btnsend = (ImageButton) findViewById(R.id.btnsend);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(oneTabChatActivity.this,"thisss",Toast.LENGTH_SHORT).show();
            }
        });
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.meo);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 500);

        ImageView circularImageView = (ImageView) findViewById(R.id.imageView);
        circularImageView.setImageBitmap(circularBitmap);
    }
}
