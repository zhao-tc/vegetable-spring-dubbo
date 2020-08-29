package com.ztc.vegetable.manage.api.cart.service;

import com.ztc.vegetable.manage.api.cart.req.AddToCartReqModel;
import com.ztc.vegetable.manage.api.cart.req.CartToOrderReqModel;
import com.ztc.vegetable.manage.api.cart.req.SubCartReqModel;
import com.ztc.vegetable.manage.api.cart.res.AddToCartModel;
import com.ztc.vegetable.manage.api.cart.res.CartToOrderResModel;
import com.ztc.vegetable.manage.api.cart.res.QryCartListResModel;
import com.ztc.vegetable.manage.api.cart.res.SubCartResModel;

public interface CartService {
    /**
     * 添加至购物车
     */
    AddToCartModel addToCart(AddToCartReqModel addToCartReqModel);
    /**
     * 查询当前用户的购物车
     */
    QryCartListResModel showCartList(Integer uid);
    /**
     * 删除购物车
     */
    SubCartResModel subCart(SubCartReqModel subCartReqModel);
    /**
     *提交订单
     */
    CartToOrderResModel toOrder(CartToOrderReqModel cartToOrderModelReq);
}
