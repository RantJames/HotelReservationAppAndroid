package com.example.androidtut;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
//import androidx.fragment.app.FragmentTransaction;

public class HotelSearchFragment extends Fragment {

    View view;
    TextView titleTextView; //camel case naming convention;
    ConstraintLayout mainLayout;
    DatePicker checkinDatePicker, checkoutDatePicker;
    EditText guestCount, guestName;
    Button confirmMySearch, search;
    String checkinDate,checkoutDate,guest_name;
    int numberOfGuests;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.hotel_search_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleTextView = view.findViewById(R.id.title_text_view);
        checkinDatePicker=view.findViewById(R.id.checkin_date_picker);
        checkoutDatePicker=view.findViewById(R.id.checkout_date_picker);
        guestCount = view.findViewById(R.id.num_guest_editText);
        guestName = view.findViewById(R.id.guest_name_editText);
        confirmMySearch = view.findViewById(R.id.confirm_my_search_button);
        search = view.findViewById(R.id.search_button);
        mainLayout = view.findViewById(R.id.main_layout);

        //set Title Text
        titleTextView.setText(R.string.welcome_text);

//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             checkinDate = getDateFromCalendar(checkinDatePicker);
             checkoutDate = getDateFromCalendar(checkoutDatePicker);
             numberOfGuests = Integer.parseInt(guestCount.getText().toString());
            // guest_name=guestName.getText().toString();
             
             Bundle bundle = new Bundle();
             bundle.putString("check in date", checkinDate);
             bundle.putString("check out date", checkoutDate);
             bundle.putInt("number of guests", numberOfGuests);

             //set fragment class arguments
                HotelResultFragment hotelResultFragment = new HotelResultFragment();
                hotelResultFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, hotelResultFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });    
    }

    private String getDateFromCalendar(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month =  datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(calendar.getTime());
        return formattedDate;
    }
    }

