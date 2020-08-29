package com.ztc.vegetable.manage.api.goods.service.res;

import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.db.model.ShopList;

import java.io.Serializable;
import java.util.List;

public class QryMainPageModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 首页信息
     */
    private List<ShopList> pageInfo;

    public List<ShopList> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(List<ShopList> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
