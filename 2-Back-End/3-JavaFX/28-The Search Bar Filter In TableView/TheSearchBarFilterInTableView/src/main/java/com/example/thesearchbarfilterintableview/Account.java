package com.example.thesearchbarfilterintableview;

import javafx.beans.property.SimpleStringProperty;

public class Account {
    private SimpleStringProperty UserName = new SimpleStringProperty();
    private SimpleStringProperty Password = new SimpleStringProperty();
    private SimpleStringProperty Type = new SimpleStringProperty();

    Account(String UserName, String Password, String Type) {
        this.UserName.set(UserName);
        this.Password.set(Password);
        this.Type.set(Type);
    }

    public String getUserName() { // Corrected method name
        return UserName.get();
    }

    public String getPassword() { // Corrected method name
        return Password.get();
    }

    public String getType() { // Corrected method name
        return Type.get();
    }
}
