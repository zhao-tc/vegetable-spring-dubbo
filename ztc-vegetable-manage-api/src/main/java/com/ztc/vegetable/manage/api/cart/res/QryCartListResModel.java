package com.ztc.vegetable.manage.api.cart.res;

import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.db.model.Cart;

import java.io.Serializable;

public class QryCartListResModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private PageInfo<Cart> pageInfo;

    public PageInfo<Cart> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<Cart> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
