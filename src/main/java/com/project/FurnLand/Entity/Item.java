package com.project.FurnLand.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Items")
public class Item {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    @Column(name ="itemId")
    private Long id;

    private Long vendorId;

    private String itemName;

    private String  itemType;

    private String  itemCategory;

    private String   itemPrice;

    private String itemDescription;

    private String imageUrl;

    private String deliveryEstimation;


    @ManyToOne
    @JoinColumn(name = "vendorId", nullable=false,insertable = false,updatable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "ordered")
    private List<Order> orders = new ArrayList<>();

    public Item() {
    }

    public Item(Long vendorId, String itemName, String itemType, String itemCategory, String itemPrice, String itemDescription, String imageUrl, String deliveryEstimation) {
        this.vendorId = vendorId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
        this.deliveryEstimation = deliveryEstimation;
    }

    public Item(Long id, Long vendorId, String itemName, String itemType, String itemCategory, String itemPrice, String itemDescription, String imageUrl, String deliveryEstimation) {
        this.id = id;
        this.vendorId = vendorId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
        this.deliveryEstimation = deliveryEstimation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDeliveryEstimation() {
        return deliveryEstimation;
    }

    public void setDeliveryEstimation(String deliveryEstimation) {
        this.deliveryEstimation = deliveryEstimation;
    }
}
