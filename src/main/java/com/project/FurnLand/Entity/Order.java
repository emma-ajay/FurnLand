package com.project.FurnLand.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long total;

    private Boolean hasPaid;

    private Long userId;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderedItem> orderedItems;

    public Set<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Set<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }


    @ManyToOne
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private User user;

    public Order() {
    }

    public Order(Long total, Boolean hasPaid, Long userId) {

        this.total = total;
        this.hasPaid = hasPaid;
        this.userId = userId;
    }

    public Order(Long id, Long total, Boolean hasPaid, Long userId) {
        this.id = id;
        this.total = total;
        this.hasPaid = hasPaid;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Boolean getHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(Boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
