package com.example.androidtut;

public class HotelListData {

    String name;
    String price;
    String address;
    String available_rooms;

    public HotelListData(String hotel_name, String price, String address, String available_rooms) {
        this.name = hotel_name;
        this.price = price;
        this.address = address;
        this.available_rooms = available_rooms;
    }

    public String getHotel_name() {
        return name;
    }

    public void setHotel_name(String hotel_name) {
        this.name = hotel_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvailable_rooms() {
        return available_rooms;
    }

    public void setAvailable_rooms(String available_rooms) {
        this.available_rooms = available_rooms;
    }

    //    public HotelListData(String hotel_name, String price, String availability) {
//        this.hotel_name = hotel_name;
//        this.price = price;
//        this.availability = availability;
//    }
//
//    public String getHotel_name() {
//        return hotel_name;
//    }
//
//    public void setHotel_name(String hotel_name) {
//        this.hotel_name = hotel_name;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getAvailability() {
//        return availability;
//    }
//
//    public void setAvailability(String availability) {
//        this.availability = availability;
//    }
}