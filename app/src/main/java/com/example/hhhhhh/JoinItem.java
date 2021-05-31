package com.example.hhhhhh;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.io.Serializable;
import java.util.Date;

public class JoinItem implements Serializable {
    String salary;
    String title;
    String school;
    String writerUid;
    String writerName;
    boolean isJoin;
    String category;
    String phone;
    String content;
    Date createdAt;
    String id;
    String img;


    public JoinItem(String title, String school, String salary, String writerUid, String writerName, boolean isValid, String category, String phone, String content, Date createdAt, String img) {
        this.title = title;
        this.school = school;
        this.salary = salary;
        this.writerUid = writerUid;
        this.writerName = writerName;
        this.isJoin = isJoin;
        this.category = category;
        this.phone = phone;
        this.content = content;
        this.createdAt = createdAt;
        this.img = img;
    }

    public JoinItem(String title, String school, String salary, String writerUid, String writerName, boolean isValid, String category, String phone, String content, Date createdAt, String img, String id) {
        this.title = title;
        this.school = school;
        this.salary = salary;
        this.writerUid = writerUid;
        this.writerName = writerName;
        this.isJoin = isJoin;
        this.category = category;
        this.phone = phone;
        this.content = content;
        this.createdAt = createdAt;
        this.img = img;
        this.id = id;
    }


    public String getSalary() { return salary; }
    public String getSchool() {
        return school;
    }
    public String getTitle() {
        return title;
    }
    public String getWriterUid() {
        return writerUid;
    }
    public String getWriterName() {
        return writerName;
    }
    public boolean getIsJoin() { return isJoin; }
    public String getCategory() {
        return category;
    }
    public String getPhone(){return phone;}
    public String getContent(){return content;}
    public Date getDate(){return createdAt;}
    public String getId() { return id; }
    public String getImg(){return img;}

    public void setSalary(String salary) {
        this.salary = salary;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setWriterUid(String writerUid) {
        this.writerUid = writerUid;
    }
    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
    public void setIsJoin(boolean isJoin) { this.isJoin = isJoin; }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setphone(String phone) { this.phone = phone; }
    public void setcontent(String content) {
        this.content = content;
    }
    public void setDate(Date createdAt){this.createdAt = createdAt;}
    public void setId(String id) {
        this.id = id;
    }
    public void setImg(String img){this.img = img;}
}
