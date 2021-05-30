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


public class Fragment3 extends Fragment {
    SellAdapter adatper;
    ListView sellview;
    ArrayList<SellItem> chatRoomList;
    Button addbtn;
    Spinner category_spinner;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        firebaseFirestore = FirebaseFirestore.getInstance();

        View v = inflater.inflate(R.layout.fragment_3, container, false);
        chatRoomList = new ArrayList<SellItem>();
        addbtn = (Button) v.findViewById(R.id.plusbtn);
        sellview = (ListView) v.findViewById(R.id.lv4);

        sellview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Sellcontent.class);
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostingActivity.class);
                startActivity(intent);
            }
        });
        category_spinner = v.findViewById(R.id.fragment4_spinner);
        return v;


    }

    @Override
    public void onResume() {
        super.onResume();
        CollectionReference collectionReference = firebaseFirestore.collection("Trade");
        collectionReference
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<SellItem> postList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("school").toString().toLowerCase().equals("gachon")) {
                                    if (document.getData().get("img") != null) {
                                        postList.add(new SellItem(
                                                document.getData().get("title").toString(),
                                                document.getData().get("school").toString(),
                                                document.getData().get("price").toString(),
                                                document.getData().get("sellerUid").toString(),
                                                document.getData().get("sellerName").toString(),
                                                (Boolean) document.getData().get("isSelling"),
                                                document.getData().get("category").toString(),
                                                document.getData().get("phone").toString(),
                                                document.getData().get("content").toString(),
                                                new Date(document.getDate("date").getTime()),
                                                document.getData().get("img").toString(),
                                                document.getId()
                                        ));
                                    } else {
                                        postList.add(new SellItem(
                                                document.getData().get("title").toString(),
                                                document.getData().get("school").toString(),
                                                document.getData().get("price").toString(),
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
                            adatper = new SellAdapter(getActivity(), postList);
                            sellview.setAdapter(adatper);
                        }
                    }
                });
    }
}

