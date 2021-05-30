package com.example.hhhhhh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Sellcontent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellcontent);

        SellItem sellItem = (SellItem) getIntent().getSerializableExtra("postInfo");

        ImageView imageView = findViewById(R.id.image);
        if(sellItem.getImg() != "" && sellItem.getImg() != null){
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);

            Glide.with(this).load(sellItem.getImg()).override(1000).into(imageView);
        }

        TextView writer = findViewById(R.id.sellcontent_writer);
        writer.setText("닉네임: "+sellItem.getSellerName());

        TextView phone = findViewById(R.id.sellcontent_phone);
        phone.setText("연락처: "+sellItem.getPhone());

        String category1;
        if(sellItem.getIsSelling()){
            category1 = "판매";
        }
        else{
            category1 = "교환";
        }
        TextView category = findViewById(R.id.sell_content_title);
        category.setText("카테고리: "+category1+", "+sellItem.getCategory());

        TextView content = findViewById(R.id.sell_content__content);
        content.setText(sellItem.getContent());
    }

}