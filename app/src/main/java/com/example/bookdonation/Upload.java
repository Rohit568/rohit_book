package com.example.bookdonation;

import java.util.jar.Attributes;

public class Upload {


    String Name;
    String ImageUrl;
    String phone_no;

    public Upload() {

    }

    public Upload(String name, String images, String phon) {
        if (name.trim().equals("")) {
            name = "No name";
        }
        Name = name;
        ImageUrl = images;
        this.phone_no = phon;
    }

    public String getName() {
        return Name;
    }


    public String getImageUrl() {
        return ImageUrl;
    }


    public String getPhone_No() {
        return phone_no;
    }

    public void setName(String name) {
        Name = name;
    }



    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}


