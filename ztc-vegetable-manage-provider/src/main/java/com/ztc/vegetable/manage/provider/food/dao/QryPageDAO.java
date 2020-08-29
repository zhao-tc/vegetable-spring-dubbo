package com.ztc.vegetable.manage.provider.food.dao;

import com.ztc.vegetable.manage.api.goods.service.req.QryGoodListReqModel;
import com.ztc.vegetable.manage.db.model.ShopList;

import java.util.List;

public interface QryPageDAO {
    List<ShopList> qryGoodList (QryGoodListReqModel qryGoodListReqModel);
}
