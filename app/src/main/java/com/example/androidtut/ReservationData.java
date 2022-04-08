package com.example.androidtut;

import java.util.ArrayList;

public class ReservationData {

    String hotel_name;
    String checkin_date;

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(String checkin_date) {
        this.checkin_date = checkin_date;
    }

    public String getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(String checkout_date) {
        this.checkout_date = checkout_date;
    }

    public ArrayList<guestInReservation> getGuestListData() {
        return guestInReservations;
    }

    public void setGuestListData(ArrayList<guestInReservation> guestListData) {
        this.guestInReservations = guestListData;
    }

    public ReservationData(String hotel_name, String checkin_date, String checkout_date, ArrayList<guestInReservation> guestListData) {
        this.hotel_name = hotel_name;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.guestInReservations = guestListData;
    }

    String checkout_date;
    ArrayList<guestInReservation> guestInReservations;
}
