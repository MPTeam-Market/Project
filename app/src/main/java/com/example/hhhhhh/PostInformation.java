package com.example.hhhhhh;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class PostInformation extends AppCompatActivity {
    private EditText titleEditText;
    private FirebaseUser user;
    private ArrayList<String> pathList = new ArrayList<>();
    private LinearLayout parent;
    private EditText contentsEditText;
    private EditText selectedEditText;
    private int pathCount = 0;
    private int successCount = 0;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_in_information);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("게시글 작성");
        }

        titleEditText = findViewById(R.id.post_title);
        parent = findViewById(R.id.contentsLayout);
        contentsEditText = findViewById(R.id.contentsEditText);

        findViewById(R.id.btn_generate_picture).setOnClickListener(onClickListener);
        findViewById(R.id.btnPostInfo).setOnClickListener(onClickListener);


        titleEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    selectedEditText = null;
                }
            }
        });
    }

    View.OnClickListener onClickListener = (v -> {
        switch (v.getId()){
            case R.id.btn_generate_picture:
                Intent intent = new Intent(this, GalleryActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btnPostInfo:
                upLoad();
        }
    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    String path = data.getStringExtra("path");
                    pathList.add(path);

                    ContentsItemView contentsItemView = new ContentsItemView(this);

                    if (selectedEditText == null) {
                        parent.addView(contentsItemView);
                    } else {
                        for (int i = 0; i < parent.getChildCount(); i++) {
                            if (parent.getChildAt(i) == selectedEditText.getParent()) {
                                parent.addView(contentsItemView, i + 1);
                                break;
                            }
                        }
                    }

                    contentsItemView.setImage(path);

                    contentsItemView.setOnFocusChangeListener(onFocusChangeListener);
                }
                break;
        }
    }

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                selectedEditText = (EditText) v;
            }
        }
    };

    private void upLoad(){
        final String Title = titleEditText.getText().toString();

        if(Title.length() > 0){
            ArrayList<String> contentsList = new ArrayList<>();
            user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();

            for(int i = 0; i <parent.getChildCount(); i++){
                LinearLayout linearLayout = (LinearLayout)parent.getChildAt(i);
                for(int j = 0; j < linearLayout.getChildCount(); j++){
                    View view = linearLayout.getChildAt(j);
                    if(view instanceof EditText){
                        String text = ((EditText) view).getText().toString();
                        if(text.length() > 0){
                            contentsList.add(text);
                        }
                    }
                    else{
                        contentsList.add(pathList.get(pathCount));
                        String[] patharray = pathList.get(pathCount).split("\\.");

                        final StorageReference mountainImagesRef = storageRef.child("users/" + user.getUid() + "/"+pathCount+"."+patharray[patharray.length-1]);
                        try {
                            InputStream stream = new FileInputStream(new File(pathList.get(pathCount)));
                            StorageMetadata metadata = new StorageMetadata.Builder()
                                    .setCustomMetadata("index", ""+(contentsList.size()-1)).build();

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
                                            contentsList.set(index, uri.toString());
                                            successCount++;
                                            if(pathList.size() == successCount){
                                                //완료
                                                MyInfo myInfo = new MyInfo(Title, "defaultName", new Date(), contentsList, user.getUid(), "gachon");
                                                storeUpload(myInfo);
                                            }
                                        }
                                    });

                                }
                            });
                        } catch (FileNotFoundException e) {
                            Log.e("로그", "에러: " + e.toString());
                        }

                        pathCount++;
                    }
                }
            }
            if (pathList.size() == 0){
                MyInfo myInfo = new MyInfo(Title, "defaultName", new Date(), contentsList, user.getUid(), "Gachon");
                storeUpload(myInfo);
            }
        }else {
            Toast.makeText(getApplicationContext(), "제목을 입력하여 주세요", Toast.LENGTH_SHORT).show();
        }
    }

    private void storeUpload(MyInfo myInfo) {
        db = FirebaseFirestore.getInstance();
        db.collection("Info").add(myInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Info생성 완료", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Info생성 실패", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
