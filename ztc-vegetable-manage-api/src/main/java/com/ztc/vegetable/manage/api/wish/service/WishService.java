package com.ztc.vegetable.manage.api.wish.service;

import com.ztc.vegetable.manage.api.wish.req.AddToWishReqModel;
import com.ztc.vegetable.manage.api.wish.req.QryWishListReqModel;
import com.ztc.vegetable.manage.api.wish.req.SubWishReqModel;
import com.ztc.vegetable.manage.api.wish.req.WAddToCartReqModel;
import com.ztc.vegetable.manage.api.wish.res.AddToWishResModel;
import com.ztc.vegetable.manage.api.wish.res.QryWishListResModel;
import com.ztc.vegetable.manage.api.wish.res.SubWishResModel;
import com.ztc.vegetable.manage.api.wish.res.WAddToCartResModel;


public interface WishService {
    /**
     * 添加至心愿列表
     */
    AddToWishResModel addToWish(AddToWishReqModel addToWishReqModel);
    /**
     * 查询当前用户的愿望清单
     */
    QryWishListResModel qryWishList(QryWishListReqModel qryWishListReqModel);
    /**
     * 添加到购物车
     */
    WAddToCartResModel addToCart(WAddToCartReqModel waddToCartReqModel);
    /**
     * 删除愿望清单
     */
    SubWishResModel subWish(SubWishReqModel subWishReqModel);
}
