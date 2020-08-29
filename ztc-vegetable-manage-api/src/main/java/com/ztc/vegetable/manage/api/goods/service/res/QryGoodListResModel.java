package com.ztc.vegetable.manage.api.goods.service.res;

import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.db.model.ShopList;

import java.io.Serializable;

public class QryGoodListResModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private PageInfo<ShopList> pageInfo;

    public PageInfo<ShopList> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<ShopList> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
