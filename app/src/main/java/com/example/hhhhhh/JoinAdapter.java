package com.example.hhhhhh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.hhhhhh.JoinItem;

import java.util.ArrayList;
import java.util.List;

public class JoinAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JoinItem> joinItemList;
    private Activity activity;

    public JoinAdapter(Activity activity, ArrayList<JoinItem> myDataset) {
        this.activity = activity;
        this.joinItemList = myDataset;
    }

    @Override
    public int getCount() {
        return joinItemList.size();
    }

    @Override
    public JoinItem getItem(int position) {
        return joinItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public TextView t;
        public TextView p;
        public TextView s;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.join_item, parent, false);

            viewHolder = new ViewHolder();

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, JoinContent.class);
                    intent.putExtra("postInfo", joinItemList.get(position));
                    activity.startActivity(intent);
                }
            });

            viewHolder.t = (TextView) convertView.findViewById(R.id.join_title);
            viewHolder.p = (TextView) convertView.findViewById(R.id.join_price);
            viewHolder.s = (TextView) convertView.findViewById(R.id.join_school);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        JoinItem item = joinItemList.get(position);
        viewHolder.t.setText("학교: "+item.getSchool());
        viewHolder.p.setText(item.getTitle());
        viewHolder.s.setText("작성자:"+item.getWriterName());

        return convertView;
    }

    public void addItem(JoinItem item) {
        joinItemList.add(item);
    }


}
