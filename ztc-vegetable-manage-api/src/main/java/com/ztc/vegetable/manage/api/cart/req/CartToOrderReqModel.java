package com.ztc.vegetable.manage.api.cart.req;


import com.ztc.vegetable.manage.db.model.Cart;

import java.io.Serializable;
import java.util.List;


public class CartToOrderReqModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer Uid;
    private String name;

    private List<Cart> carts;

    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
