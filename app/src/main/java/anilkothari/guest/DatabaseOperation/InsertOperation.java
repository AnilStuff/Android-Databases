package anilkothari.guest.DatabaseOperation;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import anilkothari.guest.waitlist.WaitlistContract;

/**
 * Created by anilkothari on 29/03/17.
 */

public class InsertOperation {
    public String guestName;
    public int mPartySize;

    public  InsertOperation (String guestName, int partySize){
        this.guestName = guestName;
        mPartySize = partySize;
    }

    public  boolean insertData (SQLiteDatabase db){
        if(db == null){
            return false;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COL_GUEST_NAME, this.guestName);
        cv.put(WaitlistContract.WaitlistEntry.COL_PARTY_SIZE, this.mPartySize);
        list.add(cv);

        //insert all guests in one transaction
        try
        {
            db.beginTransaction();
            //clear the table first
           // db.delete (WaitlistContract.WaitlistEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            //too bad :(
        }
        finally
        {
            db.endTransaction();
        }

        return true;

    }
}
