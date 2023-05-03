package julija.jelicanin.shoppinglist;

public class DBLists {
    public String mListName;
    public String mListCreator;
    public boolean mListShared;

    public DBLists(String mListName, String mListCreator, boolean mListShared) {
        this.mListName = mListName;
        this.mListCreator = mListCreator;
        this.mListShared = mListShared;
    }

    public String getmListName() {
        return mListName;
    }

    public void setmListName(String mListName) {
        this.mListName = mListName;
    }

    public String getmListCreator() {
        return mListCreator;
    }

    public void setmListCreator(String mListCreator) {
        this.mListCreator = mListCreator;
    }

    public boolean getmListShared() {
        return mListShared;
    }

    public void setmListShared(boolean mListShared) {
        this.mListShared = mListShared;
    }
}
