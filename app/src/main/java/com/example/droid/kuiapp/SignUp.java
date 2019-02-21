package com.example.droid.kuiapp;

public class SignUp {
    private String firstname;
    private String lastname;
    private String phoneNo;
    private String Email;
    private String dateofbirth;
    private String password;

    public SignUp(String fn, String ln, String email, String phno, String dob, String pass) {
        firstname = fn;
        lastname = ln;
        Email = email;
        phoneNo = phno;
        dateofbirth = dob;
        password = pass;
    }

    public String getFirstname() {
        return firstname;
    }
    public String getLastname(){return lastname;}
public String getPhoneNo(){
        return phoneNo;
}
public String getEmail() { return Email;}
public String getDateofBirth(){return dateofbirth;}
public String getPassword(){return password;}
}
