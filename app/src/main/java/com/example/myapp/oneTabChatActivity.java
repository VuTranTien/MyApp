package com.example.myapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.StringWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Model.Message;
import Model.One_line_message;
import Model.User;
import utils.ImageConverter;

import javax.json.Json;

public class oneTabChatActivity extends AppCompatActivity {
    ImageButton btnsend;
    ImageView img_avatar_OneTabChat;
    ImageButton btn_chooseImage;
    ImageButton btn_callVideo;
    EditText edt_messageContent;
    TextView txt_clientname;
    Context context = this;
    FirebaseUser me;
    One_line_message friend;
    List<Message> messageList;
    RecyclerView recyclerView;
    DatabaseReference reference;
    MessageAdapter messageAdapter;
    ImageButton btn_backchat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_tab_chat);
        Intent it = getIntent();
        friend = (One_line_message) it.getSerializableExtra("friend");

        me = FirebaseAuth.getInstance().getCurrentUser();
        if (me == null){
            Toast.makeText(oneTabChatActivity.this,"NULL",Toast.LENGTH_SHORT).show();
        }
        try {
            Anhxa();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt_messageContent.getText().toString().equals("")){
                    sendMessage(me.getEmail(),friend.getEmail(),edt_messageContent.getText().toString());
                }
                else {
                    Toast.makeText(oneTabChatActivity.this,"Nhập tin nhắn",Toast.LENGTH_SHORT).show();
                }

            }
        });

        reference = FirebaseDatabase.getInstance().getReference("List of members!!!").child(friend.getUID());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readMessage();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
        btn_backchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(oneTabChatActivity.this,My_message_window.class);
                startActivity(it1);
            }
        });
    }
    public void Anhxa() throws IOException {
        btn_backchat = (ImageButton) findViewById(R.id.btn_backchat);
        btnsend = (ImageButton) findViewById(R.id.btnsend);
        btn_callVideo = (ImageButton) findViewById(R.id.btn_callVideol);
        btn_chooseImage = (ImageButton) findViewById(R.id.btn_chooseImage); 
        img_avatar_OneTabChat = (ImageView) findViewById(R.id.img_avatar_OneTapChat);
        txt_clientname = (TextView) findViewById(R.id.txt_clientname);
        txt_clientname.setText(friend.getName());
        edt_messageContent = (EditText) findViewById(R.id.edt_messageContent);
        recyclerView = (RecyclerView) findViewById(R.id.RC_Compose);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),friend.getImage().equals("default")?R.drawable.meo:1);
                                            //Todo: Thay R.drawable.meo bằng ảnh lấy từ Bundle
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 500);
        ImageView circularImageView = (ImageView) findViewById(R.id.img_avatar_OneTapChat);
        circularImageView.setImageBitmap(circularBitmap);

    }
    private void sendMessage(String sender,String receiver, String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        hashMap.put("time",formatter.format(date));
        reference.child("ChatsContent").push().setValue(hashMap);
        edt_messageContent.setText("");

    }
    private void readMessage(){
        messageList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("ChatsContent");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Message mes = snapshot.getValue(Message.class);
                    if (mes == null) Toast.makeText(oneTabChatActivity.this,"Message is NULL",Toast.LENGTH_SHORT).show();
                    if((mes.getReceiver().equals(me.getEmail()) && mes.getSender().equals(friend.getEmail())) || (mes.getReceiver().equals(friend.getEmail()) && mes.getSender().equals(me.getEmail()))){
                        messageList.add(mes);
                    }
                    messageAdapter = new MessageAdapter(oneTabChatActivity.this,messageList);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
        
}
