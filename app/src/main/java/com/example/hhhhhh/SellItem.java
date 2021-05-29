package com.example.hhhhhh;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class SellItem {
   int image;
  String price;
    String title;
    String school;


    public SellItem(int image, String title, String school, String price) {
        this.image = image;
        this.title = title;
        this.school = school;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getSchool() {
        return school;
    }

    public String getTitle() {
        return title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
