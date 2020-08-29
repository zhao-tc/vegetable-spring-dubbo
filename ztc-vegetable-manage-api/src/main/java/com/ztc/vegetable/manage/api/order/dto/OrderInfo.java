package com.ztc.vegetable.manage.api.order.dto;

import com.ztc.vegetable.manage.db.model.Cart;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;


    private String name;
    private String phone;
    private String orderId;
    private String adress;
    private Date times;
    private Double price;

    private List<Cart> carts;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
