//package com.example.myapp;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//import PeerToPeer.MyMessage;
//
//public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
//    public static final int MSG_TYPE_LEFT = 0;
//    public static final int MSG_TYPE_RIGHT = 1;
//
//
//
//    private Context mContext;
//    private List<MyMessage> myMessages;
//
//    public MessageAdapter(Context mContext, List<MyMessage> myMessages) {
//        this.mContext = mContext;
//        this.myMessages = myMessages;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.my_message,parent,false);
//
//
//        return new MessageAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        MyMessage one_message = myMessages.get(position);
//        holder.message.setText(one_message.getContent());
//        holder.time.setText(one_message.getTime());
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return myMessages.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        public TextView message;
//        public TextView time;
//        public ViewHolder(View itemView){
//            super(itemView);
//            message = itemView.findViewById(R.id.txtMyMessage);
//            time = itemView.findViewById(R.id.txtMyMessageTime);
//        }
//
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if(myMessages.get(position).getSender().equals())
//
//    }
//}
package com.example.myapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Model.Message;
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    public static final int MSG_FROM_FRIEND = 0;
    public static final int MSG_FROM_ME = 1;
    private Context mContext;
    private List<Message> messageList;
    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<Message> messageList) {
        this.mContext = mContext;
        this.messageList = messageList;
    }


    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_FROM_ME) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.my_message,parent, false);
            return new MessageAdapter.ViewHolder(view);
            
        }
        else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.friend_message,parent, false);
            return new MessageAdapter.ViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.show_message.setText(message.getMessage());
        holder.show_time.setText(message.getTime());

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView show_message;
        public TextView show_time;

        public ViewHolder(View view) {
            super(view);
            show_message = view.findViewById(R.id.txtmyMessage);
            show_time = view.findViewById(R.id.txt_Mtime);

        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (messageList.get(position).getSender().equals(fuser.getEmail())) {
            return MSG_FROM_ME;
        }
        else return MSG_FROM_FRIEND;
    }
}
