package com.example.androidtut;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HotelGuestDetailsFragment extends Fragment {

    View view;
    String guestName, sex;
    RadioButton guestSexRadioButton;
    ProgressBar progressBar;
    List<GuestInReservation> userListResponseData;
    Integer numguests;
    GuestListAdapter guestListAdapter;
    Button guestSubmitButton;
    String checkinDate,checkoutDate;
    TextView hotelRecapTextView,guestDetailsNameTextView,guestDetailsSexTextView;
    EditText guestFirstNameEditText, guestLastNameEditText, guestAddressEditText, guestAgeEditText;
    int res;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);
        guestDetailsNameTextView = view.findViewById(R.id.hotel_guest_list_first_name_text_view);
        guestFirstNameEditText = view.findViewById(R.id.hotel_guest_list_first_name_edittext_view);
        guestLastNameEditText = view.findViewById(R.id.hotel_guest_list_last_name_edittext_view);
        guestAddressEditText = view.findViewById(R.id.hotel_guest_list_address_edittext_view);
        guestAgeEditText = view.findViewById(R.id.hotel_guest_list_age_edittext_view);
        guestDetailsSexTextView = view.findViewById(R.id.hotel_guest_list_sex_text_view);
        RadioGroup guestSexRadioGroup = view.findViewById(R.id.hotel_guest_list_radio_group);
        Button guestSubmitButton = view.findViewById(R.id.guest_submit_button);
        progressBar = view.findViewById(R.id.hotel_guest_list_progress_bar);

        String hotelName = getArguments().getString("hotel name");
        String hotelPrice = getArguments().getString("hotel price");
        String hotelAvailability = getArguments().getString("hotel availability");
        numguests = getArguments().getInt("numguests");
        checkinDate = getArguments().getString("checkinDate");
        checkoutDate = getArguments().getString("checkoutDate");


        hotelRecapTextView.setText("Hotel Name: " + hotelName + "\nPrice: " +"$"+hotelPrice+"\nAvailable Rooms: " +hotelAvailability);

        guestSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                res=guestListAdapter.validateData(guestListAdapter.holderList);
                if(res==1){
                ArrayList<GuestInReservation> guests = guestListAdapter.getGuestList();
                ReservationData reservationData = new ReservationData(hotelName, checkinDate, checkoutDate, guests);
                progressBar.setVisibility(View.VISIBLE);
                Api.getClient().postGuestsLists(reservationData, new Callback<String>() {
                    @Override
                    public void success(String confirmation, Response response) {
                    Log.e("Confirmation number",confirmation);
                        Bundle bundle = new Bundle();
                        bundle.putString("confirmation number", confirmation);

                        //set fragment class arguments
                        ConfirmationFragment confirmationFragment = new ConfirmationFragment();
                        confirmationFragment.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_layout, confirmationFragment);
                        fragmentTransaction.remove(HotelGuestDetailsFragment.this);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Confirmation failure",error.toString());
                    }
                });

            }
        }
        });


        //getGuestListsData();
        setupRecyclerView();
    }

    public ArrayList<GuestInReservation> initGuestListData() {
        ArrayList<GuestInReservation> list = new ArrayList<>();
        return list;
    }



    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        guestListAdapter = new GuestListAdapter(getActivity(), numguests);
//            a=guestListAdapter.getGuestList();
        recyclerView.setAdapter(guestListAdapter);

    }
//    public int firstnameValidation(){
//        if (TextUtils.isEmpty(guestFirstNameEditText.getText())) {
//            guestFirstNameEditText.setError("First Name is required!");
//            Toast.makeText(getActivity(), "First Name is required!", Toast.LENGTH_LONG).show();
//            return 0;
//        } else {
//            return 1;
//        }
//    }


}