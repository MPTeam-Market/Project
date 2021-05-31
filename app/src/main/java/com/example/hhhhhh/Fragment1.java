package com.example.hhhhhh;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class Fragment1 extends Fragment {
    JoinAdapter adatper;
    ListView joinview;
    ArrayList<JoinItem> RoomList;
    Button addbtn;
    Spinner category_spinner;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        firebaseFirestore = FirebaseFirestore.getInstance();

        View v = inflater.inflate(R.layout.fragment_1, container, false);
        RoomList = new ArrayList<JoinItem>();
        addbtn = (Button) v.findViewById(R.id.plusbtn);
        joinview = (ListView) v.findViewById(R.id.lv1);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JoinPostingActivity.class);
                startActivity(intent);
            }
        });
        category_spinner = v.findViewById(R.id.fragment1_spinner);
        //새로고침 위해 select 리스너를 넣어줌
        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), dummyActivity.class);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        String text = category_spinner.getSelectedItem().toString();

        if(text.equals("전체보기")){
            update();
        }else if(text.equals("배달대행")){
            updatesell();
        }else{
            updatetrade();
        }
    }

    public void update(){
        CollectionReference collectionReference = firebaseFirestore.collection("join");
        collectionReference
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<JoinItem> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("school").toString().toLowerCase().equals("gachon")) {
                                    if (document.getData().get("img") != null) {
                                        postList.add(new JoinItem(
                                                document.getData().get("title").toString(),
                                                document.getData().get("school").toString(),
                                                document.getData().get("salary").toString(),
                                                document.getData().get("writerUid").toString(),
                                                document.getData().get("writerName").toString(),
                                                (Boolean) document.getData().get("isJoin"),
                                                document.getData().get("category").toString(),
                                                document.getData().get("phone").toString(),
                                                document.getData().get("content").toString(),
                                                new Date(document.getDate("date").getTime()),
                                                document.getData().get("img").toString(),
                                                document.getId()
                                        ));
                                    } else {
                                        postList.add(new JoinItem(
                                                document.getData().get("title").toString(),
                                                document.getData().get("school").toString(),
                                                document.getData().get("salary").toString(),
                                                document.getData().get("sellerUid").toString(),
                                                document.getData().get("sellerName").toString(),
                                                (Boolean) document.getData().get("isSelling"),
                                                document.getData().get("category").toString(),
                                                document.getData().get("phone").toString(),
                                                document.getData().get("content").toString(),
                                                new Date(document.getDate("date").getTime()),
                                                "", document.getId()
                                        ));
                                    }
                                }
                            }
                            adatper = new JoinAdapter(getActivity(), postList);
                            joinview.setAdapter(adatper);
                        }
                    }
                });
    }

    public void updatesell() {
        CollectionReference collectionReference = firebaseFirestore.collection("join");
        collectionReference
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<JoinItem> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("school").toString().toLowerCase().equals("gachon")) {
                                    if(((Boolean) document.getData().get("isJoin"))) {
                                        if (document.getData().get("img") != null) {
                                            postList.add(new JoinItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("salary").toString(),
                                                    document.getData().get("writerUid").toString(),
                                                    document.getData().get("writerName").toString(),
                                                    (Boolean) document.getData().get("isJoin"),
                                                    document.getData().get("category").toString(),
                                                    document.getData().get("phone").toString(),
                                                    document.getData().get("content").toString(),
                                                    new Date(document.getDate("date").getTime()),
                                                    document.getData().get("img").toString(),
                                                    document.getId()
                                            ));
                                        } else {
                                            postList.add(new JoinItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("salary").toString(),
                                                    document.getData().get("sellerUid").toString(),
                                                    document.getData().get("sellerName").toString(),
                                                    (Boolean) document.getData().get("isSelling"),
                                                    document.getData().get("category").toString(),
                                                    document.getData().get("phone").toString(),
                                                    document.getData().get("content").toString(),
                                                    new Date(document.getDate("date").getTime()),
                                                    "", document.getId()
                                            ));
                                        }
                                    }
                                }
                            }
                            adatper = new JoinAdapter(getActivity(), postList);
                            joinview.setAdapter(adatper);
                        }
                    }
                });
    }

    public void updatetrade(){
        CollectionReference collectionReference = firebaseFirestore.collection("join");
        collectionReference
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<JoinItem> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("school").toString().toLowerCase().equals("gachon")) {
                                    if(!((Boolean) document.getData().get("isJoin"))) {
                                        if (document.getData().get("img") != null) {
                                            postList.add(new JoinItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("salary").toString(),
                                                    document.getData().get("writerUid").toString(),
                                                    document.getData().get("writerName").toString(),
                                                    (Boolean) document.getData().get("isJoin"),
                                                    document.getData().get("category").toString(),
                                                    document.getData().get("phone").toString(),
                                                    document.getData().get("content").toString(),
                                                    new Date(document.getDate("date").getTime()),
                                                    document.getData().get("img").toString(),
                                                    document.getId()
                                            ));
                                        } else {
                                            postList.add(new JoinItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("salary").toString(),
                                                    document.getData().get("sellerUid").toString(),
                                                    document.getData().get("sellerName").toString(),
                                                    (Boolean) document.getData().get("isSelling"),
                                                    document.getData().get("category").toString(),
                                                    document.getData().get("phone").toString(),
                                                    document.getData().get("content").toString(),
                                                    new Date(document.getDate("date").getTime()),
                                                    "", document.getId()
                                            ));
                                        }
                                    }
                                }
                            }
                            adatper = new JoinAdapter(getActivity(), postList);
                            joinview.setAdapter(adatper);
                        }
                    }
                });
    }
}