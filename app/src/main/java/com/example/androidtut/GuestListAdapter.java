package com.example.androidtut;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder> {


    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;
    private int guestCount;
    ArrayList<guestInReservation> guestListData = new ArrayList<>();
    ArrayList<ViewHolder> holderList = new ArrayList<>();

    //Data gets passed in the constructor
    GuestListAdapter(Context context, Integer guestCount) {
        this.layoutInflater = LayoutInflater.from(context);
        this.guestCount = guestCount;
    }

    public ArrayList<guestInReservation> getGuestList(){
    return guestListData;
    }

    @NonNull
    @Override
    public GuestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_guest_list_layout, parent, false);
        for(int i=0;i<guestCount;i++){
          guestInReservation guests = new guestInReservation();
            guestListData.add(guests);
        }
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GuestListAdapter.ViewHolder holder, int position) {
        holderList.add(holder);

        holder.firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                guestListData.get(position).setFirst_name(s.toString());

            }
        });

        holder.lastname.addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                guestListData.get(position).setLast_name(s.toString());
            }
        }));
        holder.address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                guestListData.get(position).setAddress(s.toString());
            }
        });
        holder.age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                guestListData.get(position).setAge(Integer.parseInt(s.toString()));
            }
        });
        holder.gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==0)
                    guestListData.get(position).setGender("F");
                else
                    guestListData.get(position).setGender("M");
            }
        });
    }

    @Override
    public int getItemCount() {
        if (guestListData != null) {
            return guestCount;
        } else {
            return 0;
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        EditText firstname, lastname, address, age;
        RadioGroup gender;

        public EditText getFirstname() {
            return firstname;
        }

        public void setFirstname(EditText firstname) {
            this.firstname = firstname;
        }

        public EditText getLastname() {
            return lastname;
        }

        public void setLastname(EditText lastname) {
            this.lastname = lastname;
        }

        public EditText getAddress() {
            return address;
        }

        public void setAddress(EditText address) {
            this.address = address;
        }

        public EditText getAge() {
            return age;
        }

        public void setAge(EditText age) {
            this.age = age;
        }

        public RadioGroup getGender() {
            return gender;
        }

        public void setGender(RadioGroup gender) {
            this.gender = gender;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.hotel_guest_list_first_name_edittext_view);
            lastname = itemView.findViewById(R.id.hotel_guest_list_last_name_edittext_view);
            address = itemView.findViewById(R.id.hotel_guest_list_address_edittext_view);
            gender = itemView.findViewById(R.id.hotel_guest_list_radio_group);
            age = itemView.findViewById(R.id.hotel_guest_list_age_edittext_view);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAbsoluteAdapterPosition());
        }
    }

    public int validateData(ArrayList<ViewHolder> holderList){
        int res=1;
        for (ViewHolder holder:holderList) {
            if(TextUtils.isEmpty(holder.firstname.getText())){
                holder.firstname.setError("First Name is required");
                res = 0;
           }
            else if(TextUtils.isEmpty(holder.lastname.getText())){
                holder.lastname.setError("Last Name is required");
                res = 0;
            }
            else if((TextUtils.isEmpty(holder.address.getText()))){
                holder.address.setError("Address is required");
                res = 0;
            }
            else if((TextUtils.isEmpty(holder.age.getText()))){
                holder.age.setError("Age is required");
                res = 0;
            }

        }
        return res;
    }

}
