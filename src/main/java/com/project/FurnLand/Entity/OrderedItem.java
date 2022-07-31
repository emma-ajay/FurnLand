package com.project.FurnLand.Entity;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderedItemId;

    private Long userId;

    private Long orderId;

    private Long vendorId;

    private String itemName;

    private String  itemType;

    private String  itemCategory;

    private String   itemPrice;

    private String itemDescription;

    private String imageUrl;

    private String deliveryEstimation;

    private Long count;

    private Boolean hasBeenConfirmed;

    private Boolean hasBeenSent;

    private Boolean hasBeenDelivered;

    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false,updatable = false)
    private Order order;

    public OrderedItem() {
    }

    public OrderedItem(Long userId, Long orderId, Long vendorId, String itemName, String itemType, String itemCategory, String itemPrice, String itemDescription, String imageUrl, String deliveryEstimation, Long count, Boolean hasBeenConfirmed, Boolean hasBeenSent, Boolean hasBeenDelivered) {
        this.userId = userId;
        this.orderId = orderId;
        this.vendorId = vendorId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
        this.deliveryEstimation = deliveryEstimation;
        this.count = count;
        this.hasBeenConfirmed = hasBeenConfirmed;
        this.hasBeenDelivered= hasBeenDelivered;
        this.hasBeenSent = hasBeenSent;
    }

    public OrderedItem(Long orderedItemId, Long userId, Long orderId, Long vendorId, String itemName, String itemType, String itemCategory, String itemPrice, String itemDescription, String imageUrl, String deliveryEstimation, Long count, Boolean hasBeenConfirmed, Boolean hasBeenSent, Boolean hasBeenDelivered) {
        this.orderedItemId = orderedItemId;
        this.userId = userId;
        this.orderId = orderId;
        this.vendorId = vendorId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
        this.deliveryEstimation = deliveryEstimation;
        this.count = count;
        this.hasBeenConfirmed = hasBeenConfirmed;
        this.hasBeenDelivered= hasBeenDelivered;
        this.hasBeenSent = hasBeenSent;

    }

    public Long getOrderedItemId() {
        return orderedItemId;
    }

    public void setOrderedItemId(Long orderedItemId) {
        this.orderedItemId = orderedItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Boolean getHasBeenConfirmed() {
        return hasBeenConfirmed;
    }

    public void setHasBeenConfirmed(Boolean hasBeenConfirmed) {
        this.hasBeenConfirmed = hasBeenConfirmed;
    }

    public Boolean getHasBeenSent() {
        return hasBeenSent;
    }

    public void setHasBeenSent(Boolean hasBeenSent) {
        this.hasBeenSent = hasBeenSent;
    }

    public Boolean getHasBeenDelivered() {
        return hasBeenDelivered;
    }

    public void setHasBeenDelivered(Boolean hasBeenDelivered) {
        this.hasBeenDelivered = hasBeenDelivered;
    }
}
