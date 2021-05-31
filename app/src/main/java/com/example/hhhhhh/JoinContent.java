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

        ImageView imageView = findViewById(R.id.image);
        if(joinItem.getImg() != "" && joinItem.getImg() != null){
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);

            Glide.with(this).load(joinItem.getImg()).override(1000).into(imageView);
        }

        TextView writer = findViewById(R.id.joincontent_writer);
        writer.setText("닉네임: "+joinItem.getWriterName());

        TextView phone = findViewById(R.id.joincontent_phone);
        phone.setText("연락처: "+joinItem.getPhone());

        String category1;
        if(joinItem.getIsJoin()){
            category1 = "판매";
        }
        else{
            category1 = "교환";
        }
        TextView category = findViewById(R.id.join_content_title);
        category.setText("카테고리: "+category1+", "+joinItem.getCategory());

        TextView content = findViewById(R.id.join_content__content);
        content.setText(joinItem.getContent());
    }

}