package com.example.hhhhhh;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.rpc.context.AttributeContext;
*/


public class Fragment5 extends Fragment {

    static final int REQUEST_CODE = 1;
    private ImageView imageView;
    private String uid = null;
    private TextView textView, textView2;
    private EditText Email, Password;
    public String mail;
    private DatabaseReference mDatabase;

    private FirebaseAuth Auth = FirebaseAuth.getInstance();


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    FirebaseDatabase database;
    DatabaseReference myRef;

    public Fragment5(ImageView imageView, EditText email, EditText password, DatabaseReference mDatabase) {
        this.imageView = imageView;
        Email = email;
        Password = password;
        this.mDatabase = mDatabase;
    }

    /*
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        user.getEmail(); // 사용자 이메일
        user.getUid();    // 사용자 UID
    }
*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //realtime base


        View v = inflater.inflate(R.layout.fragment_5, container, false);
        textView = v.findViewById(R.id.name);   //이메일 읽기
        textView2 = v.findViewById(R.id.collage);
        FirebaseUser user = Auth.getCurrentUser();
        textView.setText(user.getEmail());


        /*
        myRef = database.getReference("users");
      String Email = user.getEmail();
      DatabaseReference name = myRef.child(Email).child("Name");
        name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                textView2.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/


        //별명 가져오기가 안됨.....
        textView2 = v.findViewById(R.id.collage);

        String Email = user.getEmail(); // 로그인한 유저의 고유 uid 가져오기
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance(); // 파이어베이스 realtime database 에서 정보 가져오기
        DatabaseReference Nickname = mDatabase.getReference("Nickame");    // 닉네임


        Nickname.setValue(textView2.getText().toString());





/*
        myRef = FirebaseDatabase.getInstance().getReference("UserInfo");
        DatabaseReference name = myRef.child("Name").child(School);
        name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                textView2.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //데베 읽기
*/

        Button edit = v.findViewById(R.id.edit);
        edit.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), myedit.class);
            startActivity(intent);

        });
        Button btn_reputa = v.findViewById(R.id.btn_reputa);
        btn_reputa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), reputa.class);
                startActivity(intent1);
            }


        });


        Button btn_last = v.findViewById(R.id.btn_last);
        btn_last.setOnClickListener(v12 -> {
            Intent intent2 = new Intent(getActivity(), lasttrans.class);
            startActivity(intent2);
        });

        Button btn_meet = v.findViewById(R.id.btn_meet);
        btn_meet.setOnClickListener(v13 -> {
            Intent intent3 = new Intent(getActivity(), meeting.class);
            startActivity(intent3);
        });


        Button btn_setting = v.findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(v14 -> {
            Intent intent4 = new Intent(getActivity(), setting.class);
            startActivity(intent4);
        });


        return v;
    }


/*
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_5);
        Button edit = getView().findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), myedit.class);
                startActivity(intent);

            }


        });
        Button btn_reputa = getView().findViewById(R.id.btn_reputa);
        btn_reputa.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity().getApplicationContext(), reputa.class);
                startActivity(intent1);
            }


        });


        Button btn_last = getView().findViewById(R.id.btn_last);
        btn_last.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity().getApplicationContext(), lasttrans.class);
                startActivity(intent2);
            }


        });

        Button btn_meet = getView().findViewById(R.id.btn_meet);
        btn_meet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent3 = new Intent(getActivity().getApplicationContext(), meeting.class);
                startActivity(intent3);
            }


        });


        Button btn_setting = getView().findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent4 = new Intent(getActivity().getApplicationContext(), setting.class);
                startActivity(intent4);
            }


        });

    }
*/


}