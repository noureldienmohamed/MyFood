package com.example.nour.myfood.model;



public class User {
    private String Name;
    private String PassWord;

    public User(){}
    public User(String name,String passWord){
       Name=name;
        PassWord=passWord;

    }
    public void setName(String name){
        Name=name;
    }

    public String getPassWord(){
        return PassWord;
    }
    public String getName(){
        return Name;
    }

}
