package com.example.androidtut;

import android.os.Bundle;
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
    List<GuestListData> userListResponseData;

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
        TextView guestDetailsNameTextView = view.findViewById(R.id.hotel_guest_list_name_text_view);
        EditText guestDetailsNameEditText = view.findViewById(R.id.hotel_guest_list_name_edit_text);
        TextView guestDetailsSexTextView = view.findViewById(R.id.hotel_guest_list_sex_text_view);
        RadioGroup guestSexRadioGroup = view.findViewById(R.id.hotel_guest_list_radio_group);
//        RadioButton guestMaleRadioButton = view.findViewById(R.id.male_sex_radio_button);
//        RadioButton guestFemaleRadioButton = view.findViewById(R.id.female_sex_radio_button);
        Button guestSubmitButton = view.findViewById(R.id.guest_submit_button);
        progressBar = view.findViewById(R.id.hotel_guest_list_progress_bar);

        String hotelName = getArguments().getString("hotel name");
        String hotelPrice = getArguments().getString("hotel price");
        String hotelAvailability = getArguments().getString("hotel availability");

        hotelRecapTextView.setText("You have selected " + hotelName + ". The cost will be $ " + hotelPrice + " and availability is " + hotelAvailability);

        getGuestListsData();
        setupRecyclerView();
    }

        public ArrayList<GuestListData> initGuestListData() {
            ArrayList<GuestListData> list = new ArrayList<>();

            list.add(new GuestListData("Rubin", "M"));
            list.add(new GuestListData("James", "M"));
//            list.add(new HotelListData("Hotel Amano", "800$", "true"));
//            list.add(new HotelListData("San Jones", "250$", "false"));
//            list.add(new HotelListData("Halifax Regional Hotel", "2000$", "true"));
//            list.add(new HotelListData("Hotel Pearl", "500$", "false"));
//            list.add(new HotelListData("Hotel Amano", "800$", "true"));
//            list.add(new HotelListData("San Jones", "250$", "false"));

            return list;
        }


        private void getGuestListsData() {
            progressBar.setVisibility(View.VISIBLE);
            Api.getClient().getGuestsLists(new Callback<List<GuestListData>>() {
                @Override
                public void success(List<GuestListData> userListResponses, Response response) {
                    // in this method we will get the response from API
                    userListResponseData = userListResponses;


                    // Set up the RecyclerView
                    //setupRecyclerView();
                }

                @Override
                public void failure(RetrofitError error) {
                    // if error occurs in network transaction then we can get the error in this method.
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

                }
            });
        }
        private void setupRecyclerView() {
            progressBar.setVisibility(View.GONE);
            RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            GuestListAdapter guestListAdapter = new GuestListAdapter(getActivity(),initGuestListData());
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
//        guestSubmitButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          guestName = guestDetailsNameEditText.getText().toString();
//          int selectedButtonId = guestSexRadioGroup.getCheckedRadioButtonId();
//          guestSexRadioButton =(RadioButton) view.findViewById(selectedButtonId);
//          sex=guestSexRadioButton.getText().toString();
//
//            Toast.makeText(getActivity(),guestName+" is "+sex,Toast.LENGTH_LONG).show();
//        }
//        });
    }
