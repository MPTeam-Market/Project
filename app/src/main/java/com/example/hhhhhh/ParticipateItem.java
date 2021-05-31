package com.example.hhhhhh;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.io.Serializable;
import java.util.Date;

public class ParticipateItem implements Serializable {
    String limitmember;
    String title;
    String school;
    String participate_Uid;
    String participate_Name;
    boolean isparticipate;
    String category;
    String phone;
    String content;
    Date createdAt;
    String id;
    String img;


    public ParticipateItem(String title, String school, String limitmember, String participate_Uid, String participate_Name, boolean isparticipate, String category, String phone, String content, Date createdAt, String img) {
        this.title = title;
        this.school = school;
        this.limitmember = limitmember;
        this.participate_Uid = participate_Uid;
        this.participate_Name = participate_Name;
        this.isparticipate = isparticipate;
        this.category = category;
        this.phone = phone;
        this.content = content;
        this.createdAt = createdAt;
        this.img = img;
    }

    public ParticipateItem(String title, String school, String limitmember, String participate_Uid, String participate_Name, boolean isparticipate, String category, String phone, String content, Date createdAt, String img, String id) {
        this.title = title;
        this.school = school;
        this.limitmember = limitmember;
        this.participate_Uid = participate_Uid;
        this.participate_Name = participate_Name;
        this.isparticipate = isparticipate;
        this.category = category;
        this.phone = phone;
        this.content = content;
        this.createdAt = createdAt;
        this.img = img;
        this.id = id;
    }


    public String getLimitmember() { return limitmember; }
    public String getSchool() {
        return school;
    }
    public String getTitle() {
        return title;
    }
    public String getParticipateUid() {
        return participate_Uid;
    }
    public String getParticipateName() {
        return participate_Name;
    }
    public boolean getisParticipate() { return isparticipate; }
    public String getCategory() {
        return category;
    }
    public String getPhone(){return phone;}
    public String getContent(){return content;}
    public Date getDate(){return createdAt;}
    public String getId() { return id; }
    public String getImg(){return img;}

    public void setSLimitmember(String limitmember) {
        this.limitmember = limitmember;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setParticipateUid(String participate_Uid) {this.participate_Uid = participate_Uid;}
    public void setParticipateName(String participate_Name) {this.participate_Name = participate_Name;}
    public void setisParticipate(boolean isparticipate) { this.isparticipate = isparticipate; }
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
