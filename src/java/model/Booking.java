/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author bhasi
 */
public class Booking {
    
     private final String fname, lname, city, phone, email, checkIn, checkOut, roomType, adult;


    public Booking(String fname, String lname, String city, String phone, String email, String checkIn, String checkOut, String roomType, String adult) {
        this.fname = fname;
        this.lname = lname;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomType = roomType;
        this.adult = adult;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCheckIn() {
        return checkIn;
    }


    public String getCheckOut() {
        return checkOut;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getAdult() {
        return adult;
    }    
    
    
}
