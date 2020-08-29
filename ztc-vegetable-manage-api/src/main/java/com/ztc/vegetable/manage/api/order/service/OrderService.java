package com.ztc.vegetable.manage.api.order.service;

import com.ztc.vegetable.manage.api.order.req.ModefityOrderReqModel;
import com.ztc.vegetable.manage.api.order.req.QryAorderReqModel;
import com.ztc.vegetable.manage.api.order.req.SubOrderReqModel;
import com.ztc.vegetable.manage.api.order.res.ModefityOrderResModel;
import com.ztc.vegetable.manage.api.order.res.QryAorderResModel;
import com.ztc.vegetable.manage.api.order.res.QryOrderInfoResModel;
import com.ztc.vegetable.manage.api.order.res.QryOrderResModel;
import com.ztc.vegetable.manage.api.order.res.SubOrderResModel;
import com.ztc.vegetable.manage.db.model.User;

public interface OrderService {
    /**
     * 查询订单
     */
    QryOrderResModel qryOrder(User user);
    /**
     * 订单删除
     */
    SubOrderResModel subOrder(SubOrderReqModel subOrderReqModel);
    /**
     * 管理员端查询所有的订单
     */
    QryAorderResModel qryOrderByAgent(QryAorderReqModel qryAorderReqModel);
    /**
     * 更具订单查询购物详情
     */
    QryOrderInfoResModel qryOrderInfoByAgent(String orderId);

    /**
     * 修改订单信息
     * @param modefityOrderReqModel
     * @return
     */
    ModefityOrderResModel modifyOrder(ModefityOrderReqModel modefityOrderReqModel);

}
