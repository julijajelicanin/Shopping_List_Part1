package julija.jelicanin.shoppinglist;

public class ShowListCharacter {
    private String mName;
    private boolean mCheck;
    private int mId;
    private String belonging;
    private static int tracking=0;

    public ShowListCharacter(String mName, int id, String belonging) {
        this.mName = mName;
        this.mCheck = mCheck;
        this.mId = id;
        this.belonging = belonging;

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



    public static int getTracking() {
        tracking++;
        return tracking;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getBelonging() {
        return belonging;
    }

    public void setBelonging(String belonging) {
        this.belonging = belonging;
    }

}
