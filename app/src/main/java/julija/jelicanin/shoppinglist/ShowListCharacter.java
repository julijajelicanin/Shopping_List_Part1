package julija.jelicanin.shoppinglist;

public class ShowListCharacter {
    private String mName;
    private boolean mCheck;

    public ShowListCharacter(String mName, boolean mCheck) {
        this.mName = mName;
        this.mCheck = mCheck;
    }

    public String getmName() {
        return mName;
    }

    public boolean ismCheck() {
        return mCheck;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmCheck(boolean mCheck) {
        this.mCheck = mCheck;
    }
}
