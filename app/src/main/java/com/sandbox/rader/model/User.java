package com.sandbox.rader.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class User {
    @SerializedName("user_id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @SerializedName("first_name")
    @Expose
    @ColumnInfo(name = "first_name")
    public String firstName;

    @SerializedName("last_name")
    @Expose
    @ColumnInfo(name = "last_name")
    public String lastName;

    @SerializedName("user_name")
    @Expose
    @ColumnInfo(name = "user_name")
    public String userName;

    @SerializedName("email")
    @Expose
    @ColumnInfo(name = "email")
    public String email;

    @SerializedName("password")
    @Expose
    @ColumnInfo(name = "password")
    public String password;

    public User(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }

}
