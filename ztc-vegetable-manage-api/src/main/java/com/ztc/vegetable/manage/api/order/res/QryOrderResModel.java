package com.ztc.vegetable.manage.api.order.res;

import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.api.order.dto.OrderInfo;

import java.io.Serializable;
import java.util.List;

public class QryOrderResModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;


   private List<OrderInfo> orderInfo;

    public List<OrderInfo> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderInfo> orderInfo) {
        this.orderInfo = orderInfo;
    }
}
