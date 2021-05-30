package com.example.hhhhhh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hhhhhh.SellItem;

import java.util.ArrayList;
import java.util.List;

public class SellAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SellItem> sellItemList = new ArrayList<SellItem>();

    public SellAdapter() {

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

        }

        //convertView = LayoutInflater.from(context).inflate(R.layout.chatroomlist,null);
        TextView title = (TextView) convertView.findViewById(R.id.sell_title);
        TextView price = (TextView) convertView.findViewById(R.id.sell_price);
        TextView school = (TextView) convertView.findViewById(R.id.sell_school);

        SellItem item = sellItemList.get(position);
        school.setText(item.getSchool());
        title.setText(item.getTitle());
        price.setText(item.getPrice());
        return convertView;
    }

    public void addItem(SellItem item) {
        sellItemList.add(item);
    }


}
