package com.project.FurnLand.Entity;

import javax.persistence.*;

@Entity
@Table
public class SelectedItem {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;

    private Long userId;

    private Long vendorId;

    private String itemName;

    private String  itemType;

    private String  itemCategory;

    private String   itemPrice;

    private String itemDescription;

    private String imageUrl;

    private String deliveryEstimation;

    private Long count;

    private Long cartId;

    public SelectedItem() {
    }

    @ManyToOne
    @JoinColumn(name = "cartId",insertable = false,nullable = false,updatable = false)
    private Cart cart;


    public SelectedItem(Long vendorId, String itemName, String itemType, String itemCategory, String itemPrice, String itemDescription, String imageUrl, String deliveryEstimation, Long count, Long cartId) {
        this.vendorId = vendorId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
        this.deliveryEstimation = deliveryEstimation;
        this.count = count;
        this.cartId= cartId;
    }

    public SelectedItem(Long id, Long userId, Long vendorId, String itemName, String itemType, String itemCategory, String itemPrice, String itemDescription, String imageUrl, String deliveryEstimation, Long count,Long cartId) {
        Id = id;
        this.userId = userId;
        this.vendorId = vendorId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
        this.deliveryEstimation = deliveryEstimation;
        this.count = count;
        this.cartId= cartId;
    }

    public Long getId() {
        return Id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        Id = id;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
}
