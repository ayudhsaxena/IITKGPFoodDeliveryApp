
package com.example.onlinefooddeliveryapp.users;
import java.util.*;


public class User {
    protected String username;
    protected String firstName;
    protected String secondName;
    protected String email;
    protected String hall;
    protected String mobileNo;
    
    public User(HashMap m){
        username = (String)m.get("username");
        firstName = (String)m.get("fname");
        secondName = (String)m.get("lname");
        email = (String)m.get("email");
        hall = (String)m.get("hall");
        mobileNo = (String)m.get("mobile");
    }
    
    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
