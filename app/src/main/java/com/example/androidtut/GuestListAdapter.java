package com.example.androidtut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder> {

    private List<GuestListData> guestListData;
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    //Data gets passed in the constructor
    GuestListAdapter(Context context, List<GuestListData> guestListData) {
        this.layoutInflater = LayoutInflater.from(context);
        this.guestListData = guestListData;
    }

    @NonNull
    @Override
    public GuestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_guest_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestListAdapter.ViewHolder holder, int position) {
        String guestName = guestListData.get(position).getGuest_name();
        String guestSex = guestListData.get(position).getSex();
        //String hotelAvailability = hotelListData.get(position).getAvailability();

        // set up the text
        //holder.guestName.setText(guestName);
        //holder.guestSex.setText(guestSex);
        //holder.hotelPrice.setText(hotelPrice);
    }

    @Override
    public int getItemCount() {
        if (guestListData != null) {
            return guestListData.size();
        } else {
            return 0;
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView guestName, guestSex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guestName = itemView.findViewById(R.id.hotel_guest_list_name_text_view);
            //guestSex = itemView.findViewById(R.id.hotel_guest_list_radio_group);
//            int selectedButtonId = guestSexRadioGroup.getCheckedRadioButtonId();
//          guestSexRadioButton =(RadioButton) view.findViewById(selectedButtonId);
//          sex=guestSexRadioButton.getText().toString();
            //hotelAvailability = itemView.findViewById(R.id.availability_text_view);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAbsoluteAdapterPosition());
        }
    }

}
