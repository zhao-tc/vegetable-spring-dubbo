package com.ztc.vegetable.manage.api.cart.req;

import com.ztc.vegetable.manage.db.model.Cart;

import java.io.Serializable;

public class SubCartReqModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Cart cart;

    private Integer Uid;

    private  Integer Num;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer num) {
        Num = num;
    }
}
