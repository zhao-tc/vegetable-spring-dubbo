package com.ztc.vegetable.manage.api.order.res;

import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.api.order.dto.OrderInfo;

import java.io.Serializable;

public class QryOrderInfoResModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;


   private OrderInfo orderInfo;

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
