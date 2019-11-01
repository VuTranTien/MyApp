package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;


public class MainActivity extends AppCompatActivity {
    Button btndangnhap;
    Button btndangki;
    EditText editEmail, editPass;
    FirebaseAuth mAuthencation;
    FirebaseUser user;
    String tem="null";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuthencation = FirebaseAuth.getInstance();
        Anhxa();

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
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
    public void Anhxa(){
        btndangnhap = (Button) findViewById(R.id.btndangnhap);
        btndangki   = (Button) findViewById(R.id.btndangki);
        editEmail   = (EditText) findViewById(R.id.edtuser);
        editPass    = (EditText) findViewById(R.id.edtpass);
        editEmail.setText("hsnss@gmai.com");
        editPass.setText("123456");

    }
    private void DangNhap(){
        String TaiKhoan = editEmail.getText().toString();
        String MatKhau = editPass.getText().toString();
        mAuthencation.signInWithEmailAndPassword(TaiKhoan, MatKhau)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            user = mAuthencation.getCurrentUser();
                            getEmail();
                            
                            if (!tem.equals("null")){
                                Intent intent_to_dangnhap = new Intent(MainActivity.this,My_message_window.class);
                                intent_to_dangnhap.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent_to_dangnhap.putExtra("name",tem);
                                startActivity(intent_to_dangnhap);
                                finish();
                            }

                        }

                        else {
                            Toast.makeText(MainActivity.this, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void getEmail() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("List of members!!!");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snaphot : dataSnapshot.getChildren()) {
                    User _user = snaphot.getValue(User.class);
                    if (_user.getEmail().equals(user.getEmail())) {
                        tem = _user.getName();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
