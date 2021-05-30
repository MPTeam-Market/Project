package com.example.hhhhhh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity  {

    private EditText Email, Password;
    private TextView Find;
    private FirebaseAuth Auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.login_email);
        Password = findViewById(R.id.login_password);
        Find = findViewById(R.id.FindPassword);
        TextView loginsuccess = findViewById(R.id.login_success);
        TextView loginsignup = findViewById(R.id.login_signup);
        loginsuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String StrEmail = Email.getText().toString().trim();
                String StrPW = Password.getText().toString().trim();
                Auth.signInWithEmailAndPassword(StrEmail, StrPW)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//성공했을때

                                    FirebaseUser user = Auth.getCurrentUser();

                                    if (user.isEmailVerified()) {
                                        Toast.makeText(LoginActivity.this, "Login complete!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }

                                    else {
                                        Toast.makeText(LoginActivity.this, "Please check email verification.", Toast.LENGTH_SHORT).show();
                                    }

                                } else {//실패했을때
                                    Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }
        });
        loginsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
        Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(intent);
            }
        });

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = Auth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_signup:
                startActivity(new Intent(this, SignupActivity.class));
                break;

            case R.id.login_success:
                Auth.signInWithEmailAndPassword(Email.getText().toString(), Password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = Auth.getCurrentUser();
                                    if (user != null) {
                                        Toast.makeText(LoginActivity.this, "Login success." + user.getUid(),
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    }

                                } else {
                                    Toast.makeText(LoginActivity.this, "Login error.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                break;

        }
    }*/
}
