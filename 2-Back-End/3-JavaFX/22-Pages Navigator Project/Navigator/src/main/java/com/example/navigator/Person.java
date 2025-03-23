package com.example.navigator;

public class Person
{
    protected String FirstName;
    protected String LastName;
    protected int ID;
    protected String Email;
    protected String Password;

    Person(String FirstName,String LastName,int ID,String Email,String Password)
    {
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.ID=ID;
        this.Email=Email;
        this.Password=Password;
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

    public void setID(int ID) {
        this.ID = ID;
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
}
