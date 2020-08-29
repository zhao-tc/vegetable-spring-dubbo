package com.ztc.vegetable.manage.api.goods.service;

import com.ztc.vegetable.manage.api.goods.service.req.AddGoodReqModel;
import com.ztc.vegetable.manage.api.goods.service.req.QryGoodListReqModel;
import com.ztc.vegetable.manage.api.goods.service.res.*;
import com.ztc.vegetable.manage.db.model.ShopList;

public interface GoodService {
    /**
     * 分页查询
     * @return
     */
    GoodPageInfoResModel showPageGood(int pageNum );
    /**
     * 删除
     */
    DeleteGoodModel deleteGood(String id);
    /**
     * 修改商品信息
     */
    ModifyGoodModel modifyGood(ShopList shopList);
    /**
     * 增加商品
     */
    AddGoodResModel addGood(AddGoodReqModel addGoodReqModel);

    /**
     * 查询首页信息
     */
    QryMainPageModel qryMainPage();

    /**
     * 查询商品列表
     */
    QryGoodListResModel qryGoodList(QryGoodListReqModel qryGoodListReqModel);
}
