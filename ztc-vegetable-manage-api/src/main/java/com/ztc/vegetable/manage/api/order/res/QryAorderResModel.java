package com.ztc.vegetable.manage.api.order.res;

import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.db.model.Sorder;

import java.io.Serializable;

public class QryAorderResModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;



   private PageInfo<Sorder> sorderList;

    public PageInfo<Sorder> getSorderList() {
        return sorderList;
    }

    public void setSorderList(PageInfo<Sorder> sorderList) {
        this.sorderList = sorderList;
    }
}
