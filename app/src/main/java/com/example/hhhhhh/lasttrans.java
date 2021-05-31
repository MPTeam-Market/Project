package com.example.hhhhhh;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/*
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
*/


public class lasttrans extends AppCompatActivity {
    private FirebaseAuth Auth = FirebaseAuth.getInstance();
    private static final String TAG = "lasttrans";
    private EditText password;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last);


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

//비밀번호 변경하기
        Button btn_password = (Button) findViewById(R.id.btn_password);
        password = findViewById(R.id.editTextTextPassword);
        btn_password.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(lasttrans.this);
            builder.setTitle("비밀번호를 바꾸기");
            builder.setMessage("비밀번호를 바꾸시겠습니까?");
            builder.setPositiveButton("바꾸기", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String StrPW = password.getText().toString().trim();
                    user.updatePassword(StrPW)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User password updated.");
                                    }
                                }
                            });


                }
            })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .show();

        }
    });

}
}


