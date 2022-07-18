package com.project.FurnLand.DTO.Requests;

public class OrderedItemRequest {
    private Long orderId;
    private Long itemId;

    public OrderedItemRequest(Long orderId, Long itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
