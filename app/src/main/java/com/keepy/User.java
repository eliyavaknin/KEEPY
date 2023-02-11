package com.keepy;

public class User {
    private String mEmail;
    private Boolean mIsClient;
    private Boolean mIsKeeper;

    public User(String email, Boolean isClient, Boolean isKeeper){
        mEmail = email;
        mIsClient = isClient;
        mIsKeeper = isKeeper;

    }

    public String getmEmail() {
        return this.mEmail;
    }

    public Boolean getmIsClient(){
        return this.mIsClient;

    }
    public Boolean getmIsKeeper(){
        return this.mIsKeeper;
    }
    public void setmEmail(String email){
         this.mEmail = email;
    }
    public void setmIsKeeper(Boolean keeper){
        this.mIsKeeper = keeper;
    }
    public void setmIsClient(Boolean client){
        this.mIsClient = client;
    }


}
