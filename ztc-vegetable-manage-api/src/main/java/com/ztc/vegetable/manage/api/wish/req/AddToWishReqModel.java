package com.ztc.vegetable.manage.api.wish.req;

import com.ztc.vegetable.manage.db.model.ShopList;

import java.io.Serializable;

public class AddToWishReqModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private ShopList shopList;

    private Integer Uid;


    public ShopList getShopList() {
        return shopList;
    }

    public void setShopList(ShopList shopList) {
        this.shopList = shopList;
    }

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

}
