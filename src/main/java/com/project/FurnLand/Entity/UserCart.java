package com.project.FurnLand.Entity;

import javax.persistence.*;

@Entity
@Table
public class UserCart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "cart_userGen"
    )
    private  Long id ;

    private Long userId;
    private Long cartId;

    public UserCart() {
    }

    public UserCart(Long userId, long cartId) {
        this.userId = userId;
        this.cartId = cartId;
    }

    public UserCart(Long id, Long userId, Long cartId) {
        this.id = id;
        this.userId = userId;
        this.cartId = cartId;
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

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
}
