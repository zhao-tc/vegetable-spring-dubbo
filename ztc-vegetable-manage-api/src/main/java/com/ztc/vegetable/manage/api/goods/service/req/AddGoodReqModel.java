package com.ztc.vegetable.manage.api.goods.service.req;

import com.ztc.vegetable.manage.db.model.ShopList;
import java.io.Serializable;

public class AddGoodReqModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private ShopList good;

    public ShopList getGood() {
        return good;
    }

    public void setGood(ShopList good) {
        this.good = good;
    }
}
