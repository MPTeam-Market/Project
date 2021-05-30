package com.example.hhhhhh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;
import static com.example.hhhhhh.FirebaseID.user;

public class FindActivity extends AppCompatActivity {

    private FirebaseAuth Auth = FirebaseAuth.getInstance();
    private EditText Email;
    private Button find_Email;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        Email = findViewById(R.id.editTextUserEmail);
        find_Email = findViewById(R.id.buttonFind);
        back = findViewById(R.id.findBackArrow);



        back.setOnClickListener(new FindActivity.MyListener());

        find_Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(FindActivity.this);
                mDialog.setMessage("Sending Email...");
                mDialog.show();
                String StrEmail = Email.getText().toString().trim();
                Auth.sendPasswordResetEmail(StrEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    mDialog.dismiss();
                                    Log.d(TAG, "Email sent.");
                                    Toast.makeText(FindActivity.this,
                                            "Verification email sent to " + StrEmail,
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                else {
                                    mDialog.dismiss();
                                    Toast.makeText(FindActivity.this,
                                            "Verification email sending error.",
                                            Toast.LENGTH_SHORT).show();
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