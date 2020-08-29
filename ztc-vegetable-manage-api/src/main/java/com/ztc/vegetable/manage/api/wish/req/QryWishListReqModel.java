package com.ztc.vegetable.manage.api.wish.req;

import java.io.Serializable;

public class QryWishListReqModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * y用户id
     */
    private Integer Uid ;
    /**
     * 当前页
     */
    private Integer  pageNum;
    /**
     *每页条数
     */
    private Integer  pageSize;

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
