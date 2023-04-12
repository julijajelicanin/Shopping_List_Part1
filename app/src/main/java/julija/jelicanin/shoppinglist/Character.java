package julija.jelicanin.shoppinglist;

public class Character {
    private String mName;
    private String mShared;

    public Character(String mName, String mShared) {
        this.mName = mName;
        this.mShared = mShared;
    }

    public String getmName() {
        return mName;
    }

    public String getmShared() {
        return mShared;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmShared(String mShared) {
        this.mShared = mShared;
    }
}
