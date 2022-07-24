package com.project.FurnLand.DTO.Requests;

public class AddressRequest {


    private String address;

    private String country;

    public AddressRequest( String address, String country) {

        this.address = address;
        this.country = country;
    }





    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
