package com.example.hhhhhh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class JoinContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joincontent);

        JoinItem joinItem = (JoinItem) getIntent().getSerializableExtra("postInfo");

        TextView title = findViewById(R.id.join_textview);
        title.setText(joinItem.getTitle());

        TextView writer = findViewById(R.id.joincontent_writer);
        writer.setText("닉네임: "+joinItem.getWriterName());

        TextView phone = findViewById(R.id.joincontent_phone);
        phone.setText("연락처: "+joinItem.getPhone());

        String category1;
        if(joinItem.getIsJoin()){
            category1 = "배달대행";
        }
        else{
            category1 = "알바대타";
        }
        TextView category = findViewById(R.id.join_content_title);
        category.setText("카테고리: "+category1+", "+joinItem.getCategory());

        TextView content = findViewById(R.id.join_content__content);
        content.setText(joinItem.getContent());
    }

}