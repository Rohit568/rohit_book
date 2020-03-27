package com.example.bookdonation;
import java.io.Serializable;
public class UserDetails {

   public String Name;
   public String Address;
   public String Phone_number;
   public String Email_id;
   public int Credit;

    public UserDetails(){

    }

    public UserDetails(String name, String address, String phone, String email, int credit)
    {
        Name = name;
        Address = address;
        Phone_number = phone;
        Email_id = email;
        Credit = credit;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public String getEmail_id() {
        return Email_id;
    }

    public int getCredit() {
        return Credit;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public void setEmail_id(String email_id) {
        Email_id = email_id;
    }

    public void setCredit(int credit) {
        Credit = credit;
    }
}
