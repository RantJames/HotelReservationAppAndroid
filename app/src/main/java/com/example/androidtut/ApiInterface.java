package com.example.androidtut;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ApiInterface {

    // API's endpoints
    @GET("/hotelsList")
    public void getHotelsLists(Callback<List<HotelListData>> callback);
    public void getGuestsLists(Callback<List<GuestListData>> callback);


}