package com.swapnil.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class MainData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "role")
    private String role;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "confirm_password")
    private String confirmPassword;

    public MainData(String text, String lastName, String email, String role, String password, String confirmPassword) {
        this.text = text;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}