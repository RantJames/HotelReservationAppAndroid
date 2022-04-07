package com.example.androidtut;

public class guestInReservation {

    String first_name, last_name, gender, address;
    Integer age;

    public guestInReservation(String first_name, String last_name, String gender, String address, Integer age) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this. address = address;
        this.age = age;
    }

    public guestInReservation() {

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
