package com.example.hhhhhh;

public class UserInfo {
    String Name;
    String Email;
    String password;
    String uid;
    String Nickname;
    String school;

    public UserInfo(){
        // 필수적인 constructor
    }

    public UserInfo(String Name, String Email, String password, String uid, String Nickname, String school){
        this.Name = Name;
        this.Email = Email;
        this.password = password;
        this.uid = uid;
        this.Nickname = Nickname;
        this.school = school;
    }

    public String getName(){return Name;}
    public String getEmail(){return Email;}
    public String getpassword(){return password;}
    public String getuid(){return uid;}
    public String getNickname(){return Nickname;}
    public String getschool(){return school;}

    public void setName(String Name){this.Name = Name;}
    public void setEmail(String Email){this.Email = Email;}
    public void setpassword(String password){this.password = password;}
    public void setuid(String uid){this.uid = uid;}
    public void setNickname(String Nickname){this.Nickname = Nickname;}
    public void setschool(String school){this.school = school;}
}
