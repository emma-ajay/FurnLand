package com.project.FurnLand.DTO.Requests;

public class ItemUpdateRequest {
    private String itemName;

    private String  itemType;

    private String  itemCategory;

    private String   itemPrice;

    private String itemDescription;

    private String imageUrl;

    private String deliveryEstimation;


    public ItemUpdateRequest(String itemName, String itemType, String itemCategory, String itemPrice, String itemDescription, String imageUrl, String deliveryEstimation) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
        this.deliveryEstimation = deliveryEstimation;
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
