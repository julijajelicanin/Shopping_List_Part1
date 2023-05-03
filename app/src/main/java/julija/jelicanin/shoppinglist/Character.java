package julija.jelicanin.shoppinglist;

public class Character {
    private String mName;
    private  boolean mShared;

    public Character(String mName, boolean mShared) {
        this.mName = mName;
        this.mShared = mShared;
    }

    public String getmName() {
        return mName;
    }

    public boolean getmShared() {
        return mShared;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmShared(boolean mShared) {
        this.mShared = mShared;
    }

}
