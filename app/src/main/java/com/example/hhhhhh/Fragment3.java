package com.example.hhhhhh;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class Fragment3 extends Fragment {
    SellAdapter adatper;
    ListView sellview;
    ArrayList<SellItem> chatRoomList;
    Button addbtn;
    Spinner category_spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v = inflater.inflate(R.layout.fragment_3, container, false);
        chatRoomList = new ArrayList<SellItem>();
        addbtn = (Button)v.findViewById(R.id.plusbtn);
        sellview = (ListView) v.findViewById(R.id.lv4);
        adatper = new SellAdapter();
        sellview.setAdapter(adatper);
        //스피너 선택하면 해당 카테고리 목록 나와야함(
//        category_spinner =  (Spinner)v.findViewById(R.id.category);
//        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        sellview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Sellcontent.class);

                startActivity(intent);
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostingActivity.class);
                startActivity(intent);
            }
        });
        //item 선택하면 상세 페이지 나오는
        sellview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Sellcontent.class);
                startActivity(intent);
            }
        });


        return v;
    }
}