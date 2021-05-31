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

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

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

        }

        //convertView = LayoutInflater.from(context).inflate(R.layout.chatroomlist,null);
        TextView title = (TextView) convertView.findViewById(R.id.sell_title);
        TextView price = (TextView) convertView.findViewById(R.id.sell_price);
        TextView school = (TextView) convertView.findViewById(R.id.sell_school);
        ImageView img = (ImageView) convertView.findViewById(R.id.sellimg);

        SellItem item = sellItemList.get(position);
        school.setText("학교: "+item.getSchool());
        title.setText("제품명: "+item.getTitle());
        price.setText("가격: "+item.getPrice());
        if(item.getImg() != "" && item.getImg() != null){
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setAdjustViewBounds(true);

            Glide.with(convertView).load(item.getImg()).override(1000).into(img);
        }
        return convertView;
    }

    public void addItem(SellItem item) {
        sellItemList.add(item);
    }


}
