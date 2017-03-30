package anilkothari.guest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import anilkothari.guest.Adapter.GuestAdapter;
import anilkothari.guest.DatabaseOperation.InsertOperation;
import anilkothari.guest.waitlist.WaitListDbHelper;
import anilkothari.guest.waitlist.WaitlistContract;

public class MainActivity extends AppCompatActivity  {

    private GuestAdapter mAdapter;

    private SQLiteDatabase mDb;

    public RecyclerView recyclerViewList;

    public EditText mtxtPartySize;
    public EditText mtxtGuestName;


    public void submit(View view){
        if (mtxtPartySize.getText().length() ==0 ||
                mtxtGuestName.getText().length() ==0){
            return;
        }

        String guestName = mtxtGuestName.getText().toString();
        int partySize = Integer.parseInt(mtxtPartySize.getText().toString());

        InsertOperation insert = new InsertOperation(guestName,partySize);

        if (insert.insertData(mDb)){
            mtxtGuestName.setText("");
            mtxtPartySize.setText("");

        }

        getAllGuests();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewList = (RecyclerView) findViewById(R.id.rv_list);
        mtxtPartySize = (EditText) findViewById(R.id.txt_party_size);
        mtxtGuestName = (EditText) findViewById(R.id.txt_guest_name);

        WaitListDbHelper helper = new WaitListDbHelper(this);

        mDb = helper.getWritableDatabase();

        getAllGuests();



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();

                delete(id);

                 getAllGuests();
             }


        }).attachToRecyclerView(recyclerViewList);



    }


    private void getAllGuests(){
        Cursor cursor =  fetchValuesFromDataBase();

        GridLayoutManager layoutManager = new GridLayoutManager(this,1);

        recyclerViewList.setLayoutManager(layoutManager);


        recyclerViewList.setHasFixedSize(true);

        mAdapter = new GuestAdapter(cursor);

        recyclerViewList.setAdapter(mAdapter);
    }

    protected Cursor fetchValuesFromDataBase(){
        return mDb.query(WaitlistContract.WaitlistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WaitlistContract.WaitlistEntry.COL_PARTY_SIZE
                );
    }

    protected boolean delete(long row){
        return mDb.delete(WaitlistContract.WaitlistEntry.TABLE_NAME, WaitlistContract.WaitlistEntry._ID + "= "+row, null) > 0 ;
    }
}
