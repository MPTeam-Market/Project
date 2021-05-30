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

public class PostingActivity extends AppCompatActivity {
    private Button posting_btn;
    private Button posting_image;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private EditText titleEditText;
    private Spinner isSellingSpinner;
    private Spinner sellCategorySpinner;
    private EditText priceEditText;
    private EditText phoneEditText;
    private EditText postContentEditText;
    private DatabaseReference mDatabase;
    private String school = "gachon";
    private String sellerName = "tester";
    private String imagePath;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellposting);

        final String[] isSell = {"판매", "교환"};
        final String[] categ = {"식품", "잡동사니"};

        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        titleEditText = findViewById(R.id.posting_title);
        isSellingSpinner = findViewById(R.id.category);
        sellCategorySpinner = findViewById(R.id.sell_category);
        priceEditText = findViewById(R.id.posting_price);
        phoneEditText = findViewById(R.id.posting_phone);
        postContentEditText = findViewById(R.id.posting_content);
        imageView = findViewById(R.id.posting_img);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, isSell);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isSellingSpinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categ);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sellCategorySpinner.setAdapter(adapter2);


        posting_btn = findViewById(R.id.posting_btn);
        posting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoad();
            }
        });

        posting_image = findViewById(R.id.posting_image_btn);
        posting_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    imagePath = data.getStringExtra("path");
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setAdjustViewBounds(true);

                    Glide.with(this).load(imagePath).override(1000).into(imageView);
                }
                break;
        }
    }

    private void upLoad() {
        if(imagePath != null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            String[] patharray = imagePath.split("\\.");
            final StorageReference mountainImagesRef = storageRef.child("users/" + user.getUid() + "/" + 0 + "." + patharray[patharray.length - 1]);
            try {
                InputStream stream = new FileInputStream(new File(imagePath));
                StorageMetadata metadata = new StorageMetadata.Builder()
                        .setCustomMetadata("index", "" + (0)).build();

                UploadTask uploadTask = mountainImagesRef.putStream(stream, metadata);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final int index = Integer.parseInt(taskSnapshot.getMetadata().getCustomMetadata("index"));

                        mountainImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final String title = titleEditText.getText().toString();
                                CollectionReference collection = db.collection("User");
                                collection.document(user.getUid()).get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    DocumentSnapshot document = task.getResult();
                                                    school = document.getData().get("school").toString();
                                                    sellerName = document.getData().get("name").toString();
                                                }
                                                if (title.length() > 0) {
                                                    String price = priceEditText.getText().toString();
                                                    String sellerUid = user.getUid();
                                                    boolean isSelling = (isSellingSpinner.getSelectedItem().toString().equals("판매"));
                                                    String category = sellCategorySpinner.getSelectedItem().toString();
                                                    String phone = phoneEditText.getText().toString();
                                                    String content = postContentEditText.getText().toString();

                                                    SellItem trade = new SellItem(title, school, price, sellerUid, sellerName, isSelling, category, phone, content, new Date(), uri.toString());

                                                    storeUpload(trade);
                                                }
                                            }
                                        });
                            }
                        });
                    }
                });
            } catch (FileNotFoundException e) {
                Log.e("로그", "에러: " + e.toString());
            }
        }else{
            final String title = titleEditText.getText().toString();
            CollectionReference collection = db.collection("User");
            collection.document(user.getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                DocumentSnapshot document = task.getResult();
                                school = document.getData().get("school").toString();
                                sellerName = document.getData().get("name").toString();
                            }
                            if (title.length() > 0) {
                                String price = priceEditText.getText().toString();
                                String sellerUid = user.getUid();
                                boolean isSelling = (isSellingSpinner.getSelectedItem().toString().equals("판매"));
                                String category = sellCategorySpinner.getSelectedItem().toString();
                                String phone = phoneEditText.getText().toString();
                                String content = postContentEditText.getText().toString();

                                SellItem trade = new SellItem(title, school, price, sellerUid, sellerName, isSelling, category, phone, content, new Date(), "");

                                storeUpload(trade);
                            }
                        }
                    });
        }
    }

    private void storeUpload (SellItem trade){
        db.collection("Trade")
                .add(trade)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "거래 생성 성공", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "거래 생성 실패", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }


}

