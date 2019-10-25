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

import utils.ImageConverter;

public class NameAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<One_line_message> lst;

    public NameAdapter(Context context, int layout, List<One_line_message> lst) {
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
        TextView txtTen = (TextView) convertView.findViewById(R.id.ten);
        TextView txtmessage = (TextView) convertView.findViewById(R.id.lastmess);
        ImageView avatar = (ImageView) convertView.findViewById(R.id.img1);

        ImageView stt = (ImageView) convertView.findViewById(R.id.status);

        One_line_message one = lst.get(position);
        txtTen.setText(one.getName());
        txtmessage.setText(one.getLastmess());
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),one.getImg());
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 600);
        avatar.setImageBitmap(circularBitmap);
        if (one.isStatus()== true) {
            stt.setImageResource(R.drawable.status_icon);
        }
        return convertView;

        
    }
}
