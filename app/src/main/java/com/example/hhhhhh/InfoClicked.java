package com.example.hhhhhh;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class InfoClicked extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_item_clicked);

        MyInfo myInfo = (MyInfo) getIntent().getSerializableExtra("postInfo");

        TextView titleTextView = findViewById(R.id.ClickedInfo);
        titleTextView.setText(myInfo.getTitle());

        TextView userTextView = findViewById(R.id.ClickedUser);
        userTextView.setText(myInfo.getName());

        TextView createdAt = findViewById(R.id.Clickeddate);
        createdAt.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(myInfo.getDate()));

        LinearLayout contentsLayout = findViewById(R.id.clickedContentsLayout);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ArrayList<String> contentsList = myInfo.getContents();

        if(contentsLayout.getChildCount() == 0) {
            for (int i = 0; i < contentsList.size(); i++) {
                String contents = contentsList.get(i);
                if (Patterns.WEB_URL.matcher(contents).matches()) {
                    ImageView imageView = new ImageView(this);
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                    imageView.setLayoutParams(layoutParams);
                    contentsLayout.addView(imageView);
                    Glide.with(this).load(contents).override(1000).into(imageView);
                }else{
                    TextView textView = new TextView(this);
                    textView.setLayoutParams(layoutParams);
                    textView.setText(contents);
                    textView.setPadding(10, 10, 10, 10);
                    textView.setTextColor(Color.rgb(0, 0,0));
                    contentsLayout.addView(textView);
                }
            }
        }
    }

}
