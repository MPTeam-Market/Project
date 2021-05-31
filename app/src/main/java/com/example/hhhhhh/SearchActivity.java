package com.example.hhhhhh;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity {
    public static final String INTENT_NAME_RESULT = "selected";

    private ArrayList<String> list = null;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist = null;
    private String requestUrl, str, str_sch, str_cam;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_univ_main);

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);
        back = findViewById(R.id.buttonBack);
        back.setOnClickListener(new SearchActivity.MyListener());

        // 리스트를 생성한다.
        list = new ArrayList<String>();
        arraylist = new ArrayList<String>();

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
        System.out.println("중간 리스트 점검" + list);
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String result = (String) adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra(INTENT_NAME_RESULT, result);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else {
            // 리스트의 모든 데이터를 검색한다.
            for (int i = 0; i < arraylist.size(); i++) {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText)) {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }


    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.d("파싱이 시작됩니다", "파싱 시작");
            requestUrl = "https://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=c2798400738f904c2d5c286ff1e8b8cf&svcType=api&svcCode=SCHOOL&contentType=xml&gubun=univ_list&thisPage=1&perPage=500";
            try {
                boolean b_campus = false;
                boolean b_info = false;
                boolean b_type = false;
                boolean b_link = false;
                boolean b_gubun = false;
                boolean b_adres = false;
                boolean b_school = false;
                boolean cam = false;


                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("content") && str != null) {
                                arraylist.add(str);
                                list.add(str);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("content")) {
                                str = new String();
                                str_cam = new String();
                                str_sch = new String();
                            }
                            //각각의 api속 변수명이다.
                            if (parser.getName().equals("campusName")) b_campus = true;
                            if (parser.getName().equals("collegeinfourl")) b_info = true;
                            if (parser.getName().equals("schoolType")) b_type = true;
                            if (parser.getName().equals("link")) b_link = true;
                            if (parser.getName().equals("schoolGubun")) b_gubun = true;
                            if (parser.getName().equals("adres")) b_adres = true;
                            if (parser.getName().equals("schoolName")) b_school = true;
                            break;
                        case XmlPullParser.TEXT:
                            if (b_campus) {
                                str_cam = parser.getText();
                                if (str_cam.equals("본교") || str_cam.equals("")) {
                                    cam = true;
                                }
                                b_campus = false;
                            } else if (b_info) {
                               String temp = parser.getText();
                                b_info = false;
                            } else if (b_type) {
                                String temp = parser.getText();
                                b_type = false;
                            } else if (b_link) {
                                String temp = parser.getText();
                                b_link = false;
                            } else if (b_gubun) {
                                String temp = parser.getText();
                                b_gubun = false;
                            } else if (b_adres) {
                                String temp = parser.getText();
                                b_adres = false;
                            } else if (b_school) {
                                str_sch = parser.getText();
                                if (cam) {
                                    str = str_sch;
                                }
                                else {
                                    str = str_sch + "(" + str_cam + ")";
                                }
                                b_school = false;
                                cam = false;
                            }
                            break;
                    }

                    eventType = parser.next();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


    }


    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }


}