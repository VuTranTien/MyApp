package com.example.myapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;

import ClientServer.Client;
import ClientServer.Server;
import PeerToPeer.MyMessage;
import PeerToPeer.ServerThread;
import utils.ImageConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;

import PeerToPeer.PeerThread;

public class oneTabChatActivity extends AppCompatActivity {
    ImageButton btnsend;
    ImageView img_avatar_OneTabChat;
    ImageButton btn_chooseImage;
    ImageButton btn_callVideo;
    EditText edt_messageContent;
    Context context = this;
    ArrayList<MyMessage> Listmsg;


    static Server server;
    static Client client;
    static ArrayList<MyMessage> listMessages;
    static ArrayList<Client> many_clients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_tab_chat);
        try {
            Anhxa();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void Anhxa() throws IOException {
        btnsend = (ImageButton) findViewById(R.id.btnsend);
        btn_callVideo = (ImageButton) findViewById(R.id.btn_callVideol);
        btn_chooseImage = (ImageButton) findViewById(R.id.btn_chooseImage); 
        img_avatar_OneTabChat = (ImageView) findViewById(R.id.img_avatar_OneTapChat);
        edt_messageContent = (EditText) findViewById(R.id.edt_messageContent);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.meo);
                                            //Todo: Thay R.drawable.meo bằng ảnh lấy từ Bundle
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 500);
        ImageView circularImageView = (ImageView) findViewById(R.id.img_avatar_OneTapChat);
        circularImageView.setImageBitmap(circularBitmap);
        ServerThread serverThread = new ServerThread(server.getPort());
        serverThread.start();

        peer p = new peer(server,many_clients,this.context);
//        p.startChat(edt_messageContent);
        p.updateListenToPeers(edt_messageContent,server.getHostName(),serverThread,Listmsg);
        

    }
    public static class peer{
        Server server;
        ArrayList<Client> many_clients;
        Context context;

        public peer(Server server, ArrayList<Client> many_clients, Context context ) {
            this.server = server;
            this.many_clients = many_clients;
            this.context = context;
        }
//        public void startChat(ServerThread serverThread) throws IOException{
//
//
//            updateListenToPeers(bufferedReader,server.getHostName(),serverThread,);
//        }
        public void updateListenToPeers(EditText edt_messageContent,String username, ServerThread serverThread,ArrayList<MyMessage> lst_message) throws IOException {

            for(int i=0; i<=many_clients.size();i++){
                Socket socket = null;
                try{
                    socket = new Socket(many_clients.get(i).getHostName(),Integer.valueOf(many_clients.get(i).getPort()));
                    new PeerThread(socket,lst_message).start();
                }catch (Exception e){
                    if (socket!=null) {
                        socket.close();
                    }
                    else {
                        //Todo:
                    }
                }
            }
            communicate(edt_messageContent,username,serverThread);

        }
        public void communicate(EditText edt_messageContent, String username, ServerThread serverThread){
            try{
                boolean flag = true;
                while(flag){
                    String message = edt_messageContent.getText().toString();
                    StringWriter stringWriter = new StringWriter();
                    Json.createWriter(stringWriter).writeObject(Json.createObjectBuilder().add("username",username).add("message",message).build());
                    serverThread.sendMessage(message);
                }
                System.exit(0);
            }catch (Exception e){

            }

        }
        
    }
}
