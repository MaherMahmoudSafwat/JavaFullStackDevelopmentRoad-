package com.example.navigator;

public class Person
{
    protected String FirstName;
    protected String LastName;
    protected int ID;
    protected String Email;
    protected String Password;
    protected String PhoneNumber;

    Person(String FirstName,String LastName,int ID,String Email,String Password,String PhoneNumber)
    {
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.ID=ID;
        this.Email=Email;
        this.Password=Password;
        this.PhoneNumber=PhoneNumber;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getID() {
        return ID;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
}
