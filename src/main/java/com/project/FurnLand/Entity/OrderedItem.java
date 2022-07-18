package com.project.FurnLand.Entity;

import javax.persistence.*;

@Entity
@Table(name = "ordered_item")
public class OrderedItem {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE

    )
    private Long id;
    private Long orderId;
    private Long itemId;

    public OrderedItem() {
    }

    public OrderedItem(Long orderId, Long itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public OrderedItem(Long id, Long orderId, Long itemId) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
