package anilkothari.guest.waitlist;

import android.provider.BaseColumns;

/**
 * Created by anilkothari on 29/03/17.
 */

public class WaitlistContract {

    public static final class WaitlistEntry implements BaseColumns{

        public static final String TABLE_NAME = "waitlist";

        public static final String COL_GUEST_NAME = "guestName";
        public static final String COL_PARTY_SIZE = "partySize";
        public static final String COL_TIME_STAMP = "timestamp";


    }
}
