package com.ztc.vegetable.manage.api.user.service.res;

import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.db.model.User;
import com.ztc.vegetable.manage.api.dto.ContentModel;

import java.io.Serializable;

public class UserPageInfoResModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private PageInfo<User> pageInfo;

    public PageInfo<User> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<User> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
