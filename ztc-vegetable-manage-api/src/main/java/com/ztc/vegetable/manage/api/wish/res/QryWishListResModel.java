package com.ztc.vegetable.manage.api.wish.res;

import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.db.model.Wish;

import java.io.Serializable;

public class QryWishListResModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private PageInfo<Wish> pageInfo;

    public PageInfo<Wish> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<Wish> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
