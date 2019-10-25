package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DangkiActivity extends AppCompatActivity {
    Button btn_xacnhandangki;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);

        Anhxa();

        btn_xacnhandangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo:  gửi thông tin đăng kí lên firebase


                //Chuyển đến menu chat
                Intent intent_to_dangnhap = new Intent(DangkiActivity.this,My_message_window.class);
                startActivity(intent_to_dangnhap);
            }
        });
    }
    public void Anhxa(){
        btn_xacnhandangki = (Button) findViewById(R.id.btn_xacnhandangki);

    }
}

