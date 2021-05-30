package com.example.hhhhhh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;


import static android.content.ContentValues.TAG;

public class SignupActivity extends AppCompatActivity  {

    private FirebaseAuth Auth = FirebaseAuth.getInstance();
    private FirebaseFirestore store;
    private DatabaseReference Database;

    private EditText sign_Email, sign_Nickname, sign_Password, sign_Name, Join, sign_School;
    private Button Joinbtn;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        back = findViewById(R.id.buttonBack);
        sign_Email = findViewById(R.id.login_email);
        sign_Nickname = findViewById(R.id.login_nickname);
        sign_Password = findViewById(R.id.signup_password);
        sign_Name = findViewById(R.id.login_name);
        sign_School = findViewById(R.id.login_school);
        Joinbtn = findViewById(R.id.login_join);

        back.setOnClickListener(new MyListener());




        Joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = sign_Email.getText().toString().trim();
                String getNickname = sign_Nickname.getText().toString().trim();
                String getPassword = sign_Password.getText().toString().trim();
                String getName = sign_Name.getText().toString().trim();
                String getSchool = sign_School.getText().toString().trim();

                Log.d(TAG, "Sign up " + getEmail + " , " + getPassword);
                final ProgressDialog mDialog = new ProgressDialog(SignupActivity.this);
                mDialog.setMessage("Checking...");
                mDialog.show();

                //파이어베이스에 신규계정 등록하기
                Auth.createUserWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //가입 성공시
                        if (task.isSuccessful()) {
                            mDialog.dismiss();

                            FirebaseUser user = Auth.getCurrentUser();
                            if (user != null) {
                                String uid = user.getUid();
                                //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                                HashMap<Object,String> hashMap = new HashMap<>();

                                hashMap.put("uid",uid);
                                hashMap.put("Email", getEmail);
                                hashMap.put("Name", getName);
                                hashMap.put("Password", getPassword);
                                hashMap.put("Nickname", getNickname);
                                hashMap.put("School", getSchool);
                                UserInfo userInfo = new UserInfo(getName, getEmail, getPassword, uid, getNickname, getSchool);
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("User").document(uid).set(userInfo);
                                DatabaseReference reference = database.getReference("users");
                                reference.child(uid).setValue(hashMap);
                                user.sendEmailVerification();
                                Toast.makeText(SignupActivity.this, "Send verification Email to " + getEmail + ".", Toast.LENGTH_SHORT).show();
                                finish();
                                Toast.makeText(SignupActivity.this, "Sign up complete", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            mDialog.dismiss();
                            Toast.makeText(SignupActivity.this, "Sign up error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }


}

