package com.example.androidtut;

public class HotelListData {

    String name;
    String price;
    String address;
    String rooms_available;

    public HotelListData(String hotel_name, String price, String address, String available_rooms) {
        this.name = hotel_name;
        this.price = price;
        this.address = address;
        this.rooms_available = available_rooms;
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
        return rooms_available;
    }

    public void setAvailable_rooms(String available_rooms) {
        this.rooms_available = available_rooms;
    }
}