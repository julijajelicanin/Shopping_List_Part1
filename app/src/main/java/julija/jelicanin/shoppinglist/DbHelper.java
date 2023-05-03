package julija.jelicanin.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shared_list_app.db";
    public static final int DATABASE_VERSION=1;

    //table one (for login activity)
    public static final String TABLE_NAME_ONE = "USERS";
    public static final String COLUMN_USERNAME="UserName";
    public static final String COLUMN_EMAIL="UserEmail";
    public static final String COLUMN_PASSWORD="UserPassword";

    //table two(for welcome activity)
    public static final String TABLE_NAME_TWO = "LISTS";
    public static final String COLUMN_LIST_NAME="ListName";
    public static final String COLUMN_LIST_CREATOR="ListCreator";
    public static final String COLUMN_LIST_STATUS="ListShared";

    //table three(for SHOW LIST activity)
    public static final String TABLE_NAME_THREE = "ITEMS";
    public static final String COLUMN_ITEM_NAME="ItemName";
    public static final String COLUMN_ITEM_LIST_NAME="ItemListName";
    public static final String COLUMN_ITEM_CHECKED="ItemChecked";
    public static final String COLUMN_ITEM_ID="ItemId";

    private static DbHelper sInstance;

    public static synchronized DbHelper getInstance(Context context){
        if(sInstance==null)
        {
            sInstance=new DbHelper(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        }
        return sInstance;
    }
    /*public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }*/

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME_ONE +
                "(" + COLUMN_USERNAME + " TEXT, " + COLUMN_EMAIL + " TEXT, "
                + COLUMN_PASSWORD + " TEXT);" );

        db.execSQL("CREATE TABLE " + TABLE_NAME_TWO +
                "(" + COLUMN_LIST_NAME + " TEXT, " + COLUMN_LIST_CREATOR + " TEXT, "
                + COLUMN_LIST_STATUS + " INT);" );

        db.execSQL("CREATE TABLE " + TABLE_NAME_THREE +
                "(" + COLUMN_ITEM_NAME + " TEXT, " + COLUMN_ITEM_LIST_NAME + " TEXT, "
                 + COLUMN_ITEM_CHECKED + " INT, "+ COLUMN_ITEM_ID + " INT);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(DBUser user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getmUserName());
        values.put(COLUMN_EMAIL, user.getmEmail());
        values.put(COLUMN_PASSWORD, user.getmPassword());

        db.insert(TABLE_NAME_ONE, null, values);
        close();
    }
    public void insert(DBLists lists) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LIST_NAME, lists.getmListName());
        values.put(COLUMN_LIST_CREATOR, lists.getmListCreator());
        values.put(COLUMN_LIST_STATUS, lists.getmListShared());

        db.insert(TABLE_NAME_TWO, null, values);
        close();
    }
    public void insert(DBItems items) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, items.getmItemName());
        values.put(COLUMN_ITEM_LIST_NAME, items.getmItemListName());
        values.put(COLUMN_ITEM_CHECKED, items.ismItemChecked());
        values.put(COLUMN_ITEM_ID, items.getmItemId());

        db.insert(TABLE_NAME_THREE, null, values);
        close();
    }

    public void deleteUser(String userName) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME_ONE, COLUMN_USERNAME + " =?", new String[] {userName});
        close();
    }

    public void deleteListName(String listName) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME_TWO, COLUMN_LIST_NAME + " =?", new String[] {listName});
        close();
    }
    public void deleteItemId(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME_THREE, COLUMN_ITEM_ID + " =?", new String[] {id});
        close();
    }


    public DBUser[] readUsers() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_ONE, null, null, null, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }
        DBUser[] users = new DBUser[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            users[i++] = createUser(cursor);
        }

        close();
        return users;
    }


    public DBUser readUser(String userName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_ONE, null, COLUMN_USERNAME + " =?", new String[] {userName}, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }

        cursor.moveToFirst();

        DBUser user = createUser(cursor);

        close();
        return user;
    }

    private DBUser createUser(Cursor cursor) {
        String userName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME));
        String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
        String userPassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));

        return new DBUser(userName, userEmail, userPassword);
    }

    public DBLists[] readLists() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_TWO, null, null, null, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }
        DBLists[] lists = new DBLists[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            lists[i++] = createList(cursor);
        }

        close();
        return lists;
    }
    public DBLists readList(String listName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_TWO, null, COLUMN_LIST_NAME + " =?", new String[] {listName}, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }

        cursor.moveToFirst();

        DBLists list = createList(cursor);

        close();
        return list;
    }
    private DBLists createList(Cursor cursor) {
        String listName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LIST_NAME));
        String listCreator = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LIST_CREATOR));
        String listShared = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LIST_STATUS));

        return new DBLists(listName, listCreator, !listShared.equals("0"));
        //string 0/1 represents boolean false/true
    }

    public DBItems[] readItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_THREE, null, null, null, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }
        DBItems[] items = new DBItems[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            items[i++] = createItem(cursor);
        }

        close();
        return items;
    }
    public DBItems readItem(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_THREE, null, COLUMN_ITEM_ID + " =?", new String[] {id}, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }

        cursor.moveToFirst();

        DBItems item = createItem(cursor);

        close();
        return item;
    }
    private DBItems createItem(Cursor cursor) {
        String itemName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ITEM_NAME));
        String itemListName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ITEM_LIST_NAME));
        String itemChecked = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ITEM_CHECKED));
        String itemId = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ITEM_ID));

        return new DBItems(itemName, itemListName,!itemChecked.equals("0"),Integer.valueOf(itemId));
    }

}
