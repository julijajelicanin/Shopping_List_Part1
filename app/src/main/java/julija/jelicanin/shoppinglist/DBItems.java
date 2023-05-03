package julija.jelicanin.shoppinglist;

public class DBItems {
    public String mItemName;
    public String mItemListName;
    public boolean mItemChecked;
    public int mItemId;

    public DBItems(String mItemName, String mItemListName, boolean mItemChecked, int mItemId) {
        this.mItemName = mItemName;
        this.mItemListName = mItemListName;
        this.mItemChecked = mItemChecked;
        this.mItemId = mItemId;
    }

    public String getmItemName() {
        return mItemName;
    }

    public void setmItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public String getmItemListName() {
        return mItemListName;
    }

    public void setmItemListName(String mItemListName) {
        this.mItemListName = mItemListName;
    }

    public boolean ismItemChecked() {
        return mItemChecked;
    }

    public void setmItemChecked(boolean mItemChecked) {
        this.mItemChecked = mItemChecked;
    }

    public int getmItemId() {
        return mItemId;
    }

    public void setmItemId(int mItemId) {
        this.mItemId = mItemId;
    }
}
