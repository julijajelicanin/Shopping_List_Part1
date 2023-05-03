package julija.jelicanin.shoppinglist;

import android.content.Context;

public class DBUser{
    private String mUserName;
    private String mEmail;
    private String mPassword;

    public DBUser(String mUserName, String mEmail, String mPassword) {
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
