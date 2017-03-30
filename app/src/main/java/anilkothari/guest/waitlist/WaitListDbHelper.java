package anilkothari.guest.waitlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anilkothari on 29/03/17.
 */

public class WaitListDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "waitlist.db";

    public static final int DATABASE_VERSION = 1;

    public WaitListDbHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WAITLIST_DB = "CREATE TABLE "+
                WaitlistContract.WaitlistEntry.TABLE_NAME + " ("+
                WaitlistContract.WaitlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                WaitlistContract.WaitlistEntry.COL_GUEST_NAME + " TEXT NON NULL,"+
                WaitlistContract.WaitlistEntry.COL_PARTY_SIZE + " INTEGER NON NULL,"+
                WaitlistContract.WaitlistEntry.COL_TIME_STAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+");";

        db.execSQL(SQL_CREATE_WAITLIST_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ WaitlistContract.WaitlistEntry.TABLE_NAME);
        onCreate(db);
    }
}
