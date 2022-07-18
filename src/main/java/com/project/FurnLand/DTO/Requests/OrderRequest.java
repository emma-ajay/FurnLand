package com.project.FurnLand.DTO.Requests;

import javax.persistence.Column;

public class OrderRequest {


    private  Long id;
    private String address;



    public OrderRequest( Long id , String address ) {

        this.address = address;

    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
