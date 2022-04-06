package com.example.androidtut;

public class GuestListData {

    String guest_name;

    public GuestListData(String guest_name, String sex) {
        this.guest_name = guest_name;
        this.sex = sex;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    String sex;
}
