package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DangkiActivity extends AppCompatActivity {
    Button btn_xacnhandangki;
    EditText editTK, editMK, editTen;
    ImageView imgHinh;
    int REQUEST_CODE_IMAGE = 1;

    FirebaseAuth mAuthencation;
    private DatabaseReference mData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        mAuthencation = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();

        Anhxa();

        btn_xacnhandangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });

        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data){
        if(requestcode == REQUEST_CODE_IMAGE && resultcode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestcode, resultcode, data);
    }
    public void Anhxa(){
        btn_xacnhandangki = (Button) findViewById(R.id.btn_xacnhandangki);
        editTen           = (EditText) findViewById(R.id.edt_tenhienthi);
        editTK            = (EditText) findViewById(R.id.edt_emaildangki);
        editMK            = (EditText) findViewById(R.id.edt_passdangki);
        imgHinh           = (ImageView) findViewById(R.id.img_avatardangki);

    }
    private void DangKy(){
        final String name = editTen.getText().toString();
        final String email = editTK.getText().toString();
        final String password = editMK.getText().toString();
        mAuthencation.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(DangkiActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent_to_message = new Intent(DangkiActivity.this,My_message_window.class);
                            startActivity(intent_to_message);
                            DangKyThanhVien dk = new DangKyThanhVien(name, email, password);
                            mData.child("List of members!!!").push().setValue(dk);
                        }
                        else {
                            Toast.makeText(DangkiActivity.this, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

