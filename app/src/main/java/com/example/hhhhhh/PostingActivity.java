package com.example.hhhhhh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostingActivity extends AppCompatActivity {
    Button posting_btn;
    Fragment4 fragment4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellposting);

        posting_btn = findViewById(R.id.posting_btn);
        posting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment4 = new Fragment4();

                getSupportFragmentManager().beginTransaction().add(R.id.lv4, fragment4).commit();
            }
        });

    }
}