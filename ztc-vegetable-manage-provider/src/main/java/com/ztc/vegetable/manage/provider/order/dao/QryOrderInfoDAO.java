package com.ztc.vegetable.manage.provider.order.dao;

import com.ztc.vegetable.manage.api.order.req.QryAorderReqModel;
import com.ztc.vegetable.manage.db.model.Sorder;

import java.util.List;

public interface QryOrderInfoDAO {
    /**
     * 查询订单表详情
     */
    List<Sorder> qryOrderInfo (QryAorderReqModel qryAorderReqModel);
}
