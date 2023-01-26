package com.keepy;

public class User {
    private String mEmail;
    private String mPassword;
    private Boolean mIsClient;
    private Boolean mIsKeeper;

    public User(String email, String password, Boolean isClient, Boolean isKeeper){
        mEmail = email;
        mPassword = password;
        mIsClient = isClient;
        mIsKeeper = isKeeper;

    }

    public String getmEmail(){
        return this.mEmail;

    } public String getmPassword(){
        return this.mPassword;

    } public Boolean getmIsClient(){
        return this.mIsClient;

    }
    public Boolean getmIsKeeper(){
        return this.mIsKeeper;
    }
    public void setmEmail(String email){
         this.mEmail = email;
    }
    public void setmPassword(String password){
        this.mPassword = password;
    }
    public void setmIsKeeper(Boolean keeper){
        this.mIsKeeper = keeper;
    }
    public void setmIsClient(Boolean client){
        this.mIsClient = client;
    }


}
