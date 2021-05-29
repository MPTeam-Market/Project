package com.example.hhhhhh;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Fragment4 extends Fragment {
    SellAdapter adatper;
    ListView sellview;
    ArrayList<SellItem> chatRoomList;
    Button addbtn;
    Spinner category_spinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v = inflater.inflate(R.layout.fragment_4, container, false);
        chatRoomList = new ArrayList<SellItem>();
        addbtn = (Button)v.findViewById(R.id.plusbtn);
        sellview = (ListView) v.findViewById(R.id.lv4);
        adatper = new SellAdapter();
        sellview.setAdapter(adatper);
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
        category_spinner = (Spinner)v.findViewById(R.id.fragment4_spinner);



        return v;

    }



}