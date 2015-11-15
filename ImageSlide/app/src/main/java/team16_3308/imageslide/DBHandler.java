package team16_3308.imageslide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import java.util.ArrayList;


public class DBHandler{
    //Parts taken from http://stackoverflow.com/questions/18160712/using-sqlite-database-for-the-very-first-time-in-android-how
    public static final String DATABASE_NAME = "sites";
    public static final String DATABASE_TABLE = "reddit";
    public static final int DATABASE_VERSION = 1;
    private AppCompatActivity activity;
    public static final String KEY_ROWID = "_id";
    public static final String KEY_SUBNAME = "subname";
    private static final String TAG = "DBAdapter";


    private static final String DATABASE_CREATE =
            "CREATE TABLE reddit(id INTEGER PRIMARY KEY AUTOINCREMENT, subname TEXT)";

    private final Context context;

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBHandler(Context ctx)
    {
        this.context = ctx;
        helper = new DBHelper(context);
    }


    private static class DBHelper extends SQLiteOpenHelper{
        DBHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ". which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS reddit");
            onCreate(db);
        }

    }

    public DBHandler open() throws SQLException{
        db = helper.getWritableDatabase();
        return this;
    }

    public void close(){
        helper.close();
    }

    public long insertSub(String subname){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_SUBNAME, subname);
        return db.insert(DATABASE_TABLE, null, initialValues);

    }

    public ArrayList<String> readAll(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            if(db!=null) {
                Cursor mCursor = db.rawQuery("select * from reddit", null);


                if (mCursor.moveToFirst()) {
                    while (!mCursor.isAfterLast()) {
                        String name = mCursor.getString(mCursor.getColumnIndex("subname"));
                        list.add(name);
                        mCursor.moveToNext();
                    }
                }
            }
        }catch (SQLiteException e){
            //database does not exist
        }

        return list;
    }

}
