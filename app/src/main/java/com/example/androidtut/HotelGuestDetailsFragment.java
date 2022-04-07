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
    List<guestInReservation> userListResponseData;
    Integer numguests;
    GuestListAdapter guestListAdapter;
    Button guestSubmitButton;
    String checkinDate,checkoutDate;

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

        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);
        TextView guestDetailsNameTextView = view.findViewById(R.id.hotel_guest_list_first_name_text_view);
        EditText guestDetailsNameEditText = view.findViewById(R.id.hotel_guest_list_first_name_edittext_view);
        TextView guestDetailsSexTextView = view.findViewById(R.id.hotel_guest_list_sex_text_view);
        RadioGroup guestSexRadioGroup = view.findViewById(R.id.hotel_guest_list_radio_group);
//        RadioButton guestMaleRadioButton = view.findViewById(R.id.male_sex_radio_button);
//        RadioButton guestFemaleRadioButton = view.findViewById(R.id.female_sex_radio_button);
        Button guestSubmitButton = view.findViewById(R.id.guest_submit_button);
        progressBar = view.findViewById(R.id.hotel_guest_list_progress_bar);

        String hotelName = getArguments().getString("hotel name");
        String hotelPrice = getArguments().getString("hotel price");
        String hotelAvailability = getArguments().getString("hotel availability");
        numguests = getArguments().getInt("numguests");
        checkinDate = getArguments().getString("checkinDate");
        checkoutDate = getArguments().getString("checkoutDate");


        hotelRecapTextView.setText("You have selected " + hotelName + ". The cost will be $ " + hotelPrice + " and availability is " + hotelAvailability + " num of guests is " + numguests);

        guestSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                guestName = guestDetailsNameEditText.getText().toString();
//                int selectedButtonId = guestSexRadioGroup.getCheckedRadioButtonId();
//                guestSexRadioButton =(RadioButton) view.findViewById(selectedButtonId);
//                sex=guestSexRadioButton.getText().toString();
                ArrayList<guestInReservation> guests = guestListAdapter.getGuestList();
                ReservationData reservationData = new ReservationData(hotelName, checkinDate, checkoutDate, guests);
                progressBar.setVisibility(View.VISIBLE);
                Api.getClient().postGuestsLists(reservationData, new Callback<String>() {
                    @Override
                    public void success(String confirmation, Response response) {
                    Log.e("Confirmation number",confirmation);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Confirmation failure",error.toString());
                    }
                });

                    Toast.makeText(getActivity(),guestName+" is "+sex,Toast.LENGTH_LONG).show();
            }
        });


        //getGuestListsData();
        setupRecyclerView();
    }

    public ArrayList<guestInReservation> initGuestListData() {
        ArrayList<guestInReservation> list = new ArrayList<>();
        return list;
    }


    //        private void getGuestListsData() {
//            progressBar.setVisibility(View.VISIBLE);
//            Api.getClient().postGuestsLists(new Callback<List<GuestListData>>() {
//                @Override
//                public void success(List<GuestListData> userListResponses, Response response) {
//                    // in this method we will get the response from API
//                    userListResponseData = userListResponses;
//
//
//                    // Set up the RecyclerView
//                    //setupRecyclerView();
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                    // if error occurs in network transaction then we can get the error in this method.
//                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//
//                }
//            });
//        }
    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        guestListAdapter = new GuestListAdapter(getActivity(), numguests);
//            a=guestListAdapter.getGuestList();
        recyclerView.setAdapter(guestListAdapter);

        //Bind the click listener
        //hotelListAdapter.setClickListener(this::onClick);

    }


//        @Override
//        public void onClick(View view, int position) {
//            // HotelListData hotelListData = userListResponseData.get(position);
//            HotelListData hotelListData = initGuestListData().get(position);
//
//
//            String hotelName = hotelListData.getHotel_name();
//            String price = hotelListData.getPrice();
//            String availability = hotelListData.getAvailability();
//
//            Bundle bundle = new Bundle();
//            bundle.putString("hotel name", hotelName);
//            bundle.putString("hotel price", price);
//            bundle.putString("hotel availability", availability);
//
//            HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
//            hotelGuestDetailsFragment.setArguments(bundle);
//
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.remove(HotelResultFragment.this);
//            fragmentTransaction.replace(R.id.main_layout, hotelGuestDetailsFragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commitAllowingStateLoss();
//
//        }

}