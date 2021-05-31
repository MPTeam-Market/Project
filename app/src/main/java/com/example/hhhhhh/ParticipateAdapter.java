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

        final ParticipateAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.participate_item, parent, false);

            viewHolder = new ParticipateAdapter.ViewHolder();

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ParticipateContent.class);
                    intent.putExtra("postInfo", ParticipateItemList.get(position));
                    activity.startActivity(intent);
                }
            });

            viewHolder.t = (TextView) convertView.findViewById(R.id.participate_title);
            viewHolder.p = (TextView) convertView.findViewById(R.id.participate_limitmember);
            viewHolder.s = (TextView) convertView.findViewById(R.id.participate_school);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ParticipateAdapter.ViewHolder) convertView.getTag();
        }

        ParticipateItem item = ParticipateItemList.get(position);
        viewHolder.t.setText("학교: "+item.getSchool());
        viewHolder.p.setText(item.getTitle());
        viewHolder.s.setText("작성자:"+item.getParticipateName());

        return convertView;
    }

    public void addItem(ParticipateItem item) {
        ParticipateItemList.add(item);
    }


}
