package com.example.hhhhhh;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

public class Fragment2 extends Fragment {
    ParticipateAdapter adatper;
    ListView participateview;
    ArrayList<ParticipateItem> RoomList;
    Button addbtn;
    Spinner category_spinner;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        firebaseFirestore = FirebaseFirestore.getInstance();

        View v = inflater.inflate(R.layout.fragment_2, container, false);
        RoomList = new ArrayList<ParticipateItem>();
        addbtn = (Button) v.findViewById(R.id.plusbtn);
        participateview = (ListView) v.findViewById(R.id.lv1);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ParticipatePost.class);
                startActivity(intent);
            }
        });
        category_spinner = v.findViewById(R.id.fragment2_spinner);
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
            update_participate();
        }
        else if(text.equals("스터디")){
            update_participate_study();
        }
        else if(text.equals("프로젝트")){
            update_participate_club();
        }
        else{
            update_participate_project();
        }
    }

    public void update_participate(){
        CollectionReference collectionReference = firebaseFirestore.collection("Participate");
        collectionReference
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<ParticipateItem> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("school").toString().toLowerCase().equals("gachon")) {
                                    if (document.getData().get("img") != null) {
                                        postList.add(new ParticipateItem(
                                                document.getData().get("title").toString(),
                                                document.getData().get("school").toString(),
                                                document.getData().get("limitmember").toString(),
                                                document.getData().get("participateUid").toString(),
                                                document.getData().get("participateName").toString(),
                                                document.getData().get("isParticipate").toString(),
                                                document.getData().get("category").toString(),
                                                document.getData().get("phone").toString(),
                                                document.getData().get("content").toString(),
                                                new Date(document.getDate("date").getTime()),
                                                document.getData().get("img").toString(),
                                                document.getId()
                                        ));
                                    } else {
                                        postList.add(new ParticipateItem(
                                                document.getData().get("title").toString(),
                                                document.getData().get("school").toString(),
                                                document.getData().get("limitmember").toString(),
                                                document.getData().get("participateUid").toString(),
                                                document.getData().get("participateName").toString(),
                                                document.getData().get("isParticipate").toString(),
                                                document.getData().get("category").toString(),
                                                document.getData().get("phone").toString(),
                                                document.getData().get("content").toString(),
                                                new Date(document.getDate("date").getTime()),
                                                "", document.getId()
                                        ));
                                    }
                                }
                            }
                            adatper = new ParticipateAdapter(getActivity(), postList);
                            participateview.setAdapter(adatper);
                        }
                    }
                });
    }

    public void update_participate_study() {
        CollectionReference collectionReference = firebaseFirestore.collection("Participate");
        collectionReference
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<ParticipateItem> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("school").toString().toLowerCase().equals("gachon")) {
                                    if(document.getData().get("isParticipate").toString().equals("스터디")) {
                                        if (document.getData().get("img") != null) {
                                            postList.add(new ParticipateItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("limitmember").toString(),
                                                    document.getData().get("participateUid").toString(),
                                                    document.getData().get("participateName").toString(),
                                                    document.getData().get("isParticipate").toString(),
                                                    document.getData().get("category").toString(),
                                                    document.getData().get("phone").toString(),
                                                    document.getData().get("content").toString(),
                                                    new Date(document.getDate("date").getTime()),
                                                    document.getData().get("img").toString(),
                                                    document.getId()
                                            ));
                                        } else {
                                            postList.add(new ParticipateItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("limitmember").toString(),
                                                    document.getData().get("participateUid").toString(),
                                                    document.getData().get("participateName").toString(),
                                                    document.getData().get("isParticipate").toString(),
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
                            adatper = new ParticipateAdapter(getActivity(), postList);
                            participateview.setAdapter(adatper);
                        }
                    }
                });
    }

    public void update_participate_club(){
        CollectionReference collectionReference = firebaseFirestore.collection("Participate");
        collectionReference
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<ParticipateItem> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("school").toString().toLowerCase().equals("gachon")) {
                                    if(document.getData().get("isParticipate").toString().equals("프로젝트")) {
                                        if (document.getData().get("img") != null) {
                                            postList.add(new ParticipateItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("limitmember").toString(),
                                                    document.getData().get("participateUid").toString(),
                                                    document.getData().get("participateName").toString(),
                                                    document.getData().get("isParticipate").toString(),
                                                    document.getData().get("category").toString(),
                                                    document.getData().get("phone").toString(),
                                                    document.getData().get("content").toString(),
                                                    new Date(document.getDate("date").getTime()),
                                                    document.getData().get("img").toString(),
                                                    document.getId()
                                            ));
                                        } else {
                                            postList.add(new ParticipateItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("limitmember").toString(),
                                                    document.getData().get("participateUid").toString(),
                                                    document.getData().get("participateName").toString(),
                                                    document.getData().get("isParticipate").toString(),
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
                            adatper = new ParticipateAdapter(getActivity(), postList);
                            participateview.setAdapter(adatper);
                        }
                    }
                });
    }

    public void update_participate_project(){
        CollectionReference collectionReference = firebaseFirestore.collection("Participate");
        collectionReference
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<ParticipateItem> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("school").toString().toLowerCase().equals("gachon")) {
                                    if(document.getData().get("isParticipate").toString().equals("동호회")) {
                                        if (document.getData().get("img") != null) {
                                            postList.add(new ParticipateItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("limitmember").toString(),
                                                    document.getData().get("participateUid").toString(),
                                                    document.getData().get("participateName").toString(),
                                                    document.getData().get("isParticipate").toString(),
                                                    document.getData().get("category").toString(),
                                                    document.getData().get("phone").toString(),
                                                    document.getData().get("content").toString(),
                                                    new Date(document.getDate("date").getTime()),
                                                    document.getData().get("img").toString(),
                                                    document.getId()
                                            ));
                                        } else {
                                            postList.add(new ParticipateItem(
                                                    document.getData().get("title").toString(),
                                                    document.getData().get("school").toString(),
                                                    document.getData().get("limitmember").toString(),
                                                    document.getData().get("participateUid").toString(),
                                                    document.getData().get("participateName").toString(),
                                                    document.getData().get("isParticipate").toString(),
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
                            adatper = new ParticipateAdapter(getActivity(), postList);
                            participateview.setAdapter(adatper);
                        }
                    }
                });
    }
}