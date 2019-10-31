package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Model.One_line_message;
import Model.User;
import utils.ImageConverter;


public class My_message_window extends AppCompatActivity {

    ListView lsv;
    NameAdapter nameAdapter;
    ImageView img_myavatar;
    ImageButton btn_dangxuat;
    FirebaseUser currentUser;
    DatabaseReference reference;
    private List<One_line_message> mUsers;
    private TextView tenhienthi;
    private FirebaseAuth mAuth;


    public My_message_window context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message_window);
        mUsers = new ArrayList<One_line_message>();
        mAuth = FirebaseAuth.getInstance();
        Intent it = getIntent();

        Anhxa();
        tenhienthi.setText(it.getStringExtra("name"));
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("List of members!!!");
        Toast.makeText(My_message_window.this,currentUser.getEmail(),Toast.LENGTH_SHORT).show();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    assert user != null;
                    assert currentUser != null;

                    if (!user.getEmail().equals(currentUser.getEmail())){
                        mUsers.add(new One_line_message(user.getEmail(),user.getName(),"default","",true,currentUser.getUid()));
                    }
                }

                nameAdapter = new NameAdapter(My_message_window.this,R.layout.oneline_message,mUsers);
                lsv.setAdapter(nameAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Đổ data từ Adapter sa listview
//        readUsers();
        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it;

                if(mUsers.get(position).getStatus() == true) {

                        it = new Intent(My_message_window.this, oneTabChatActivity.class);
                        it.putExtra("friend",mUsers.get(position));
                        startActivity(it);
                        
                }
                else {
                    Toast.makeText(My_message_window.this,"User is Offline",Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        Toast.makeText(My_message_window.this,currentUser.getEmail(),Toast.LENGTH_SHORT).show();

    }
    
    public void Anhxa(){
        lsv = (ListView) findViewById(R.id.lsv_user) ;
        img_myavatar = (ImageView) findViewById(R.id.img_myavatar);
        btn_dangxuat = (ImageButton) findViewById(R.id.btn_dangxuat);
        tenhienthi = (TextView) findViewById(R.id.edt_tenhienthi);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();


        Bitmap my_avatar_bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.default_avatar);
        //Todo: thay R.drawable.default_avatar bằng ảnh lấy từ bundle

        Bitmap my_avatar_circularBitmap = ImageConverter.getRoundedCornerBitmap(my_avatar_bitmap, 500);
        img_myavatar.setImageBitmap(my_avatar_circularBitmap);

    }
    @Override
    public void onBackPressed() {
        Intent back_intent = new Intent(My_message_window.this,MainActivity.class);
        startActivity(back_intent);
    }
//    private void readUsers(){

//    }

}
