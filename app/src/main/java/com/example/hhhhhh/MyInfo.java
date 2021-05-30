package com.example.hhhhhh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MyInfo implements Serializable {
    private String title;
    private String name;
    private Date createdAt;
    private ArrayList<String> contents;
    private String id;
    private String university;
    private String Uid;

    public MyInfo(String title, String name, Date createdAt, ArrayList<String> contents, String Uid, String university, String id) {
        this.title = title;
        this.name = name;
        this.createdAt = createdAt;
        this.contents = contents;
        this.Uid = Uid;
        this.university= university;
        this.id = id;
    }

    public MyInfo(String title, String name, Date createdAt, ArrayList<String> contents, String Uid, String university) {
        this.title = title;
        this.name = name;
        this.createdAt = createdAt;
        this.contents = contents;
        this.Uid = Uid;
        this.university= university;
    }

    public MyInfo() {
    }

    public String getUid() {
        return Uid;
    }
    public void setUid(String Uid) {
        this.Uid = Uid;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDate(){
        return createdAt;
    }
    public void setDate(){
        this.createdAt = createdAt;
    }
    public ArrayList<String> getContents(){ return contents; }
    public void setContents(){
        this.contents = contents;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUniversity() {
        return university;
    }
    public void setUniversity(String title) {
        this.university = university;
    }
}
