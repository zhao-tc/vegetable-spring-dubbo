package com.ztc.vegetable.manage.api.order.req;

import java.io.Serializable;

public class SubOrderReqModel implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer Uid;

    private  String orderId;

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
