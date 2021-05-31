package com.example.hhhhhh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class ParticipatePost extends AppCompatActivity {
    private Button posting_btn;
    private Button posting_image;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private EditText titleEditText;
    private Spinner isJoinSpinner;
    private Spinner joinLocationSpinner;
    private EditText priceEditText;
    private EditText phoneEditText;
    private EditText postContentEditText;
    private DatabaseReference mDatabase;
    private String school = "gachon";
    private String writerName = "tester";
    private String imagePath;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate_posting);

        final String[] isjoin = {"스터디", "프로젝트", "동호회"};
        final String[] location = {"경기", "서울"};

        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        titleEditText = findViewById(R.id.participate_posting_title);
        isJoinSpinner = findViewById(R.id.participate_category);
        joinLocationSpinner = findViewById(R.id.participate_location_category);
        priceEditText = findViewById(R.id.participate_posting_salary);
        phoneEditText = findViewById(R.id.participate_posting_phone);
        postContentEditText = findViewById(R.id.participate_posting_content);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, isjoin);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isJoinSpinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, location);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        joinLocationSpinner.setAdapter(adapter2);


        posting_btn = findViewById(R.id.joinposting_btn);
        posting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoad();
            }
        });

    }


    private void upLoad() {
        final String title = titleEditText.getText().toString();
        CollectionReference collection = db.collection("User");
        collection.document(user.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            school = document.getData().get("school").toString();
                            writerName = document.getData().get("name").toString();
                        }
                        if (title.length() > 0) {
                            String price = priceEditText.getText().toString();
                            String writerUid = user.getUid();
                            String isJoin = isJoinSpinner.getSelectedItem().toString();
                            String category = joinLocationSpinner.getSelectedItem().toString();
                            String phone = phoneEditText.getText().toString();
                            String content = postContentEditText.getText().toString();

                            ParticipateItem participateItem = new ParticipateItem(title, school, price, writerUid, writerName, isJoin, category, phone, content, new Date(), "");

                            storeUpload(participateItem);
                        }
                    }
                });
    }

    private void storeUpload(ParticipateItem participateItem) {
        db.collection("Participate")
                .add(participateItem)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "게시글 생성 성공", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "게시글 생성 실패", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }


}

