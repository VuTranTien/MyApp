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



import java.util.List;

import PeerToPeer.MyMessage;
import utils.ImageConverter;

public class MessageAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<MyMessage> lst;

    public MessageAdapter(Context context, int layout, List<MyMessage> lst) {
        this.context = context;
        this.layout = layout;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInf.inflate(layout,null);

        //ánh xạ

        if (layout == R.layout.my_message){
            TextView txtMyMessage = (TextView) convertView.findViewById(R.id.txtMyMessage);


            MyMessage one = lst.get(position);
            txtMyMessage.setText(one.getContent());

        }
        else if(layout == R.layout.friend_message){
            TextView txtFriend_mesage =  (TextView) convertView.findViewById(R.id.txtFriend_message);

            MyMessage one = lst.get(position);
            txtFriend_mesage.setText(one.getContent());



        }
        return convertView;


    }

}
