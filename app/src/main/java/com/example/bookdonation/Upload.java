package com.example.bookdonation;

public class Upload {


    public String Name;
     public String ImageUrl;
     public String User;
     public String phone_no;

    public Upload()
    {

    }
    public Upload(String name,String images, String  user,String phone_no)
    {
        if(name.trim().equals(""))
        {
            name = "No name";
        }
        Name = name;
        ImageUrl = images;
        this.User = user;
        this.phone_no= phone_no;

    }


}
