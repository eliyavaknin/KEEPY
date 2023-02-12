package com.keepy;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;

import kotlin.text.UStringsKt;

public class User implements Serializable {
    private String mEmail;
    private Boolean mIsClient;
    private Boolean mIsKeeper;
    private String mUserID;
    private String mFullName;
    private String mPhone;
    private String mLocation;
    private String mAboutMe;

    public User(){
        this("",false,false);
    }



    public User(String email, Boolean isClient, Boolean isKeeper){
        this(email,isClient,isKeeper,"","","","");

    }
    public User(String email, Boolean isClient, Boolean isKeeper, String fullName, String location, String phone, String aboutMe){
        mEmail = email;
        mIsClient = isClient;
        mIsKeeper = isKeeper;
        mFullName = fullName;
        mPhone = phone;
        mLocation = location;
        mAboutMe = aboutMe;
    }
    public String getmEmail() {
        return this.mEmail;
    }

    public Boolean getmIsClient(){
        return this.mIsClient;

    }
    public String getmUserID() {
        return this.mUserID;
    }
    public String getmPhone() {
        return this.mPhone;
    }
    public String getmFullName() {
        return this.mFullName;
    }
    public String getmLocation() {
        return this.mLocation;
    }
    public String getmAboutMe() {
        return this.mAboutMe;
    }

    public Boolean getmIsKeeper(){
        return this.mIsKeeper;
    }
    public void setmIsKeeper(Boolean keeper){
        this.mIsKeeper = keeper;
    }
    public void setmIsClient(Boolean client){
        this.mIsClient = client;
    }
    public void setmEmail(String email){
        this.mEmail = email;
    }
    public void setmUserID(String UserID){
        this.mUserID = UserID;
    }
    public void setmPhone(String phone){
        this.mPhone = phone;
    }
    public void setmFullName(String fullName){
        this.mFullName = fullName;
    }
    public void setmLocation(String location){
        this.mLocation = location;
    }
    public void setmAboutMe(String aboutMe){
        this.mAboutMe = aboutMe;
    }


}
