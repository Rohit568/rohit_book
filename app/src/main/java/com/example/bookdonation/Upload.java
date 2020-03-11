package com.example.bookdonation;

public class Upload {

    public String Name;
     public String ImageUrl;
     public String User;

    public Upload()
    {

    }
    public Upload(String name,String images, String  user)
    {
        if(name.trim().equals(""))
        {
            name = "No name";
        }
        Name = name;
        ImageUrl = images;
        this.User = user;

    }


}
