package com.example.hhhhhh;

import java.io.Serializable;
import java.util.Date;

public class SellItem implements Serializable {
    String price;
    String title;
    String school;
    String sellerUid;
    String sellerName;
    boolean isSelling;
    String category;
    String phone;
    String content;
    Date createdAt;
    String id;
    String img;


    public SellItem(String title, String school, String price, String sellerUid, String sellerName, boolean isSelling, String category, String phone, String content, Date createdAt, String img) {
        this.title = title;
        this.school = school;
        this.price = price;
        this.sellerUid = sellerUid;
        this.sellerName = sellerName;
        this.isSelling = isSelling;
        this.category = category;
        this.phone = phone;
        this.content = content;
        this.createdAt = createdAt;
        this.img = img;
    }

    public SellItem(String title, String school, String price, String sellerUid, String sellerName, boolean isSelling, String category, String phone, String content, Date createdAt, String img, String id) {
        this.title = title;
        this.school = school;
        this.price = price;
        this.sellerUid = sellerUid;
        this.sellerName = sellerName;
        this.isSelling = isSelling;
        this.category = category;
        this.phone = phone;
        this.content = content;
        this.createdAt = createdAt;
        this.img = img;
        this.id = id;
    }


    public String getPrice() { return price; }
    public String getSchool() {
        return school;
    }
    public String getTitle() {
        return title;
    }
    public String getSellerUid() {
        return sellerUid;
    }
    public String getSellerName() {
        return sellerName;
    }
    public boolean getIsSelling() { return isSelling; }
    public String getCategory() {
        return category;
    }
    public String getPhone(){return phone;}
    public String getContent(){return content;}
    public Date getDate(){return createdAt;}
    public String getId() { return id; }
    public String getImg(){return img;}

    public void setPrice(String price) {
        this.price = price;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
    public void setIsSelling(boolean isSelling) { this.isSelling = isSelling; }
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
