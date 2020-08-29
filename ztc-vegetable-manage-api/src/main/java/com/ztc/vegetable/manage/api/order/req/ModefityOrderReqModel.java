package com.ztc.vegetable.manage.api.order.req;

import java.io.Serializable;

public class ModefityOrderReqModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 收货人电话
     */
    private String phone;

    /**
     * 收货人地址
     */
    private String adress;

    /**
     * 收货人地址
     */
    private String id;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
