package PeerToPeer;


import android.content.Context;
import android.widget.Button;
import android.widget.ListView;


import com.example.myapp.MessageAdapter;
import com.example.myapp.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;


import javax.json.Json;
import javax.json.JsonObject;

import ClientServer.Client;

public class PeerThread extends Thread {
    private BufferedReader bufferedReader;
    MessageAdapter messAdapter;
    Context context;

    public PeerThread(Socket socket, ArrayList<MyMessage> listmsg) throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        

    }
    public void run(){
        boolean flag = true;
        while(flag){
            try
            {

                JsonObject jsonObject = Json.createReader(bufferedReader).readObject();
                if (jsonObject.containsKey("username")){
                    //Todo: gọi phương thức khởi tạo cho MessageAdapter
                    
                    MessageAdapter msgAdapter = new MessageAdapter(this.context,R.layout.friend_message,)
                }

            } catch (Exception e){
                flag = false;
                interrupt();
                
            }
        }
    }
}
