package com.example.hhhhhh;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

//소윤님
import android.content.SharedPreferences;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Fragment5 extends Fragment {



    private String uid = null;


    DatabaseReference mDatabase;

    private final FirebaseAuth Auth = FirebaseAuth.getInstance();

    //소윤님 깃허브에서 가져온 내용
    FirebaseFirestore firebaseFirestore;
    private String _userID;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth firebaseAuth;

    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences sh_Pref;

    /*
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _userID = Objects.requireNonNull(Auth.getCurrentUser()).getUid();
        sh_Pref = this.requireActivity().getSharedPreferences("Login Credentials ", MODE_PRIVATE);
        _userID = sh_Pref.getString("Nickname", "");
    }

    /*

*/

    /*
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        user.getEmail(); // 사용자 이메일
        user.getUid();    // 사용자 UID
    }
*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //realtime base
        TextView textView, textView2, textView3, textView4;
        View v = inflater.inflate(R.layout.fragment_5, container, false);
        textView = v.findViewById(R.id.email);   //이메일 읽기
        textView2 = v.findViewById(R.id.nickname);
        textView3 = v.findViewById(R.id.collage);

        FirebaseUser user = Auth.getCurrentUser();
        textView.setText(user.getEmail());






        String uid = user.getUid();
        myRef = database.getInstance().getReference("users");

        DatabaseReference name = myRef.child(uid).child("Nickname");
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

        DatabaseReference School = myRef.child(uid).child("School");
        School.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                textView3.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //데베 읽기


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
    private void showInfo(TextView textView2, TextView textView3, TextView textView4) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Info")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        // 파이어스토어에서 데이터를 가져오는 것을 성공했을 때
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (!document.getId().equals(_userID)) continue;
                                textView2.setText(document.getData().get("title").toString());

                                break;
                            }
                        }
                    }
                });
    }
 */

}