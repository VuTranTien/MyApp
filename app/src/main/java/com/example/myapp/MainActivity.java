package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btndangnhap;
    Button btndangki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

    }
    public void Anhxa(){
        btndangnhap = (Button) findViewById(R.id.btndangnhap);
        btndangki = (Button) findViewById(R.id.btndangki);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_dangnhap = new Intent(MainActivity.this,My_message_window.class);
                startActivity(intent_to_dangnhap);

                //Todo: dùng bundle để gửi dữ liệu người dùng đến My_message_window activity
            }
        });
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_dangki = new Intent(MainActivity.this,DangkiActivity.class);
                startActivity(intent_to_dangki);
                //Todo: upload thông tin đăng kí lên server
            }
        });
    }
}
