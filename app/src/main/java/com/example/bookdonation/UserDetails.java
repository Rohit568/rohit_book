package com.example.bookdonation;
import java.io.Serializable;
public class UserDetails {

   public String name;
   public String address;
   public String phone_number;
   public String email_id;
   public int credit;

    public UserDetails(){

    }

    public UserDetails(String name, String address, String phone_number, String email_id, int credit) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.email_id = email_id;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
