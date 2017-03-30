package anilkothari.guest.Adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import anilkothari.guest.R;
import anilkothari.guest.waitlist.WaitlistContract;

/**
 * Created by anilkothari on 30/03/17.
 */

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.ViewHolder>{

    Cursor mCursor;

    public GuestAdapter(Cursor aCursor){
        this.mCursor = aCursor;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView listItemGuestName;
        private TextView listItemPartySize;

        public ViewHolder(View itemView) {
            super(itemView);
            listItemGuestName = (TextView) itemView.findViewById(R.id.guest_name);
            listItemPartySize = (TextView) itemView.findViewById(R.id.party_size);


        }

        public void bind(int position, Cursor cursor){
            if (cursor.moveToPosition(position)){

                String name = cursor.getString(cursor.getColumnIndex(WaitlistContract.WaitlistEntry.COL_GUEST_NAME));
                int partySize = cursor.getInt(cursor.getColumnIndex(WaitlistContract.WaitlistEntry.COL_PARTY_SIZE));
                long id = cursor.getLong(cursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));

                this.itemView.setTag(id);
                listItemGuestName.setText(name);
                listItemPartySize.setText(String.valueOf(partySize));

            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guest_card,parent,false
        );
        ViewHolder vw = new ViewHolder(view);
        return vw;
    }

    @Override
    public void onBindViewHolder(GuestAdapter.ViewHolder holder, int position) {
        holder.bind(position,mCursor);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}


