package com.example.androidtut;


import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class HotelSearchFragment extends Fragment {

    View view;
    TextView titleTextView; //camel case naming convention;
    ConstraintLayout mainLayout;
    DatePicker checkinDatePicker, checkoutDatePicker;
    EditText guestCount;
    Button search;
    String checkinDate, checkoutDate, city;
    int numberOfGuests, date_res, guestField_res, guestVal_res, city_res;
    DateTimeFormatter formatter;
    LocalDate checkin_date;
    LocalDate checkout_date;
    Spinner cityDropDown;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_search_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleTextView = view.findViewById(R.id.title_text_view);
        checkinDatePicker = view.findViewById(R.id.checkin_date_picker);
        checkoutDatePicker = view.findViewById(R.id.checkout_date_picker);
        guestCount = view.findViewById(R.id.num_guest_editText);
        search = view.findViewById(R.id.search_button);
        mainLayout = view.findViewById(R.id.main_layout);
        cityDropDown = view.findViewById(R.id.city_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        cityDropDown.setAdapter(adapter);

        //set Title Text
        titleTextView.setText(R.string.welcome_text);


        search.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                checkinDate = getDateFromCalendar(checkinDatePicker);
                checkoutDate = getDateFromCalendar(checkoutDatePicker);


                city = cityDropDown.getSelectedItem().toString();

                date_res = DateValidation();
                guestField_res = guestCountFieldValidation();
                guestVal_res = guestValueValidation();
                city_res = cityValidation();

                if (date_res != 0 && guestField_res!=0 && guestVal_res!=0 && city_res!=0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("check in date", checkinDate);
                    bundle.putString("check out date", checkoutDate);
                    bundle.putInt("number of guests", numberOfGuests);
                    bundle.putString("city", city);

                    //set fragment class arguments
                    HotelResultFragment hotelResultFragment = new HotelResultFragment();
                    hotelResultFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_layout, hotelResultFragment);
                    fragmentTransaction.remove(HotelSearchFragment.this);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    private String getDateFromCalendar(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(calendar.getTime());
        return formattedDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int DateValidation() {
        //Date Validation
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        checkin_date = LocalDate.parse(checkinDate, formatter);
        checkout_date = LocalDate.parse(checkoutDate, formatter);
        if (checkin_date.isAfter(checkout_date)) {
            Toast.makeText(getActivity(), "Checkin Date cannot be greater than Checkout Date.", Toast.LENGTH_LONG).show();
            return 0;
        }
        else
            return 1;
    }

    public int guestCountFieldValidation() {
        if (TextUtils.isEmpty(guestCount.getText())) {
            guestCount.setError("guest Count is required!");
            Toast.makeText(getActivity(), "guest Count is required!", Toast.LENGTH_LONG).show();

            return 0;
        } else {
            numberOfGuests = Integer.parseInt(guestCount.getText().toString());
            return 1;
        }
    }

    public int guestValueValidation() {
        if (numberOfGuests > 6) {
            guestCount.setError("Maximum of 6 guests allowed in one booking!");
            Toast.makeText(getActivity(), "Maximum of 6 guests allowed in one booking!", Toast.LENGTH_LONG).show();
            return 0;
        }
        else
            return 1;
    }

    public int cityValidation() {
        if (city == null) {
            Toast.makeText(getActivity(), "Please provide the city.", Toast.LENGTH_LONG).show();
            return 0;
        }
        else
            return 1;
    }

}

