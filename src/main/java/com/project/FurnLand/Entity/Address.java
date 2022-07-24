package com.project.FurnLand.Entity;

import javax.persistence.*;

@Entity
@Table
public class Address {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private  Long id;

    private Long userId;

    private String address;

    private String country;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId",nullable=false,insertable = false,updatable = false)
    private User user;

    public Address() {
    }

    public Address(Long userId, String address, String country) {
        this.userId = userId;
        this.address = address;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
