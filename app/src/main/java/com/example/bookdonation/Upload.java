package com.example.bookdonation;

import java.util.jar.Attributes;

public class Upload {


    String name;
    String imageurl;
    String phone_no;

    public Upload() {

    }

    public Upload(String name, String imageurl, String phone_no) {
        this.name = name;
        this.imageurl = imageurl;
        this.phone_no = phone_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    @Override
    public String toString() {
        return "Upload{" +
                "name='" + name + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", phone_no='" + phone_no + '\'' +
                '}';
    }
}


