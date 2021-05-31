package com.example.hhhhhh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ParticipateContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate_content);

        ParticipateItem participateItem = (ParticipateItem) getIntent().getSerializableExtra("postInfo");

        TextView title = findViewById(R.id.participate_textview);
        title.setText(participateItem.getTitle());

        TextView writer = findViewById(R.id.participate_content_writer);
        writer.setText("닉네임: "+participateItem.getParticipateName());

        TextView phone = findViewById(R.id.participate_content_phone);
        phone.setText("연락처: "+participateItem.getPhone());

        String category1;
        if(participateItem.getisParticipate()){
            category1 = "스터디";
        }
        else if (participateItem.getisParticipate()){
            category1 = "프로젝트";
        }
        else {
            category1 = "동호회";
        }

        TextView category = findViewById(R.id.participate_content_title);
        category.setText("카테고리: "+category1+", "+participateItem.getCategory());

        TextView content = findViewById(R.id.participate_content__content);
        content.setText(participateItem.getContent());
    }

}