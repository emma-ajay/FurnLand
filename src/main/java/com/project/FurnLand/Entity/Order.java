package com.project.FurnLand.Entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderGen"
    )
    @Column(name = "orderId")
    private Long id;

    @Column(name = "customerAddress")
    private String address;

    @Column(name = "deliveryStatus")
    private String status;




    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL
            })
    @JoinTable(
            name = "ordered_item",
            joinColumns = @JoinColumn(name = "orderId",nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "itemId",nullable = false, updatable = false)
    )
    List<Item> ordered = new ArrayList<>();

    public List<Item> getItems(){
        return this.ordered;
    }


    public Order() {
    }

    public Order(Long customerId, String address, String status) {

        this.address = address;
        this.status = status;
    }

    public Order(Long id, Long customerId, String address, String status) {
        this.id = id;

        this.address = address;
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
