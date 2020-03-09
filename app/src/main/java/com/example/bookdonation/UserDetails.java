package com.example.bookdonation;

public class UserDetails {

   public String Name;
   public String Address;
   public String Phone_number;
   public String Email_id;
   public int Credit;

    public UserDetails(){

    }

    public UserDetails(String name, String address, String phone, String email)
    {
        Name = name;
        Address = address;
        Phone_number = phone;
        Email_id = email;
        Credit = 4;
    }

}
