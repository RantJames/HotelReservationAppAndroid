package com.example.androidtut;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import retrofit.Callback;
import java.util.ArrayList;
import java.util.List;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HotelResultFragment extends Fragment implements ItemClickListener{
    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    List<HotelListData> userListResponseData;
    Integer numguests;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.hotel_result_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        headingTextView=view.findViewById(R.id.heading_text_view);
        progressBar=view.findViewById(R.id.progress_bar);
        String checkInDate =getArguments().getString("check in date");
        String checkoutdate=getArguments().getString("check out date");
        numguests = getArguments().getInt("number of guests");


        headingTextView.setText("Welcome, displaying hotel for "+numguests+" for checkin date "+ checkInDate +" and checkoutdate "+checkoutdate);

        getHotelsListsData();
        //setupRecyclerView();

    }
    public ArrayList<HotelListData> initHotelListData() {
        ArrayList<HotelListData> list = new ArrayList<>();

        list.add(new HotelListData("Halifax Regional Hotel", "2000$", "A", "50"));
        list.add(new HotelListData("Hotel Pearl", "500$", "A", "50"));
        list.add(new HotelListData("Hotel Amano", "800$", "A", "50"));
        list.add(new HotelListData("San Jones", "250$", "A", "50"));
        list.add(new HotelListData("Halifax Regional Hotel", "2000$", "A", "50"));
        list.add(new HotelListData("Hotel Pearl", "500$", "A", "50"));
        list.add(new HotelListData("Hotel Amano", "800$", "A", "50"));
        list.add(new HotelListData("San Jones", "250$", "A", "50"));

        return list;
    }
    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);
        Api.getClient().getHotelsLists(new Callback<List<HotelListData>>() {
            @Override
            public void success(List<HotelListData> userListResponses, Response response) {
                // in this method we will get the response from API
                Log.e("Hotels", String.valueOf(userListResponses.size()));
                userListResponseData = userListResponses;


                // Set up the RecyclerView
                setupRecyclerView();
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
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(),userListResponseData);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
        hotelListAdapter.setClickListener(this::onClick);

    }
    @Override
    public void onClick(View view, int position) {
       HotelListData hotelListData = userListResponseData.get(position);
//        HotelListData hotelListData = initHotelListData().get(position);


        String hotelName = hotelListData.getHotel_name();
        String price = hotelListData.getPrice();
        String availability = hotelListData.getAvailable_rooms();
        String address = hotelListData.getAddress();

        Bundle bundle = new Bundle();
        bundle.putString("hotel name", hotelName);
        bundle.putString("hotel price", price);
        //Log.e("Availability",availability);
        bundle.putString("hotel availability", availability);
        bundle.putString("address", address);
        bundle.putInt("numguests", numguests);

        HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
        hotelGuestDetailsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(HotelResultFragment.this);
        fragmentTransaction.replace(R.id.main_layout, hotelGuestDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();

    }


}
