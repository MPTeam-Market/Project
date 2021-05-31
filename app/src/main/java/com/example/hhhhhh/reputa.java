package com.example.hhhhhh;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class reputa extends AppCompatActivity {
    private static final String TAG = "HomeFragment";
    private FirebaseFirestore firebaseFirestore;
    private InfoAdapter InfoAdapter;
    private RecyclerView recyclerView;
    private final FirebaseAuth Auth = FirebaseAuth.getInstance();
    FirebaseUser user = Auth.getCurrentUser();
    String uid = user.getUid();

    public reputa() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reputation);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.rv_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }




    @Override
    public void onResume() {
        super.onResume();
        CollectionReference collectionReference = firebaseFirestore.collection("Info");
        collectionReference
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<MyInfo> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getData().get("uid").toString().equals(uid)) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    postList.add(new MyInfo(
                                            document.getData().get("title").toString(),
                                            document.getData().get("name").toString(),
                                            new Date(document.getDate("date").getTime()),
                                            (ArrayList<String>) document.getData().get("contents"),
                                            document.getData().get("uid").toString(),
                                            document.getData().get("university").toString(),
                                            document.getId()
                                    ));
                                }
                                else{ }
                            }
                            InfoAdapter = new InfoAdapter(reputa.this, postList);
                            recyclerView.setAdapter(InfoAdapter);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}