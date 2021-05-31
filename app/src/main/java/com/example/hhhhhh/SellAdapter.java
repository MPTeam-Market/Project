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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hhhhhh.SellItem;

import java.util.ArrayList;
import java.util.List;

public class SellAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SellItem> sellItemList;
    private Activity activity;

    public SellAdapter(Activity activity, ArrayList<SellItem> myDataset) {
        this.activity = activity;
        this.sellItemList = myDataset;
    }

    @Override
    public int getCount() {
        return sellItemList.size();
    }

    @Override
    public SellItem getItem(int position) {
        return sellItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public TextView t;
        public TextView p;
        public TextView s;
        public ImageView i;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sr_item, parent, false);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, Sellcontent.class);
                    intent.putExtra("postInfo", sellItemList.get(position));
                    activity.startActivity(intent);
                }
            });

            holder = new ViewHolder();
            holder.t = (TextView) convertView.findViewById(R.id.sell_title);
            holder.p = (TextView) convertView.findViewById(R.id.sell_price);
            holder.s = (TextView) convertView.findViewById(R.id.sell_school);
            holder.i = (ImageView) convertView.findViewById(R.id.sellimg);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SellItem item = sellItemList.get(position);

        holder.t.setText("학교: " + item.getSchool());
        holder.p.setText("제품명: " + item.getTitle());
        holder.s.setText("가격: " + item.getPrice());
        if (item.getImg() != "") {
            holder.i.setScaleType(ImageView.ScaleType.FIT_XY);
            holder.i.setAdjustViewBounds(true);

            Glide.with(convertView).load(item.getImg()).override(1000).into(holder.i);
        }
        else{
            Glide.with(convertView).load(R.drawable.box).override(1000).into(holder.i);
        }

        return convertView;
    }

    public void addItem(SellItem item) {
        sellItemList.add(item);
    }


}
