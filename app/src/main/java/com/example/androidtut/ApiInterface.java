package com.example.androidtut;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ApiInterface {

    // API's endpoints
    @GET("/ResGenList/")
    public void getHotelsLists(@Query("checkin_date") String checkin_date, @Query("checkout_date") String checkout_date, @Query("address") String address, Callback<List<HotelListData>> callback);
    @POST("/ResGenList/")
    public void postGuestsLists(@Body ReservationData reservationData, Callback<String> callback);


}