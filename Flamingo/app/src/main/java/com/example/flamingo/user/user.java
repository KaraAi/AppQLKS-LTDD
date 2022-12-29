package com.example.flamingo.user;

import java.io.Serializable;

public class user implements Serializable {
    private String UserName;
    private String PassWord;
    private String Email;
    private String Phone;
    private int sex;
    private String chucvu;
    private String avata;
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {

        this.UserName = userName;
    }


    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        this.PassWord = passWord;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getChucvu() {return chucvu;}
    public void setChucvu(String chucvu) { this.chucvu = chucvu;}
    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }
}
