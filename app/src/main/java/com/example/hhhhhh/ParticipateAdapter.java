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

public class ParticipateAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ParticipateItem> ParticipateItemList;
    private Activity activity;

    public ParticipateAdapter(Activity activity, ArrayList<ParticipateItem> myDataset) {
        this.activity = activity;
        this.ParticipateItemList = myDataset;
    }

    @Override
    public int getCount() {
        return ParticipateItemList.size();
    }

    @Override
    public ParticipateItem getItem(int position) {
        return ParticipateItemList.get(position);
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
            convertView = inflater.inflate(R.layout.participate_item, parent, false);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, Sellcontent.class);
                    intent.putExtra("postInfo", ParticipateItemList.get(position));
                    activity.startActivity(intent);
                }
            });

        }

        //convertView = LayoutInflater.from(context).inflate(R.layout.chatroomlist,null);
        TextView title = (TextView) convertView.findViewById(R.id.participate_title);
        TextView price = (TextView) convertView.findViewById(R.id.participate_limitmember);
        TextView school = (TextView) convertView.findViewById(R.id.participate_school);


        ParticipateItem item = ParticipateItemList.get(position);
        school.setText("학교: "+item.getSchool());
        title.setText(item.getTitle());
        price.setText("작성자:"+item.getParticipateName());

        return convertView;
    }

    public void addItem(ParticipateItem item) {
        ParticipateItemList.add(item);
    }


}