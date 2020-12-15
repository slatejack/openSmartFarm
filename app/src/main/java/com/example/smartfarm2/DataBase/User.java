package com.example.smartfarm2.DataBase;

public class User {
    private String username;
    private String userpass;
    private int role;

    public User(String username,String userpass,int role){
        this.username=username;
        this.userpass=userpass;
        this.role=role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
